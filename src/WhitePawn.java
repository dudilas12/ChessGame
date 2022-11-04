public class WhitePawn extends Piece implements White {

    WhitePawn(Position pos)
    {
        super(pos);
    }


    public boolean isMove(Position pos)
    {

        if(super.pos.getX() == pos.getX() -1 && super.pos.getY() == pos.getY() && pos.getPiece() == null)
            return true;
        else if(super.pos.getX() == pos.getX() -1 && Math.abs(super.pos.getY() - pos.getY()) == 1 && pos.getPiece() instanceof Black)
            return true;
        return false;


    }

    public String toString()
    {
        return "whitePawn";
    }
}
