import java.net.*;
import javax.swing.*;
import java.awt.*;

public class dbJLabel extends JLabel
{
    private Image dbImage;
    private Graphics dbg;

    dbJLabel( String text, int pos )
    {
        super( text, pos );
    }

    public void update(Graphics g)
    {
        // initialize buffer
        if (dbImage == null)
        {
            dbImage = createImage (this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics ();
        }

        // clear screen in background
        dbg.setColor (getBackground ());
        dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

        // draw elements in background

        paint(dbg);

        // draw image on the screen
        g.drawImage (dbImage, 0, 0, this);
    }


}
