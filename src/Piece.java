
/*
Piece class for representing each piece on the board
all specific pieces are extending from this class
 */
public abstract class Piece {
    protected Position pos;


    Piece(Position pos)
    {
        this.pos = pos;

    }

    public Position getPos()
    {
        return pos;
    }



    public void setPos(Position pos)
    {
        this.pos = pos;
    }

    public abstract boolean isMove(Position pos);
}