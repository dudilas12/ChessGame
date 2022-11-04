public class WhiteRook extends Piece implements White{

    WhiteRook(Position pos)
    {
        super(pos);
    }


    public boolean isMove(Position pos)
    {

        if(pos.getPiece() instanceof White) return false;

        if(super.pos.getX() == pos.getX()) {
            for(int j = Math.min(super.pos.getY(), pos.getY()) +1; j <= Math.max(super.pos.getY(), pos.getY()) -1; j++)
                if(ChessController.grid[pos.getX()][j].getPiece() != null)
                {
                    System.out.println(j);
                    return false;}

        }

        if(super.pos.getY() == pos.getY()) {
            for(int i = Math.min(super.pos.getX(), pos.getX()) +1;i <= Math.max(super.pos.getX(), pos.getX()) -1; i++)
                if(ChessController.grid[i][pos.getY()].getPiece() != null)
                    return false;

        }

        return super.pos.getX() == pos.getX() || super.pos.getY() == pos.getY();


    }

    public String toString()
    {
        return "whiteRook";
    }
}
