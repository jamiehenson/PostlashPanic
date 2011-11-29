import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageDisplay extends JLabel
{
    protected int xPos;
    protected int yPos;
    protected int w;
    protected int h;
    protected Boolean visible = true;
    protected Image myimage;
    private Image dbImage;
    private Graphics dbg;

    public void setImage( String fileName, int x, int y )
    {
        xPos = x;
        yPos = y;

        setOpaque(false);

        myimage = readImage(fileName);

        this.setSize( myimage.getWidth(this), myimage.getHeight(this) );

        makeTheMove();
    }

    public Image readImage( String url )
    {
        try
        {
            return ImageIO.read(getClass().getResource(url));
        }
        catch (IOException ie) {
            System.err.println( url + " not found!" );
            return null;
        }      
    }

    public URL getURL( String name )
    {
        return this.getClass().getResource(name);
    }

    void setImageSize()
    {
        this.setSize( myimage.getWidth(this), myimage.getHeight(this) );
    }

    protected void makeTheMove()
    {
        visible = true;
        this.setLocation( xPos, yPos );
        repaint();
    }

    public int getX()
    {
        return xPos;
    }

    public int getY()
    {
        return yPos;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if ( visible )
            g.drawImage( myimage, 0, 0, null);
    }

    public boolean isOptimizedDrawingEnabled()
    {
        return false;
    }

    public void update (Graphics g)
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
        
        paint (dbg);

        // draw image on the screen
        g.drawImage (dbImage, 0, 0, this);
    }

}
