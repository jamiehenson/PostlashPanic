import java.net.*;
import java.util.Random;

public class Rubbish extends Movable
{
    private int type;
    private int binGoingTo;
    private int count;
    private Play play;
    private int masterIndex;

    private String[][] names = {{"banana", "burger", "doughnut", "fish"},
                                {"bottle1", "bottle2", "bottle3", "water"},
                                {"can", "can2", "soup", "spam"},
                                {"flyer", "mag", "paper", "winner"},
                                {"glasses", "hat", "hat", "puppy", "netbook", "oscar"}};
    Rubbish( Play obj )
    {
        play = obj;
    }

    public void beThrown()
    {
        Random generator = new Random();
        type = Math.abs(generator.nextInt() % names.length);
        String name =  names[type][Math.abs(generator.nextInt() % names[type].length)];
        myimage = readImage( "img/projectiles/" + name + ".png" );

        switch ( play.getErik().getOffDir() )
        {
            case 1:
                xPos = 800;
                yPos = play.getErik().getY() - 30;
                break;
            case 2:
                xPos = play.getErik().getX() + 80;
                yPos = 600;
                break;
            case 3:
            default:
                xPos = -100;
                yPos = play.getErik().getY() - 30;
                break;
        }

        setImageSize();
        makeTheMove();

        MovementDetails details = new MovementDetails();

        details.addEntry( 370, 410, 50 );
        details.addEntry( play.getIan() );

        moveObject( details );
    }

    public void atBin()
    {
        this.setSize( 0, 0 );
        play.getIan().lookAtErik();
        play.getMaster().removeIan( masterIndex );

        if ( binGoingTo != type )
        {
            play.resetCombo();
            play.getPage().wrongBinSpeech();
        }else
            play.addPoints();

        if ( binGoingTo != 4 )
            play.getOverflow().addtobin(binGoingTo);
    }

    public void make( String type, int x, int y )
    {
        setImage( "img/projectiles/"+type+".png", x, y );
        setImageSize();
        makeTheMove();
    }

    public int getType()
    {
        return type;
    }

    public void sendingToBin( int bin )
    {
        binGoingTo = bin;
    }

    public void setMasterIndex( int index )
    {
        masterIndex = index;
    }

    public int getMasterIndex()
    {
        return masterIndex;
    }
}
