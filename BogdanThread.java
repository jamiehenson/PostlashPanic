import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

public class BogdanThread extends Thread
{
    private Bogdan bogdan;
    
    BogdanThread( Bogdan obj )
    {
        bogdan = obj;
    }
    
    public void run()
    {
        //While other parts of the program want the thread to, this changes the
        // images of Bogdan sleeping (flipping between the two) every x
        // millseconds. x is deturmined by bogdan.getSleepingWait().
        while ( bogdan.keepSleeping() && !bogdan.getStopForCleaner() )
        {
            bogdan.changeSleepingImage();
            pause( bogdan.getSleepingWait() );
        }
    }

    private void pause ( int time )
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(BogdanThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
