import java.awt.*;
import java.net.*;

// This contains the images of the explosions and all the methods to do with
// them.
public class Explosion extends Movable
{
    private Image[] images = new Image[3];
    private Boolean exploding = false;
    private int imageIndex = 0;
    private ExplosionThread thethread;

    // At the start, the three explosions are loaded into memory
    Explosion()
    {
        images[0] = readImage("img/UI/exp1.png");
        images[1] = readImage("img/UI/exp2.png");
        images[2] = readImage("img/UI/exp3.png");
    }

    public void makeExplosion( int bin )
    {
        switch( bin )
        {
            case 0:
                xPos = 142;
                break;
            case 1:
                xPos = 260;
                break;
            case 2:
                xPos = 375;
                break;
            case 3:
                xPos = 495;
                break;
        }

        yPos = 160;

        exploding = true;
        thethread = new ExplosionThread(this);
        thethread.start();
    }

    public void endExplosion()
    {
        exploding = false;
        setSize( 0, 0 );
    }

    public void changeExplosion()
    {
        myimage = images[imageIndex];
        setImageSize();
        makeTheMove();
        
        if ( imageIndex == 2 )
            imageIndex = 0;
        else
            imageIndex++;
    }
}
