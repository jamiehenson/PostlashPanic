import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.net.*;

// This thread deals with the changing images as the charactors walk around the
// screen.
public class ConThread extends Thread
{
    private Boolean stopThread = false;
    private int curImageIndex = 0;
    private int id;
    private Image[] images = new Image[2];
    private MovableWithDir object;
    private int dirrection;
    private String character;

    ConThread( int dir, String charactor, MovableWithDir obj, int MoveID )
    {
        object = obj;
        dirrection = dir;
    }

    // This is called inorder to stop the thread
    public void endThread()
    {
        stopThread = true;
    }

    // This is called when the thread is started. It sets up some variables, and
    // then call the function which changes the image every 250 milliseconds
    public void run()
    {
        curImageIndex = 0;
        
        while ( !stopThread )
        {
            if ( curImageIndex == 1 )
                curImageIndex = 2;
            else
                curImageIndex = 1;

            object.changeImage( dirrection, curImageIndex );

            pause( 250 );
        }
    }

    private void pause ( int time )
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Movable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
