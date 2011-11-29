import java.awt.*;
import java.net.*;

// This contains the images of Bogdan and all the methods to do with him.
public class Bogdan extends MovableWithDir
{
    private Image[] sleepingImages = new Image[2];
    private Image[][] sleepingImagesStore = new Image[4][2];
    private Thread sleepingThread;
    private BogdanBinThread bogdanbinthread;
    private int sleepingIndex = 0;
    private int noiseCount = 0;
    private Boolean sleeping = true;
    private Play play;
    private int sleepingWait;

    // The constructor loads all the images which will be used. It then sets
    // up the images we want to run first and starts him sleeping.
    Bogdan( Play obj )
    {
        play = obj;

        loadImages("bogdan");
        sleepingImagesStore[0][0] = readImage("img/bogdan/bogdansleep1.png");
        sleepingImagesStore[0][1] = readImage("img/bogdan/bogdansleep2.png");
        sleepingImagesStore[1][0] = readImage("img/bogdan/bogdanangry1a.png");
        sleepingImagesStore[1][1] = readImage("img/bogdan/bogdanangry1b.png");
        sleepingImagesStore[2][0] = readImage("img/bogdan/bogdanangry2a.png");
        sleepingImagesStore[2][1] = readImage("img/bogdan/bogdanangry2b.png");
        sleepingImagesStore[3][0] = readImage("img/bogdan/bogdanangry3a.png");
        sleepingImagesStore[3][1] = readImage("img/bogdan/bogdanangry3b.png");

        startSleeping();

    }

    // This starts Bogdan sleeping. It sets his co-ordinates, the images he will
    // need and then starts the thread which will call the changes to  his
    // images as he sleeps.
    private void startSleeping()
    {
        // Set up co-ordinates
        xPos = 65;
        yPos = 180;

        // Set up images
        setSleepingImages(0);
        myimage = sleepingImages[0];
        
        setImageSize();
        makeTheMove();

        // Start the thread
        sleepingThread = new BogdanThread( this );
        sleepingThread.start();
    }

    // This is called when someone in the game makes a noise. Bogdan doesnt like
    // noises, so it adds one to the counter and takes the appropriate action if
    // the counter is at specific places.
    public void noise()
    {
        // The noises only count is Bogdan is sleeping
        if ( !sleeping )
            return;

        noiseCount++;

        // Change his images to get more angier at a count of 2, 4 and 6.
        if ( noiseCount == 3 )
            setSleepingImages(1);
        else if ( noiseCount == 6 )
            setSleepingImages(2);
        else if ( noiseCount == 9 )
            setSleepingImages(3);

        // If it is 8, he should walk up and knock all the bins over.
        else if ( noiseCount == 12 )
        {
            sleeping = false;
            setLoadedImage(2,0,65,180);
            MovementDetails details = new MovementDetails();
            details.addEntry( 65, 250, 20 );
            details.addEntry( 90, 250, 20 );
            moveObject( details );

            bogdanbinthread = new BogdanBinThread(this, play);
            bogdanbinthread.start();
        }
    }

    // This is called when all the bins have been knocked over. The method makes
    // him walk back to where he was sleeping.
    public void walkBack()
    {
        MovementDetails details = new MovementDetails();
        details.addEntry( 65, 250, 20 );
        details.addEntry( 65, 180, 20 );
        moveObject(details);
    }

    // This is called when he reaches where he sleeps. The method sets
    // everything up for him to sleep and count noises again.

    public void sitBackDown()
    {
        sleepingImages[0] = sleepingImagesStore[0][0];
        sleepingImages[1] = sleepingImagesStore[0][1];
        sleeping = true;
        startSleeping();
        noiseCount = 0;
    }

    // This is called by his sleeping thread. Everytime it is called, Bogdans
    // image flips back and forth between the images 
    public void changeSleepingImage()
    {
        if ( sleepingIndex == 0 )
        {
            myimage = sleepingImages[0];
            sleepingIndex = 1;
        }else{
            myimage = sleepingImages[1];
            sleepingIndex = 0;
        }
        makeTheMove();
    }

    // This is the getter for sleeping.
    public Boolean keepSleeping()
    {
        return sleeping;
    }

    // This returns the image "img/bogdan/bogdan" + name + ".png".
    private Image grabBogdanSleeping( String name )
    {
        String url = "img/bogdan/bogdan" + name + ".png";
        return readImage( url );
    }

    // This changes the images of him sleeping
    private void setSleepingImages( int level )
    {
        sleepingImages[0] = sleepingImagesStore[level][0];
        sleepingImages[1] = sleepingImagesStore[level][1];

        // This is the amount of time which the thread waits between changing
        // the images.
        sleepingWait = ( 4 - level ) * 269;
    }

    // This is the getter for sleepWait.
    public int getSleepingWait()
    {
        return sleepingWait;
    }
}
