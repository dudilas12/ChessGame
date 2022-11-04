public class WhiteQueen extends Piece implements White {

    WhiteQueen(Position pos)
    {
        super(pos);
    }


    public boolean isMove(Position pos)
    {

        Piece rook_sim = new WhiteRook(super.pos);
        Piece bishop_sim = new WhiteBishop(super.pos);

        return rook_sim.isMove(pos) || bishop_sim.isMove(pos);




    }

    public String toString()
    {
        return "whiteQueen";
    }
}