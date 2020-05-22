package com.emrhmrc.thumber;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.emrhmrc.genericrecycler.helpers.GRVHelper;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.thumber.adapter.ThumberAdapter;
import com.emrhmrc.thumber.databinding.ActivityThumberBinding;
import com.emrhmrc.thumber.model.ThumbnailModel;
import com.emrhmrc.thumber.util.ImageHelper;

public class ThumberActivity extends AppCompatActivity implements IOnItemClickListener<ThumbnailModel> {
    public static final int THUMBER_ACTIVTY = 1010;
    public static final int THUMBER_PICK_VIDEO = 8080;
    public static final int THUMBER_CAPTURE_VIDEO = 9090;
    public static final int THUMBER_WRITE_EXTERNAL_PERMISSION = 7070;
    public static final int THUMBER_CAMERA_PERMISSION = 6060;
    public int duration;
    private Uri videoUri;
    private String videoPath;
    private ActivityThumberBinding binding;
    private ThumberAdapter thumberAdapter;
    private VideoFrom videoFrom;
    private int thumberCount;
    private Bitmap selectedBitmap = null;
    private int oldPosition = -1;
    private int currentDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        thumberCount = getIntent().getIntExtra(ThumberData.ThumberCount.name(), 10);
        duration = getIntent().getIntExtra(ThumberData.Duration.name(), 10);
        videoFrom = (VideoFrom) getIntent().getSerializableExtra(ThumberData.VideoFrom.name());
        prepareRecylerView();
        onClicks();
        selectUploadType();

    }

    private void onClicks() {
        binding.btnContinue.setOnClickListener(view -> {
            if (selectedBitmap != null) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(ThumberData.ThumbnailDuration.name(), currentDuration);
                returnIntent.putExtra(ThumberData.VideoUri.name(), videoUri.toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            } else {
                Toast.makeText(this, getString(R.string.choose_item), Toast.LENGTH_LONG).show();
            }

        });
        binding.btnCancel.setOnClickListener(view -> {
            cancelProcess();
        });
        binding.imgPlay.setOnClickListener(view -> setPlayState());
        binding.lnrVideoContainer.setOnClickListener(view -> setPlayState());
    }

    private void prepareRecylerView() {
        thumberAdapter = new ThumberAdapter(this, this, null);
        GRVHelper.setup(thumberAdapter, binding.rcvThumbnails);
        binding.rcvThumbnails.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
    }

    private void selectUploadType() {
        if (videoFrom == VideoFrom.GALLERY) {
            videoFromGallery();
        } else if (videoFrom == VideoFrom.CAPTURE) {
            videoCapture();
        } else {
            Toast.makeText(this, getString(R.string.error_video_from), Toast.LENGTH_LONG).show();
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }
    }

    private void setPlayState() {
        if (binding.videoview.isPlaying()) {
            binding.imgPlay.setVisibility(View.VISIBLE);
            binding.videoview.pause();
            return;
        } else {
            binding.imgPlay.setVisibility(View.GONE);
            binding.videoview.start();
            binding.videoview.seekTo(currentDuration);
        }

    }

    private void videoCapture() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                THUMBER_WRITE_EXTERNAL_PERMISSION);

    }

    private void videoCaptureAfterPerms() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, duration);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, THUMBER_CAPTURE_VIDEO);
        }
    }

    private void videoFromGallery() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                THUMBER_CAMERA_PERMISSION);

    }

    private void videoFromGalleryAfterPerms() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_video)),
                    THUMBER_PICK_VIDEO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == THUMBER_CAPTURE_VIDEO) {
                assert data != null;
                videoUri = data.getData();
                binding.btnContinue.setEnabled(true);
                videoPath = ImageHelper.getRealPathFromURI(this, videoUri);
                binding.videoview.setVideoPath(videoPath);
                thumberAdapter.setItems(ThumberHelper.getThumberList(this, videoUri, thumberCount));
                setPlayState();

            } else if (requestCode == THUMBER_PICK_VIDEO) {
                assert data != null;
                videoUri = data.getData();
                binding.btnContinue.setEnabled(true);
                videoPath = ImageHelper.getRealPathFromURI(this, videoUri);
                binding.videoview.setVideoPath(videoPath);
                thumberAdapter.setItems(ThumberHelper.getThumberList(this, videoUri, thumberCount));
                setPlayState();
            }

        } else {
            cancelProcess();
        }
    }

    private void cancelProcess() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void onItemClicked(ThumbnailModel item, int positon) {
        if (oldPosition == -1) {
            oldPosition = positon;
            thumberAdapter.getItem(oldPosition).setSelected(true);
            thumberAdapter.updateItem(thumberAdapter.getItem(oldPosition), oldPosition);
        } else {
            if (oldPosition == positon) {
                thumberAdapter.getItem(positon).setSelected(true);
                thumberAdapter.updateItem(thumberAdapter.getItem(positon), positon);
            } else {
                thumberAdapter.getItem(oldPosition).setSelected(false);
                thumberAdapter.updateItem(thumberAdapter.getItem(oldPosition), oldPosition);

                thumberAdapter.getItem(positon).setSelected(true);
                thumberAdapter.updateItem(thumberAdapter.getItem(positon), positon);

                oldPosition = positon;
            }

        }
        selectedBitmap = thumberAdapter.getItem(positon).getBitmap();
        currentDuration = (int) item.getDuration();
        setPlayState();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case THUMBER_WRITE_EXTERNAL_PERMISSION: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    videoCaptureAfterPerms();
                } else {
                    Toast.makeText(this, getString(R.string.file_permission_denied), Toast.LENGTH_LONG).show();
                }
                return;
            }
            case THUMBER_CAMERA_PERMISSION: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    videoFromGalleryAfterPerms();
                } else {
                    Toast.makeText(this, getString(R.string.camera_permission_denied), Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }
}
