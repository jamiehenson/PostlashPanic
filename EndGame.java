// This class contols the image shown at the end.
public class EndGame extends Movable
{
    private Play play;
    private Boolean won = false;

    EndGame( Play obj )
    {
        play = obj;
    }

    // This method shows the winning game screen.
    public void GameWin()
    {
        play.getBogdan().LookAtUser();
        play.getErik().LookAtUser();
        play.getIan().LookAtUser();
        play.setKeepTiming( false );

        setImage("img/UI/gameoverwin.png", 0, 0);
        makeTheMove();
    }

    // This method shows the losing game screen after a timed delay
    public void GameLose()
    {
        play.getCleaner().setCleanerUp();
        MovementDetails details = new MovementDetails();
        details.addEntry(0, 335, 20);
        details.addEntry( 1000 );
        details.addEveryoneStop( this );
        details.addEntry( 1000 );
        details.addDisplayLoseScreen( this );
        play.getCleaner().moveObject( details );
    }

    // This method stops Bogdan, Erik, Ian and Dan Page moving after a delay
    public void everyoneStop()
    {
        play.getBogdan().StopForCleaner( 44, 382 );
        play.getErik().StopForCleaner( 44, 382 );
        play.getIan().StopForCleaner( 44, 382 );
        play.getPage().StopForCleaner( 44, 382 );
    }

    // After the delay from GameLose() this method is called and shows the
    // lose screen.
    public void displayLoseScreen()
    {
        setImage("img/UI/gameoverlose.png", 0, 0);
        makeTheMove();
    }
}
