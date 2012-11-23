package de.emgress;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
{
	private static final String TAG = "CameraPreview";

	private SurfaceHolder mHolder;
	private Camera mCamera;

	public CameraPreview(Context context, Camera camera) {
		super(context);
		mCamera = camera;

		// Install a SurfaceHolder.Callback so we get notified when the
		// underlying surface is created and destroyed.
		mHolder = getHolder();
		mHolder.addCallback(this);
		// deprecated setting, but required on Android versions prior to 3.0
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder)
	{
		// The Surface has been created, now tell the camera where to draw the preview.
		try
		{
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		}
		catch (IOException e)
		{
			Log.d(TAG, "Error setting camera preview: " + e.getMessage());
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h)
	{
		if (mHolder.getSurface() == null) { return; }

		try
		{
			mCamera.stopPreview();
		}
		catch (Exception e) {}

		// set preview size and make any resize, rotate or reformatting changes here

		try
		{
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();
		}
		catch (Exception e)
		{
			Log.d(TAG, "Error starting camera preview: " + e.getMessage());
		}
	}
}
