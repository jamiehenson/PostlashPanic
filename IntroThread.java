import java.util.logging.Level;
import java.net.*;
import java.util.logging.Logger;

public class IntroThread extends Thread
{
    private Erik erik;
    private Ian ian;
    private Play play;
    private Speech speech;

    IntroThread( Erik e, Ian i, Speech s, Play p )
    {
        erik = e;
        ian = i;
        speech = s;
        play = p;
    }

    public void run()
    {
        MovementDetails details;

        pause( 1000 );
        ian.setImage(1, 65, 320);
        pause( 700 );
        speech.addToQueue(160, 230, "speechleft1");
        pause( 1000 );
        erik.setImage( 3, 580, 440 );
        pause( 1500 );
        speech.addToQueue(430, 350, "speechright2");
        pause( 2000 );
        details = new MovementDetails();
        details.addEntry(430,440, 15);//430, 360//580, 440
        details.addEntry(430, 360, 15);
        details.addFinalDir(3);
        erik.moveObject( details );
        pause( 1500 );
        //speech.addToQueue(430, 350, "speechright2");
        //pause( 2500 );
        speech.addToQueue(270, 270, "speechrightsheen1");
        pause( 2500 );
        speech.addToQueue(140, 380, "speechleftsheen1");
        pause( 1000 );
        speech.addToQueue(270, 270, "speechrightsheen2");
        pause( 4500 );
        speech.addToQueue(160, 230, "speechleft2");
        pause( 2500 );
        speech.addToQueue(140, 380, "speechleft3");
        pause( 500 );
        ian.setImage(2, 65, 320);
        pause( 2000 );
        speech.addToQueue(270, 270, "speechright3");
        pause( 500 );
        ian.setImage(1, 65, 320);
        pause( 2000 );
        speech.addToQueue(270, 270, "speechright4");
        pause( 2000 );
        speech.addToQueue(160, 230, "speechleft4");
        pause( 2000 );
        details = new MovementDetails();
        details.addEntry(430, 650, 15);
        details.addFinalDir(3);
        erik.moveObject( details );
        pause( 1500 );
        details = new MovementDetails();
        details.addEntry(370, 320, 15);
        details.addEntry(370, 360, 15);
        details.addFinalDir(2);
        ian.moveObject( details );
        pause( 2000 );
        
        for ( int i = 0; i < 3; i++ )
        {
            ian.changeImage(2, 1);
            pause( 200 );
            ian.changeImage(2, 2);
            pause( 200 );
        }

        speech.addToQueue(140, 380, "speechleft6");
        pause(500);
        ian.setImage(3, 370, 360);
        pause(2500);
        ian.setImage(2, 370, 360);

        speech.addToQueue(450, 250, "speechleft7");

        for ( int i = 0; i < 3; i++ )
        {
            ian.makeHench(0);
            pause(400);
            ian.makeHench(1);
            pause(400);
        }
        speech.addToQueue(450, 250, "speechleftnone");
        for ( int i = 0; i < 5; i++ )
        {
            ian.makeHench(2);
            pause(200);
            ian.makeHench(3);
            pause(200);
        }


        erik.thethread.start();
        play.startTiming();

        /*
         * //setImage( 0, 370, 360 );Original

        setImage(0, 65, 320);
         */
    }

    private void pause( int time )
    {
       try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(ErikThread.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
