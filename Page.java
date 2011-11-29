import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.net.*;

public class Page extends MovableWithDir
{
    private Image[] sittingImages = new Image[2];
    private Image[][] bagImages = new Image[4][3];
    private Image[][] walkingImages = new Image[4][3];
    private Boolean sitting = true;
    private int sittingIndex = 0;
    private Thread sittingThread;
    private Play play;
    private int baggingBin;

    Page( Play obj )
    {
        play = obj;
        sittingImages[0] = grabPageSitting(0);
        sittingImages[1] = grabPageSitting(1);
        loadImages("page");
        loadBagImages();
        startWritting();
    }

    private void startWritting()
    {
        xPos = 45;
        yPos = 445;
        myimage = sittingImages[0];
        setImageSize();
        makeTheMove();

        sittingThread = new PageConThread( this );
        sittingThread.start();
    }

    private void loadBagImages()
    {
        for ( int i = 0; i < 4; i++ )
            for ( int j = 0; j < 3; j++ )
                bagImages[i][j] = grabBagImage( i, j );
    }

    private String makeBagURL( int dir, int num )
    {
        if ( num == 0 )
            return "img/page/pagebag" + dirName(dir) + "idle.png";
        else
            return "img/page/pagebag" + dirName(dir) + (num+1) + ".png";
    }

    private Image grabBagImage( int dir, int num )
    {
        String url = makeBagURL( dir, num );
        URL img = getClass().getResource(url);
        Image file = Toolkit.getDefaultToolkit().getImage(img);
        return file;

        /*
        try
        {
            File file = new File( url );
            return ImageIO.read(file);
        }
        catch (IOException ie) {
            System.err.println(url + " does not exsist");
            return null;
        }
         *
         */
    }

    public void callOver( int bin )
    {
        if ( !sitting )
            return;
            

        if ( !play.getOverflow().isBinFull(bin) )
        {
            play.getSpeech().addToQueue(140, 380, "speechleftnotfull");
            return;
        }

        sitting = false;
        baggingBin = bin;
        setImage( 0, 120, 428 );
        MovementDetails details = new MovementDetails();
        details.addEntry(120, 300, 20);

        switch ( bin )
        {
            case 0:
                details.addEntry(190, 300, 20);
                break;
            case 1:
                details.addEntry(300, 300, 20);
                break;
            case 2:
                details.addEntry(420, 300, 20);
                break;
            case 3:
                details.addEntry(540, 300, 20);
                break;
        }

        details.addEntry(200);
        details.addStartToBag(this);
        details.addFinalDir(0);
        moveObject(details);
    }

    public void changeSittingImage()
    {
        if ( sittingIndex == 0 )
        {
            myimage = sittingImages[0];
            sittingIndex = 1;
        }else{
            myimage = sittingImages[1];
            sittingIndex = 0;
        }
        makeTheMove();
    }

    private Image grabPageSitting( int num )
    {
        String url = "img/page/pagesit" + (num+1) + ".png";
        return readImage( url );
    }

    public Boolean keepSitting()
    {
        return sitting;
    }

    public void wrongBinSpeech()
    {
        play.getBogdan().noise();
        play.getSpeech().addToQueue( 130, 340, "speechleft5" );
    }

    public void startToBag()
    {
        setMoving(false);
        play.getOverflow().emptybin(baggingBin);
        walkingImages = getImages();
        setImages( bagImages );

        MovementDetails details = new MovementDetails();
        details.addEntry(200, 300, 20);
        details.addEntry(200, 800, 20);
        details.addReturnWithoutBag(this);
        moveObject(details);
    }

    public void returnWithoutBag()
    {
        setMoving(false);
        bagImages = getImages();
        setImages(walkingImages);

        MovementDetails details = new MovementDetails();
        details.addEntry(200, 428, 20);
        details.addEntry(120, 428, 20);
        details.addPageSitDown(this);
        moveObject(details);
    }

    public void pageSitDown()
    {
        setMoving(false);
        startWritting();
        sitting = true;
    }
}
