package de.emgress.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import de.fhe.R;

public class MainActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.two_pane_layout );
    }
}