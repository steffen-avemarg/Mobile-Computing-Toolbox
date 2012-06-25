package de.emgress.android.socketconnection;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    private static String DEVICE_IP = "127.0.0.1";


    public static void main(String[] args)
    {
        Socket socket = null;

        try
        {
            socket = new java.net.Socket( DEVICE_IP, 5000 );
            socket.setSoTimeout(1000 * 5);

            System.out.println( "Socket: " + socket.getRemoteSocketAddress() );

            BufferedWriter out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream() ) );
            out.write( "Hello Android" );
            out.flush();

            System.out.println( "Written" );

            socket.close();
        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch (Exception e1)
            {
            }

        }
    }

}
