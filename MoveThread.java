import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

public class MoveThread extends Thread
{
    private MovementDetails details;
    private Movable object;
    private int curX;
    private int curY;
    private int curSpeed;
    private Boolean pageBagging = false;

    MoveThread( Movable object )
    {
        this.details = details;
        this.object = object;
    }

    public void addDetails( MovementDetails details )
    {
        this.details = details;
    }

    public void run()
    {
        updateCurs();
        if ( !object.getStopForCleaner() )
            move();
        return;
    }

    private void updateCurs()
    {
        curX = details.getCurX();
        curY = details.getCurY();
        curSpeed = details.getCurSpeed();
    }

    private void move()
    {
        while ( object.isMoving() )
        {
            if ( object.updateMove(curX, curY, curSpeed, details) )
            {
                if ( details.isMore() )
                {
                    details.nextIndex();
                    if ( details.getCurType().equals(MovementType.MoveToTarget) )
                        updateCurs();
                    else if ( details.getCurType()  == MovementType.Pause )
                        pause( details.getCurPause() );
                    else if ( details.getCurType() == MovementType.EveryoneStop )
                        details.everyoneStop();
                    else if (details.getCurType() == MovementType.ThrowRubbish)
                        details.runThrowRubbish();
                    else if ( details.getCurType() == MovementType.RubbishAtIan )
                    {
                        if ( ( object.getClass().getName() ).equals("Rubbish"))
                            details.rubbishAtIan();
                        else
                            throw new Error( "Object should be Rubbish but"
                                            + "isnt, it is "
                                            + object.getClass().getName() );
                    }
                    else if ( details.getCurType() == MovementType.RubbishAtBin )
                        details.rubbishAtBin();
                    else if ( details.getCurType() == MovementType.StartToBag )
                    {
                        pageBagging = true;
                        details.startToBag();
                    }
                    else if ( details.getCurType() == MovementType.ReturnWithoutBag )
                    {
                        pageBagging = true;
                        details.returnWithoutBag();
                    }
                    else if ( details.getCurType() == MovementType.PageSitDown )
                        details.pageSitDown();
                    else if ( details.getCurType() == MovementType.DisplayLoseScreen )
                        details.displayLoseScreen();

                }else{
                    if ( !pageBagging )
                        object.setMoving( false );

                    if ( details.getFinalDir() != -1 )
                        object.finalDir( details.getFinalDir() );
                    return;
                }
            }

            pause( 50 );
        }
    }

    private void pause( int time )
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Movable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
