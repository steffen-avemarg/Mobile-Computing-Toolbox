package de.emgress;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

public class AccelerometerActivity extends Activity implements SensorEventListener
{
    private boolean colored = false;
    private long lastEvent = System.currentTimeMillis();

    public SensorManager sensorManager;
    private ViewGroup container;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer);

        container = (ViewGroup)findViewById( R.id.accel_layout );

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        Log.i( "AndroidApp", "Sensor Data Changed" );

        if( sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER )
        {
            float[] values = sensorEvent.values;
            // Movement
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float accelationSquareRoot = (x * x + y * y + z * z)
                    / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

            if (accelationSquareRoot < 2) return;

            Log.i( "AndroidApp", "Sensor Data Changed" );

            long currentTime = System.currentTimeMillis();

            if (accelationSquareRoot < 2) return;
            if( currentTime - lastEvent < 200 ) return;

            if( colored )
            {
                container.setBackgroundColor( Color.RED );
            }
            else
            {
                container.setBackgroundColor( Color.GREEN );
            }

            colored = !colored;

            lastEvent = currentTime;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}


    @Override
    protected void onResume()
    {
        super.onResume();

        sensorManager.registerListener( this, sensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER ),
                SensorManager.SENSOR_DELAY_NORMAL );

    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener( this );
    }
}