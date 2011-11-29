import java.net.*;
import java.util.*;
import java.awt.Image;


public class Speech extends Movable
{
    private List<String> queue = new ArrayList<String>();
    private Image[] speechImages = new Image[2];
    private Boolean speechOn = false;
    private SpeechThread thethread;

    public void addToQueue( int x, int y, String name )
    {
        if ( !speechOn )
        {
            myimage = grabSpeech( name );
            xPos = x;
            yPos = y;
            setImageSize();
            makeTheMove();
            thethread = new SpeechThread( this );
            thethread.start();
        }
    }

    public void nextSpeech()
    {
        this.setSize( 0, 0 );
    }

    public void SpeechVisibility( Boolean vis )
    {

    }

    private Image grabSpeech( String name )
    {
        String url = "img/UI/" + name + ".png";
        return readImage( url );
    }
}
