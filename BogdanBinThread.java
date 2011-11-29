import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

// This thread contols the bins being knocked over by Bogdan
public class BogdanBinThread extends Thread
{
    private Bogdan bogdan;
    private Play play;

    BogdanBinThread( Bogdan obj1, Play obj2 )
    {
        bogdan = obj1;
        play = obj2;
    }
    
    public void run()
    {
        pause( 500 );

        for ( int i = 0; i < 4; i++ )
        {
            play.getExplosion().makeExplosion(i);
            play.takeRubbish( -5 );
            pause( 1000 );
        }

        pause( 1000 );
        bogdan.walkBack();
        pause( 800 );
        bogdan.sitBackDown();
    }

    private void pause( int time )
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(BogdanBinThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
