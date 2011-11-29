import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.net.*;

public class MovableWithDir extends Movable
{
    private Image[][] images = new Image[4][3];
    private ConThread thethread;
    protected String[] dirToString = {"back", "right", "front", "left"};
    protected int curMovement = 0;
    protected int lastCurMovement;
    protected int lastDir = 5;
    protected int curDir = 1;

    void updateDir( int dir )
    {
        if ( dir != lastDir )
        {
            lastDir = dir;
            curDir = dir;
            if ( moving && thethread != null)
                thethread.endThread();

            thethread = new ConThread( dir, "erik", this, curMovement );
            thethread.start();

            //changeImage( dir, 0 );
        }
    }

    protected void changeImage( int dir, int pos )
    {
        myimage = images[dir][pos];
        setImageSize();
        makeTheMove();
    }

    public void stopCon()
    {
        if ( thethread != null )
            thethread.endThread();
        
        lastDir = 5;
        changeImage( curDir, 0 );
    }

    public void loadImages( String name )
    {
        for ( int i = 0; i < 4; i++ )
            for ( int j = 0; j < 3; j++ )
                images[i][j] = grabImage( name, i, j );
    }

    public void setImage( int dir, int x, int y )
    {
        xPos = x;
        yPos = y;
        myimage = images[dir][0];
        setImageSize();
        makeTheMove();
    }

    public String makeURL( String name, int dir, int num )
    {
        if ( num == 0 )
            return "img/" + name + "/" + name + dirName(dir) + "idle.png";
        else
            return "img/" + name + "/" + name + dirName(dir) + (num+1) + ".png";
    }

    public Image grabImage( String name, int dir, int num )
    {
        String url = makeURL( name, dir, num );
        return readImage(url);
    }

    public String dirName( int dir )
    {
        return dirToString[dir];
    }

    public void finalDir( int dir )
    {
        if ( dir != -1 )
        {
            changeImage( dir, 0 );
            makeTheMove();
        }
    }

    public void setLoadedImage( int pos, int dir, int x, int y )
    {
        xPos = x;
        yPos = y;
        myimage = images[pos][dir];
        makeTheMove();
    }

    public void setImages( Image[][] img )
    {
        images = img;
    }

    public Image[][] getImages()
    {
        return images;
    }

    public void lookInDir( int x, int y )
    {

        int xVec = x - xPos;
        int yVec = y - yPos;
        int dir = calcDir( xVec, yVec );
        myimage = images[dir][1];
        moving = false;
    }

    public void StopForCleaner( int x, int y )
    {
        setMoving( false );
        stopForCleaner = true;
        lookInDir( x, y );
    }

    public void LookAtUser()
    {
        setMoving( false );
        stopForCleaner = true;
        setImage( 2, xPos, yPos );
    }
}
