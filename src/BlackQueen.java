public class BlackQueen extends Piece implements Black {
    BlackQueen(Position pos)
    {
        super(pos);
    }



    public boolean isMove(Position pos)
    {

        Piece rook_sim = new BlackRook(super.pos);
        Piece bishop_sim = new BlackBishop(super.pos);

        return rook_sim.isMove(pos) || bishop_sim.isMove(pos);


    }

    public String toString()
    {
        return "blackQueen";
    }
}
