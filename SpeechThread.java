import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpeechThread extends Thread
{
    private Speech speech;

    SpeechThread( Speech obj )
    {
        speech = obj;
    }

    public void run()
    {
        pause( 2000 );
        speech.nextSpeech();
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
