public class BinFull extends Movable {


    public BinFull(int x, int y)
    {
        xPos = x;
        yPos = y;
        setImage("img/UI/bintop.png", x, y);
        setSize(0,0);
    }

    // If the bin is designated as full, then display the "full" graphic at its
    // right size.
    public void RightSize()
    {
        setImageSize();
    }

    // Hide the "full" graphic
    public void Hide()
    {
        setSize(0,0);
    }

}