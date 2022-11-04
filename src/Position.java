import javafx.scene.control.Button;

public class Position extends Button {
    private int x,y;
    private Piece piece;

    Position(int x, int y)
    {
        this.x = x;
        this.y = y;
        piece = null;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Piece getPiece()
    {
        return this.piece;
    }

    public void setPiece(Piece p)
    {
        this.piece = p;
    }
}
