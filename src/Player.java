import java.util.ArrayList;
import java.util.Iterator;

public class Player implements Iterable<Piece>{

    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    private Player opponent;

    private Piece king;

    public void addPiece(Piece piece)
    {
        pieces.add(piece);
        if(piece instanceof BlackKing || piece instanceof WhiteKing)
            king = piece;
    }

    public Iterator<Piece> iterator()
    {
        return pieces.iterator();
    }

    public void setOpponent(Player opponent)
    {
        this.opponent = opponent;
    }

    public Player getOpponent()
    {
        return opponent;
    }

    public Piece getKing()
    {
        return king;
    }

    public void updateAlive()
    {
        Iterator<Piece> it = pieces.iterator();

        while(it.hasNext())
        {
            Piece i = it.next();
            if(i.getPos() == null)
                it.remove();
        }

    }

    public boolean containsPiece(Piece piece)
    {
        return pieces.contains(piece);
    }



    public boolean isMove(Position start, Position target)
    {
        if(!pieces.contains(start.getPiece())) return false;

        if(start.getPiece().isMove(target))
        {
            Piece startPiece = start.getPiece();
            Piece targetPiece = target.getPiece();
            ChessController.movePiece(start, target);
            ChessController.updateView();
            boolean kingChecked = kingChecked();

            start.setPiece(startPiece);
            try{
            startPiece.setPos(start);} catch(NullPointerException e) {
            }


            target.setPiece(targetPiece);
            try{
                targetPiece.setPos(target);} catch(NullPointerException e){}


            return !kingChecked;
        }
        return false;
    }

    public boolean kingChecked()
    {
        for(Piece p : opponent)
        {

            if(p.getPos()!= null && p.isMove(king.getPos()))
                return true;
        }
        return false;
    }




}
