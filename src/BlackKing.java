public class BlackKing extends Piece implements Black {
    BlackKing(Position pos)
    {
        super(pos);
    }



    public boolean isMove(Position pos)
    {

        if(pos.getPiece() instanceof Black) return false;

        if(Math.abs(super.pos.getX() - pos.getX()) == 1 || Math.abs(super.pos.getY() - pos.getY()) == 1)
            return true;
        return false;


    }

    public String toString()
    {
        return "blackKing";
    }
}
