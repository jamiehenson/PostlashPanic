import java.util.logging.Level;
import java.net.*;
import java.util.logging.Logger;

public class ExplosionThread extends Thread
{
    Explosion explosion;


    ExplosionThread( Explosion obj )
    {
        explosion = obj;
    }

    public void run()
    {
        for ( int i = 0; i < 20; i++ )
        {
            explosion.changeExplosion();
            pause( 100 );
        }

        explosion.endExplosion();
    }

    private void pause ( int time )
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(ExplosionThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
