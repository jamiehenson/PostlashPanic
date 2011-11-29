import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeThread extends Thread
{
    private Play play;

    TimeThread( Play obj )
    {
        play = obj;
    }

    public void run()
    {
        while ( play.keepTiming() )
        {
            play.addTime();
            pause( 1000 );
        }
    }

    private void pause( int time )
    {
       try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(ErikThread.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
