import java.util.ArrayList;
import java.net.*;

public class MovementDetails
{
    private ArrayList<MovementType> typeList;
    private ArrayList<Integer> xList;
    private ArrayList<Integer> yList;
    private ArrayList<Integer> speedList;
    private ArrayList<Integer> pauseTimeList;
    private Erik erik;
    private Ian ian;
    private Rubbish rubbish;
    private Page page;
    private EndGame endgame;
    private int curIndex;
    private int totalEntries;
    private int finalDir = -1;
    Boolean repeat;

    MovementDetails()
    {
        typeList = new ArrayList<MovementType>(3);
        xList = new ArrayList<Integer>(3);
        yList = new ArrayList<Integer>(3);
        speedList = new ArrayList<Integer>(3);
        pauseTimeList = new ArrayList<Integer>(3);
        totalEntries = 0;
        curIndex = 0;
        repeat = false;
    }

    public void addEntry( int x, int y, int speed )
    {
        typeList.add( MovementType.MoveToTarget );
        xList.add( x );
        yList.add( y );
        speedList.add( speed );
        pauseTimeList.add( null );
        totalEntries++;
    }

    public void addEntry( int pauseTime )
    {
        typeList.add( MovementType.Pause );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( pauseTime );
        totalEntries++;
    }

    public void addEntry( Erik obj )
    {
        typeList.add( MovementType.ThrowRubbish );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( null );
        erik = obj;
        totalEntries++;
    }

    public void addEntry( Ian obj )
    {
        typeList.add( MovementType.RubbishAtIan );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( null );
        ian = obj;
        totalEntries++;
    }

    public void addEntry( Rubbish obj )
    {
        typeList.add( MovementType.RubbishAtBin );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( null );
        rubbish = obj;
        totalEntries++;
    }

    public void addStartToBag( Page obj )
    {
        typeList.add( MovementType.StartToBag );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( null );
        page = obj;
        totalEntries++;
    }

    public void addReturnWithoutBag( Page obj )
    {
        typeList.add( MovementType.ReturnWithoutBag );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( null );
        page = obj;
        totalEntries++;
    }

    public void addPageSitDown( Page obj )
    {
        typeList.add( MovementType.PageSitDown );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( null );
        page = obj;
        totalEntries++;
    }

    public void addEveryoneStop( EndGame obj )
    {
        typeList.add( MovementType.EveryoneStop );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( null );
        endgame = obj;
        totalEntries++;
    }

    public void addDisplayLoseScreen( EndGame obj )
    {
        typeList.add( MovementType.DisplayLoseScreen );
        xList.add( null );
        yList.add( null );
        speedList.add( null );
        pauseTimeList.add( null );
        endgame = obj;
        totalEntries++;
    }

    public void addFinalDir( int dir )
    {
        finalDir = dir;
    }

    public int getFinalDir()
    {
        return finalDir;
    }

    public void restartIndex()
    {
        curIndex = 0;
    }

    public Boolean isMore()
    {
        return ( curIndex < totalEntries - 1 );
    }

    public void nextIndex()
    {
        curIndex++;
    }

    public MovementType getCurType()
    {
        return typeList.get( curIndex );
    }

    public int getCurX()
    {
        return xList.get( curIndex );
    }

    public int getCurY()
    {
        return yList.get( curIndex );
    }

    public int getCurSpeed()
    {
        return speedList.get( curIndex );
    }

    public int getCurPause()
    {
        return pauseTimeList.get( curIndex );
    }

    public void runThrowRubbish()
    {
        erik.throwRubbish();
    }

    public void rubbishAtIan()
    {
        ian.rubbishAtIan();
    }

    public void rubbishAtBin()
    {
        rubbish.atBin();
    }

    public void startToBag()
    {
        page.startToBag();
    }

    public void returnWithoutBag()
    {
        page.returnWithoutBag();
    }

    public void pageSitDown()
    {
        page.pageSitDown();
    }

    public void everyoneStop()
    {
        endgame.everyoneStop();
    }

    public void displayLoseScreen()
    {
        endgame.displayLoseScreen();
    }
}


