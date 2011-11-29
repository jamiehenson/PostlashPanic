import java.awt.*;

public class Ian extends MovableWithDir
{
    private Boolean holdingRubbish = false;
    private Play play;
    private int spotIndex = 0;
    private Image[] hench = new Image[4];

    Ian( Play obj )
    {
        play = obj;
        loadImages("ian");
        hench[0] = readImage("img/ian/ianguns1.png");
        hench[1] = readImage("img/ian/ianguns2.png");
        hench[2] = readImage("img/ian/ianpump1.png");
        hench[3] = readImage("img/ian/ianpump2.png");

        setImage(0, 65, 320);
        setImageSize();
        makeTheMove();
    }

    public void makeHench( int pos )
    {
        myimage = hench[pos];
        makeTheMove();
    }

    public void erikWalking( int dir )
    {
        changeImage( dir, 0 );
    }

    public void keyPressed( char c )
    {
        
        if ( !holdingRubbish )
            return;

        switch ( c )
        {
            //Throw intoo bin
            case 'a':
            case 'A':
                play.getMaster().getHolding().sendingToBin(0);
                throwRubbish(200, 200);
                break;
            case 's':
            case 'S':
                play.getMaster().getHolding().sendingToBin(1);
                throwRubbish(315, 200);
                break;
            case 'd':
            case 'D':
                play.getMaster().getHolding().sendingToBin(2);
                throwRubbish(425, 200);
                break;
            case 'f':
            case 'F':
                play.getMaster().getHolding().sendingToBin(3);
                throwRubbish(550, 200);
                break;
            case 'g':
            case 'G':
                play.getMaster().getHolding().sendingToBin(4);
                throwRubbish(-200, 410);
                setLoadedImage( 3, 0, 370, 360 );
                break;

            //Send Dan to bin
            case 'q':
            case 'Q':
                play.getPage().callOver(0);
                break;
            case 'w':
            case 'W':
                play.getPage().callOver(1);
                break;
            case 'e':
            case 'E':
                play.getPage().callOver(2);
                break;
            case 'r':
            case 'R':
                play.getPage().callOver(3);
                break;
        }
    }

    public void throwRubbish( int x, int y )
    {
        setLoadedImage( 0, 0, 370, 360 );
        MovementDetails details = new MovementDetails();
        details.addEntry(x, y, 30);
        details.addEntry( play.getMaster().getHolding() );
        play.getMaster().getHolding().moveObject( details );
        play.getMaster().newIan();
        holdingRubbish = false;

    }

    public void rubbishAtIan()
    {
        if ( holdingRubbish )
        {
            play.getSpeech().addToQueue(450, 250, "swear");
            play.getBogdan().noise();
            play.getMaster().removeErik();
        }else{
            holdingRubbish = true;
            play.getMaster().newHolding();
        }
        
    }

    public void lookAtErik()
    {
        erikWalking( play.getErik().getOffDir() );

    }
}
