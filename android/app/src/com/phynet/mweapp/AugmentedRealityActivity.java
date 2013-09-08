package com.phynet.mweapp;

import java.io.IOException;

import com.phynet.mweapp.R;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.ImageStruct;
import com.metaio.sdk.jni.TrackingValues;
import com.metaio.sdk.jni.TrackingValuesVector;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;



@SuppressLint("SetJavaScriptEnabled")
public class AugmentedRealityActivity extends MetaioSDKViewActivity {

	private TrackingValues mTrackingValues;
	
	// will load the 3D model.
	private IGeometry mImagePlane1;
	private IGeometry mImagePlane2;
	private IGeometry mImagePlane3;
	private IGeometry mImagePlane4;
	private IGeometry mImagePlane5;

	
	//what you're gonna see when pointing to a cover
	private String assets = "Assets1/";
	private String path1 = assets + "simon_sues_port.jpg";
	private String path2 = assets + "TwoKeys_target.png";
	private String path3 = assets + "metaio1.png";
	private String path4 = assets + "metaio2.png";
	private String path5 = assets + "metaio3.png";


	private String imagePathFirstCover[]  =  {path1,path2,path3, path4,path5};
	private String imagePathSecondCover[] = {path1,path2,path3, path4,path5};
	private String imagePathThirdCover[]  =  {path1,path2,path3, path4,path5};
	private String imagePathFourthCover[] = {path1,path2,path3, path4,path5};
	private String imagePathFifthCover[]  =  {path1,path2,path3, path4,path5};
	
	
	boolean mImageTaken;
	CharSequence text = "Se ha guardado la foto en la galeria de imagenes.";
	
	// will load the assets
	AssetsExtracter mTask;
	int duration = Toast.LENGTH_SHORT;

	private MetaioSDKCallbackHandler mCallbackHandler;
	private ProgressDialog progDialog;

	@Override
	protected int getGUILayout() {
		// extract all the assets
		
		// mCallbackHandler = new MetaioSDKCallbackHandler();
		
		return R.layout.activity_augmented_reality;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mImageTaken = false;

		mCallbackHandler = new MetaioSDKCallbackHandler();		
		
		setContentView(R.layout.activity_augmented_reality);


		
		mTask = new AssetsExtracter();
		mTask.execute(0);
		
		
	
	}

	@Override
	protected void onStart() 
	{
		super.onStart();


	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
	}
	
	
	@Override
    public void onDrawFrame() 
    {
        super.onDrawFrame();

        
        if (metaioSDK != null)
        {
             // get all detected poses/targets
            TrackingValuesVector poses = metaioSDK.getTrackingValues();

            //if we have detected one, attach our metaio man to this coordinate system Id
            if (poses.size() != 0){
            	mImagePlane1.setCoordinateSystemID(1);
            	mImagePlane2.setCoordinateSystemID(2); 
            	mImagePlane3.setCoordinateSystemID(3); 
            	mImagePlane4.setCoordinateSystemID(4); 
            	mImagePlane5.setCoordinateSystemID(5); 
            }       
        }
    }

	// This class is defined in AssetsManager, it is a METAIO class.
	private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean> {
		
		
		
		@Override
		protected void onPreExecute() 
		{
			//mProgress.setVisibility(View.VISIBLE);
			//Toast.makeText(AugmentedRealityActivity.this,"Invoke onPreExecute()", Toast.LENGTH_SHORT).show();
		    
		    progDialog = ProgressDialog.show(AugmentedRealityActivity.this, "Descargando Modelos", "espere...");

		}
		
		// Will extract all the assets before we load the layout		
		@Override
		protected Boolean doInBackground(Integer... params) {
			
			
			try {
				
				AssetsManager.extractAllAssets(getApplicationContext(), true);
				
				String trackingConfigFile2 = AssetsManager.getAssetPath("Assets1/TrackingData_MarkerlessFast.xml");
				boolean result2 = metaioSDK.setTrackingConfiguration(trackingConfigFile2);
				
				MetaioDebug.log("DATOS CARGADOSSSSSSSSSSSSSSSSS: " + result2);
				System.out.println(trackingConfigFile2);
				
				
			} catch (IOException e) {
				MetaioDebug.printStackTrace(Log.ERROR, e);
				return false;
			}
			return true;
		}
		
		 @Override
		protected void onProgressUpdate(Integer... values) {
			   
			 
			 super.onProgressUpdate(values[0]);
			    
		 }
		
		@Override
		protected void onPostExecute(Boolean result) 
		{
			//Toast.makeText(AugmentedRealityActivity.this,"Invoke onPostExecute()", Toast.LENGTH_SHORT).show();
			
		    System.out.println("caragdo");
		    
		    progDialog.hide();

			
	    }

	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() 
	{
		return mCallbackHandler;
	}

	@Override
	protected void loadContent() {
		
		
		//loading a random number for accessing a image path in each array
		int randomNumber = (int) (Math.random() * 5+1);
		int randomNumber2 = (int) (Math.random() * 5+1);
		int randomNumber3 = (int) (Math.random() * 5+1);
		int randomNumber4 = (int) (Math.random() * 5+1);
		int randomNumber5 = (int) (Math.random() * 5+1);
		
		String finalPath1= imagePathFirstCover[randomNumber];
		String finalPath2 = imagePathSecondCover[randomNumber2];
		String finalPath3 = imagePathThirdCover[randomNumber3];
		String finalPath4 = imagePathFourthCover[randomNumber4];
		
		/*randomNumber = (int) (Math.random() * 5+1);*/
		String finalPath5 = imagePathFifthCover[randomNumber5];
	

		
		try {
			
			//String trackingConfigFile2 = AssetsManager.getAssetPath("Assets1/TrackingData_MarkerlessFast.xml");
			
			//asynk task doing in background = hacer el parseo  ... se pasa el type de los parametros
			//do in background
			//Inner class
			
			//System.out.println(trackingConfigFile2);

			//boolean result2 = metaioSDK.setTrackingConfiguration(trackingConfigFile2);
			
			//MetaioDebug.log("Tracking data loaded: " + result2);
			
			
			
			//loading images from each cover
			String imagePath1 = AssetsManager.getAssetPath(finalPath1);
			String imagePath2 = AssetsManager.getAssetPath(finalPath2);
			String imagePath3 = AssetsManager.getAssetPath(finalPath3);
			String imagePath4 = AssetsManager.getAssetPath(finalPath4);
			String imagePath5 = AssetsManager.getAssetPath(finalPath5);

			
			if (imagePath1 != null) {
				mImagePlane1 = metaioSDK.createGeometryFromImage(imagePath1,false);
				mImagePlane2 = metaioSDK.createGeometryFromImage(imagePath2,false);
				mImagePlane3 = metaioSDK.createGeometryFromImage(imagePath3,false);
				mImagePlane4 = metaioSDK.createGeometryFromImage(imagePath4,false);
				mImagePlane5 = metaioSDK.createGeometryFromImage(imagePath5,false);

			
				
				if (mImagePlane1 != null){
					mImagePlane1.setScale(new Vector3d(3.0f, 3.0f, 3.0f));
					MetaioDebug.log("Loaded geometry " + imagePath1);
				} else {
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "
							+ imagePath1);
				}

				if(mImagePlane2 != null){
					mImagePlane2.setScale(new Vector3d(3.0f, 3.0f, 3.0f));
					MetaioDebug.log("Loaded geometry " + imagePath2);
				} else {
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "
							+ imagePath2);
				}

				if (mImagePlane3 != null){
					mImagePlane3.setScale(new Vector3d(3.0f, 3.0f, 3.0f));
					MetaioDebug.log("Loaded geometry " + imagePath3);
				} else {
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "
							+ imagePath3);
				}

			    if (mImagePlane4 != null){
					mImagePlane4.setScale(new Vector3d(3.0f, 3.0f, 3.0f));
					MetaioDebug.log("Loaded geometry " + imagePath4);
			    } else {
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "
							+ imagePath4);
				}

				if (mImagePlane5 != null){
					mImagePlane5.setScale(new Vector3d(3.0f, 3.0f, 3.0f));
					
					MetaioDebug.log("Loaded geometry " + imagePath5);
				} else {
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "
							+ imagePath1);
				}
			}

		} catch (Exception e) {
			System.out.println("It's not working as it supposed to be");
		}

	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// We need to implement this method in order to add events when user
		// touch the imagel

	}

    @Override
    public void onBackPressed() {
            super.onBackPressed();
            progDialog.dismiss();
            this.finish();
    }
	
    // called when the save screenshot button has been pressed
 	public void onSaveScreen(View v) 
 	{
 		// request screen shot
 		metaioSDK.requestScreenshot();
 	}

	// called when the take picture button has been pressed
	public void onTakePicture(View v) 
	{
		// take a picture using the SDK and save it to external storage
		String imagepath = Environment.getExternalStorageDirectory().getPath() + "/target.jpg";
		metaioSDK.requestCameraImage(imagepath, mCameraResolution.getX(), mCameraResolution.getY());	

	}
 	
	final class MetaioSDKCallbackHandler extends IMetaioSDKCallback 
	{

		@Override
		public void onSDKReady() 
		{
			// show GUI
			runOnUiThread(new Runnable() 
			{
				@Override
				public void run() 
				{
					mGUIView.setVisibility(View.VISIBLE);
				}
			});
		}
		// callback function for taking images using SDK
        @Override
		public void onCameraImageSaved(final String filepath) 
        {
			// save the tracking values in case the application exits improperly
			mTrackingValues = metaioSDK.getTrackingValues(1);
			mImageTaken = true;

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (filepath.length() > 0)
					{
						metaioSDK.setImage(filepath);
					}
				}
			});
			
		}
       
        @Override
        public void onScreenshotImage(ImageStruct image)
        {
        	
    		// the screenshot can also be saved to the image gallery
    		Bitmap bitmap = image.getBitmap();
    		final String url = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, 
    				"screenshot_"+System.currentTimeMillis(), "screenshot");

    		// release screenshot ImageStruct
    		image.release();
    		image.delete();
    		
    		// Recycle the bitmap
    		bitmap.recycle();
    		bitmap = null;
    		
    		runOnUiThread(new Runnable()
    		{
				
				@Override
				public void run() {
					// a toast message to alert the user
					
					String message = "El Screen Shot ha sido almacenado en la galeria de fotos.";
					if (url == null)
						message = "Imposible a–adir una el screen shot a la galeria";
					
					Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					
				}
			});
        }


	}
	
}