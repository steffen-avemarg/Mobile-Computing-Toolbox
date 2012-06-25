package de.emgress.android.socketconnection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.i( "SocketConnection", "Starting" );

        SocketTask socketTask = new SocketTask();

        socketTask.execute(null, null, null);

    }


    public class SocketTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {

            ServerSocket serverSocket = null;


            Log.i( "SocketConnection", "Starting doInBackground" );

            try
            {
                Log.i( "SocketConnection", "Create Server Socket" );

                serverSocket = new ServerSocket( 6000, 0 );

                Log.i( "SocketConnection", "Server Socket created - Timeout 5 sek" );

            }
            catch ( Exception sse )
            {
                sse.printStackTrace();
            }

            Log.d("SocketConnection", "Server Online...");

            Socket clientSocket;

            try
            {
                clientSocket = serverSocket.accept();

                Log.i( "SocketConnection", "Client Connected" );

                BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );

                String input = in.readLine();

                Log.i( "SocketConnection", "Received: " + input );

                in.close();
                clientSocket.close();

            }
            catch ( Exception e )
            {
                e.printStackTrace();
                Log.d("TCP", "C: " + e);
            }

            return null;
        }
    }

}
