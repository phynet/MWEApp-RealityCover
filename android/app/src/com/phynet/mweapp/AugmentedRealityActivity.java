package com.phynet.mweapp;

import java.io.IOException;

import com.phynet.mweapp.R;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.Button;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;


@SuppressLint("SetJavaScriptEnabled")
public class AugmentedRealityActivity extends MetaioSDKViewActivity {

	// will load the 3D model.
	private IGeometry mModel;
	private IGeometry mImagePlane;

	private Button button;

	// will load the assets
	AssetsExtracter mTask;

	// private MetaioSDKCallbackHandler mCallbackHandler;

	@Override
	protected int getGUILayout() {
		// extract all the assets
		mTask = new AssetsExtracter();
		mTask.execute(0);
		// mCallbackHandler = new MetaioSDKCallbackHandler();
		return R.layout.activity_main;

		// return R.layout.activity_modal_translucid;
	}

	// This class is defined in AssetsManager, it is a METAIO class.
	private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean> {

		// Will extract all the assets before we load the layout
		@Override
		protected Boolean doInBackground(Integer... params) {
			try {
				AssetsManager.extractAllAssets(getApplicationContext(), true);
			} catch (IOException e) {
				MetaioDebug.printStackTrace(Log.ERROR, e);
				return false;
			}

			return true;
		}

	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void loadContent() {

		try {

			String trackingConfigFile2 = AssetsManager
					.getAssetPath("Assets1/TrackingData_MarkerlessFast.xml");
			System.out.println(trackingConfigFile2);

			boolean result2 = metaioSDK
					.setTrackingConfiguration(trackingConfigFile2);
			MetaioDebug.log("Tracking data loaded: " + result2);

			// Loading image geometry
			String imagePath = AssetsManager
					.getAssetPath("Assets1/simon_sues_port.jpg");

			if (imagePath != null) {
				mImagePlane = metaioSDK.createGeometryFromImage(imagePath,
						false);
				if (mImagePlane != null) {
					mImagePlane.setScale(new Vector3d(3.0f, 3.0f, 3.0f));

					MetaioDebug.log("Loaded geometry " + imagePath);
				} else {
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "
							+ imagePath);
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

}