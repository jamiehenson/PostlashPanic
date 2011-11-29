import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

public class PageConThread extends Thread
{
    Page page;
    Boolean wait = false;

    PageConThread( Page obj )
    {
        page = obj;
    }

    PageConThread( Page obj, Boolean w )
    {
        page = obj;
        wait = true;
    }

    public void run()
    {
        if ( wait )
            pause(100);

        while( page.keepSitting() && !page.getStopForCleaner() )
        {
            page.changeSittingImage();
            pause( 500 );
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
