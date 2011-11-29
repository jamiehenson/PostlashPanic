import java.net.*;

public class RubbishMaster
{
    private Rubbish rubbishErik;
    private Rubbish rubbishHolding;
    private Rubbish rubbishIan1;
    private Rubbish rubbishIan2;
    private Rubbish rubbishIdle1;
    private Rubbish rubbishIdle2;
    private Rubbish rubbishIdle3;

    RubbishMaster( Rubbish obj1, Rubbish obj2, Rubbish obj3 )
    {
        rubbishErik = null;
        rubbishIan1 = null;
        rubbishIan2 = null;
        rubbishIdle1 = obj1;
        rubbishIdle2 = obj2;
        rubbishIdle3 = obj2;
    }

    public void newErik()
    {
        if ( rubbishIdle1 == null )
            throw new Error("Run out of Rubbish objects");

        if ( rubbishErik != null )
            return;
            //throw new Error("Already rubbishErik element");

        rubbishErik = rubbishIdle1;
        rubbishIdle1 = rubbishIdle2;
        rubbishIdle2 = rubbishIdle3;
        rubbishIdle3 = null;
    }

    public void newHolding()
    {
        if ( rubbishHolding != null )
            throw new Error("Holding already has a rubbish");

        if ( rubbishErik == null )
            throw new Error("Nothing to move to holdingRubbish");

        rubbishHolding = rubbishErik;
        rubbishErik = null;
    }

    public void newIan()
    {
        if ( rubbishHolding == null )
            throw new Error("Nothing to move to holdingRubbish");

        if ( rubbishIan1 == null )
        {
            rubbishHolding.setMasterIndex(1);
            rubbishIan1 = rubbishHolding;
        }
        else if ( rubbishIan2 == null )
        {
            rubbishHolding.setMasterIndex(2);
            rubbishIan2 = rubbishHolding;
        }
        else
        {
            // This can happen occationly - just deal with
            throw new Error("Ian Already has a rubbish");
        }

        rubbishHolding = null;
    }



    public void removeErik()
    {
        addToIdle( rubbishErik );
        rubbishErik.setSize(0,0);
        rubbishErik = null;
        rubbishHolding.setImageSize();
        rubbishHolding.makeTheMove();
    }

    public void removeIan( int index )
    {
        if ( index == 1 )
        {
            addToIdle( rubbishIan1 );
            rubbishIan1.setSize( 0, 0 );
            rubbishIan1 = null;
        }
        else
        {
            addToIdle( rubbishIan2 );
            rubbishIan2.setSize( 0, 0 );
            rubbishIan2 = null;
        }
    }

    private void addToIdle( Rubbish newIdle )
    {
        if ( rubbishIdle1 == null )
            rubbishIdle1 = newIdle;
        else if ( rubbishIdle2 == null )
            rubbishIdle2 = newIdle;
        else if ( rubbishIdle3 == null )
            rubbishIdle3 = newIdle;
        else
            throw new Error("No space to put new idle in");
    }

    

    public Rubbish getErik()
    {
        return rubbishErik;
    }

    public Rubbish getHolding()
    {
        return rubbishHolding;
    }
}
