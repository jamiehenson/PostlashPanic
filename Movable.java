import java.net.*;

public class Movable extends ImageDisplay
{
    protected Boolean moving = false;
    protected Boolean stopForCleaner = false;
    private MoveThread moveThread;
    private int xLastDir, yLastDir;

    public void makeVisible()
    {
        setVisible( (Boolean)true );
    }

    public void makeInvisible()
    {
        setVisible( (Boolean)false );
    }

    public void placeObject( int x, int y )
    {
        xPos = x;
        yPos = y;
        xLastDir = 0;
        yLastDir = 0;
        makeTheMove();
    }

    public void moveObject( MovementDetails details )
    {
        if ( !isMoving() )
        {
            moving = true;
            moveThread = new MoveThread( this );
            moveThread.addDetails( details );
            ( moveThread ).start();
        }
    }

    public Boolean updateMove( int xTarget, int yTarget, int speed, MovementDetails details )
    {
        int xDis = xTarget - xPos;
        int yDis = yTarget - yPos;

        double vLength = Math.sqrt( xDis * xDis + yDis * yDis );

        double xVec = xDis / vLength;
        double yVec = yDis / vLength;

        int xNew = xPos + (int)(xVec * speed);
        int yNew = yPos + (int)(yVec * speed);


        if ( willCross(xTarget, xPos, xNew) && willCross(yTarget,yPos,yNew) )
        {
            xPos = xTarget;
            yPos = yTarget;
            makeTheMove();
            stopCon();
            return true;
        }

         xPos =(int) (xPos + xVec * speed);
        yPos += yVec * speed;
        updateDir( calcDir( xVec, yVec) );
        makeTheMove();

        return false;
    }

    //methods overwritten by MovableWithDir
    void updateDir( int dir ) {}
    void stopCon() {}
    void finalDir( int dir ) {};

    public int calcDir( double x, double y )
    {
        if ( Math.abs(x) > Math.abs(y) )
        {
            if ( x > 0 )
                return 1;
            else
                return 3;
        } else {
            if ( y > 0 )
                return 2;
            else
                return 0;
        }
    }

    private Boolean willCross( int target, int cur, int newC )
    {
        if ( cur > target )
        {
            if ( target >= newC )
                return true;
        }else{
            if ( newC >= target )
                return true;
        }

        return false;
    }

    public Boolean isMoving()
    {
        return moving;
    }

    protected void setMoving( Boolean newMoving )
    {
        moving = newMoving;
    }

    public Boolean getVisible()
    {
        return visible;
    }

    public void setVisible( Boolean visible )
    {
        this.visible = visible;
    }

    public Boolean getStopForCleaner()
    {
        return stopForCleaner;
    }
}
