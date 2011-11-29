import java.awt.Component.*;
import javax.swing.InputMap.*;
import javax.swing.ActionMap.*;
import javax.swing.*;
import java.net.*;

public class Bin
{
    // How much the bin can hold
    private int threshold = 5;

    // Initialisers for enumerating the bins
    private int bintotal = 0;
    private int[] bincounts = new int[4];
    private BinFull[] binfull = new BinFull[4];
    private Play play;
    private JFrame frame;


    public Bin( Play obj, JFrame w)
    {
        // Position the bins and set them as empty
        binfull[0] = new BinFull(183,215);
        binfull[1] = new BinFull(300,218);
        binfull[2] = new BinFull(415,215);
        binfull[3] = new BinFull(530,215);
        bincounts[0] = 0;
        bincounts[1] = 0;
        bincounts[2] = 0;
        bincounts[3] = 0;

        play = obj;
        frame = w;
        w.add( binfull[0] );
        w.add( binfull[1] );
        w.add( binfull[2] );
        w.add( binfull[3] );
    }

    public void zOrder( int order)
    {
        // Set the visual depth layering of the bins
        frame.setComponentZOrder( binfull[0], order );
        frame.setComponentZOrder( binfull[1], order+1 );
        frame.setComponentZOrder( binfull[2], order+2 );
        frame.setComponentZOrder( binfull[3], order+3 );
    }

    // If any of the bins' contents are under the threshold, then increment
    // the contents variable and the total count
    public void addtobin(int bintype)
    {
        if (bincounts[bintype] < threshold)
        {
            bintotal++;
            bincounts[bintype]++;
            play.takeRubbish(1);
        }
        else  if (bincounts[bintype] == threshold)
        {
            binfull[bintype].RightSize();
            bintotal++;
            bincounts[bintype]++;
            play.takeRubbish(1);
        }
        else
        {
            play.getExplosion().makeExplosion(bintype);
            emptybin(bintype);
            play.takeRubbish(-1 * (threshold + 2 ));
        }
    }

    // Reset the bins' contents to 0, and hide the "full" graphic
    public void emptybin(int bintype)
    {
        bincounts[bintype] = 0;
        binfull[bintype].Hide();
    }

    // Determines whether or not the bins' contents have exceeded the threshold.
    public Boolean isBinFull( int bin )
    {
        if ( bincounts[bin] >= threshold + 1 )
            return true;
        else
            return false;
    }
}