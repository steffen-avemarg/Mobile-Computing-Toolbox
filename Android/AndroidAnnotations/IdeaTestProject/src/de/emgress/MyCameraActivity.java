package de.emgress;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


public class MyCameraActivity extends Activity {

	private Camera mCamera;
	private CameraPreview mPreview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_camera_layout);

		// Create an instance of Camera
		mCamera = getCameraInstance();

		if( mCamera != null )
		{
		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById( R.id.camera_preview );
		preview.addView(mPreview);

		// Add a listener to the Capture button
		Button captureButton = (Button) findViewById( R.id.button_capture);
		captureButton.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// get an image from the camera
						mCamera.takePicture(null, null, mPicture);
					}
				}
		);
		}
		else
		{
			Log.i( "CameraActivity", "No Camera" );
		}
	}

	public Camera getCameraInstance()
	{
		Camera c = null;

		try
		{
			c = Camera.open();
		}
		catch (Exception e) {}

		return c;
	}


	private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera)
		{
			Log.i( "CameraActivity", "Picture taken!" );

			// Camera.release() an geeigneter Stelle aufrufen!
		}
	};
}