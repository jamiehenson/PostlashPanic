import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

// This thread makes Erik throw rubbish every 2 seconds
class ErikThread extends Thread
{
    Erik erik;
    Boolean stop = false;
    int coffeLevel = 20;

    ErikThread( Erik obj )
    {
        erik = obj;
    }

    public void run()
    {
        
        while( !stop && !erik.getStopForCleaner() )
        {
            erik.walkToThrow();
            pause( 2000 );
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
