import java.util.Random;
import java.net.*;

// This contains the images of Erik and all the methods to do with her.
public class Erik extends MovableWithDir
{
    Play play;
    int offDir;
    ErikThread thethread;

    // At the start, Erik's images and location are set. Then the thred which
    // controls him throwing rubbish at ian is started
    Erik( Play parent )
    {
        play = parent;

        // Load images and set location
        loadImages("erik");
        setImage( 1, 580, 440 );
        setImageSize();
        makeTheMove();
        
        // Start the thread
        thethread = new ErikThread( this );
    }

    // This is the getter for getOffDir.
    public int getOffDir()
    {
        return offDir;
    }

    //This throws a peice of rubbish to ian.
    public void throwRubbish()
    {
        play.getMaster().newErik();
        play.getMaster().getErik().beThrown();
    }

    // This method makes erik walk from a random position of frame on to the
    // frame, throw a peice of rubbish and then walk off.
    public void walkToThrow()
    {
        Random randomGenerator = new Random();

        int xTarget;
        int yTarget;
        MovementDetails details = new MovementDetails();

        switch( randomGenerator.nextInt() % 3 )
        {
            case 0:
                yTarget = 335 + Math.abs(randomGenerator.nextInt() % 91 );
                setLoadedImage( 1, 0, -100, yTarget );
                details.addEntry(000, yTarget, 15);
                addThrow( details );
                details.addEntry( -100, yTarget, 20 );
                offDir = 3;
                break;
            case 1:
                xTarget = 700;
                yTarget = 195 + Math.abs(randomGenerator.nextInt() % 100 );
                setLoadedImage( 1, 0, 830, yTarget );
                details.addEntry(700, yTarget, 15);
                addThrow( details );
                details.addEntry( 830, yTarget, 20 );
                offDir = 1;
                break;
            case 2:
            default:
                xTarget = 230 + Math.abs(randomGenerator.nextInt() % 340);//570
                yTarget = 500;
                setLoadedImage( 1, 0, xTarget, 600 );
                details.addEntry(xTarget, 485, 15);
                addThrow( details );
                details.addEntry( xTarget, 600, 20 );
                offDir = 2;
                break;
        }
        play.getIan().erikWalking(offDir);
        moveObject( details );
    }

    private void addThrow( MovementDetails details )
    {
        details.addEntry( 250 );
        details.addEntry( this );
        details.addEntry( 250 );
    }
}
