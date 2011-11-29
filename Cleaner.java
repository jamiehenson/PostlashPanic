// This contains the images of the cleaner and all the methods to do with her.
public class Cleaner extends MovableWithDir
{
    // At the start, the images of her are loaded
    Cleaner()
    {
        loadImages("cleaner");
    }

    // This sets her image a location to the ones just before she walks in.
    public void setCleanerUp()
    {
        setImage(1, -100, 335);
    }
}
