package de.emgress.android.appwidget;

import android.app.Activity;
import android.os.Bundle;
import de.fhe.R;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}