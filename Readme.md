# Thumber 
					
Best thumbnail picker Thumber :camera:

![thumber](https://user-images.githubusercontent.com/23655824/82667359-64ba6a80-9c40-11ea-9f8f-b0060c8390fd.png)
## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	 implementation 'com.github.hamurcuabi:Thumber:1.0.0'
	 
}
```
``` Add this to your module's `build.gradle` into android tags

```   viewBinding {
        enabled = true
    }
    dataBinding {
        enabled = true
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

```
## Configuration

1-Choose type of video . You can chag the number of thumbnail will display and time of video

   private void fromCapture() {
        Intent i = new Intent(this, ThumberActivity.class);
        i.putExtra(ThumberData.ThumberCount.name(), 5);
        i.putExtra(ThumberData.Duration.name(), 9);
        i.putExtra(ThumberData.VideoFrom.name(), VideoFrom.CAPTURE);
        startActivityForResult(i, ThumberActivity.THUMBER_ACTIVTY);
    }
```

  private void fromGallery() {
        Intent i = new Intent(this, ThumberActivity.class);
        i.putExtra(ThumberData.ThumberCount.name(), 5);
        i.putExtra(ThumberData.Duration.name(), 9);
        i.putExtra(ThumberData.VideoFrom.name(), VideoFrom.GALLERY);
        startActivityForResult(i, ThumberActivity.THUMBER_ACTIVTY);

    }
    
    2. Override onActivityResult than get your Bitmap thumnail
    
       @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ThumberActivity.THUMBER_ACTIVTY) {
            if (resultCode == Activity.RESULT_OK) {
              
                Uri uri = Uri.parse(data.getStringExtra(ThumberData.VideoUri.name()));
                int duration = data.getIntExtra(ThumberData.ThumbnailDuration.name(), 0);
                Bitmap bitmap=ThumberHelper.getThumber(this, uri, duration);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.d(TAG, "onActivityResult: Canceled");
            }
        }
    }
    
    
    
    


