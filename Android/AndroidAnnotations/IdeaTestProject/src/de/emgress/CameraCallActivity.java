package de.emgress;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


@EActivity( R.layout.camera_result )
public class CameraCallActivity extends Activity
{
    private static final int THUMBNAIL_SIZE = 250;

    private static final int CAPTURE_IMAGE_REQUEST_CODE = 1;
    private Uri fileUri;
    private Bitmap image;

    @ViewById( R.id.imageTaken)
    public ImageView imageView;

    @Click( R.id.takePicture )
    public void pickImage()
    {
        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );

        fileUri = Uri.fromFile( new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                "IMG_" + System.currentTimeMillis() + ".jpg" ) );
        intent.putExtra( MediaStore.EXTRA_OUTPUT, fileUri );

        startActivityForResult(intent, CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAPTURE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            try
            {

                image = getThumbnail(fileUri);
                imageView.setImageBitmap( image );

            } catch (IOException e)
            {
                Log.i( "AndroidApp", "Doof" );
            }
        }
        else if( requestCode == CAPTURE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED )
        {
            Toast.makeText( this, "No Picture taken", Toast.LENGTH_SHORT ).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Click
    public void rotateLeft()
    {
        image = rotateBitmap( image, -90 );
        imageView.setImageBitmap( image );
    }

    @Click
    public void rotateRight()
    {
        image = rotateBitmap( image, 90 );
        imageView.setImageBitmap( image );
    }

    /**
     * Helper to rotate the bitmap displayed by the ImageView
     *
     * @param bitmap Bitmap to rotate
     * @param degree Degree to rotate, positive values will result
     *               in a clockwise rotation - negative values in counter-clockwise rotation
     * @return the rotated Bitmap
     */
    public Bitmap rotateBitmap( Bitmap bitmap, int degree )
    {
        Matrix matrix = new Matrix();
        matrix.setRotate( degree );
        Bitmap rotatedBitmap = Bitmap.createBitmap( bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, false);

        return rotatedBitmap;
    }

    /**
     * Reference http://stackoverflow.com/questions/3879992/get-bitmap-from-an-uri-android
     *
     * @param uri URI to image file
     * @return A thumbnail bitmap of the given image file
     * @throws FileNotFoundException
     * @throws IOException
     */

    public Bitmap getThumbnail(Uri uri) throws FileNotFoundException, IOException
    {
        InputStream input = this.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true; //optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888; //optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > THUMBNAIL_SIZE) ? (originalSize / THUMBNAIL_SIZE) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither=true;//optional
        bitmapOptions.inPreferQualityOverSpeed= true;
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        input = this.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio)
    {
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }

}