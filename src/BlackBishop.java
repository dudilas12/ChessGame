public class BlackBishop extends Piece implements Black {
    BlackBishop(Position pos)
    {
        super(pos);
    }



    public boolean isMove(Position pos)
    {

        if(pos.getPiece() instanceof Black) return false;

        if(Math.abs(super.pos.getX() - pos.getX()) == Math.abs(super.pos.getY() - pos.getY())) {

            int i = super.pos.getX();
            int j = super.pos.getY();
            int sub_i = i > pos.getX()? -1 : 1;
            int sub_j = j > pos.getY()? -1 : 1;

            i+= sub_i;
            j+= sub_j;

            while((i != pos.getX()))
            {
                if(ChessController.grid[i][j].getPiece() != null){
                    System.out.println(i + "" + j);
                    return false;}
                i += sub_i;
                j+= sub_j;
            }

            return true;
        }

        return false;


    }

    public String toString()
    {
        return "blackBishop";
    }
}
