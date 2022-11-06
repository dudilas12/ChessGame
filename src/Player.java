import java.util.ArrayList;
import java.util.Iterator;

public class Player {

    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    public void addPiece(Piece piece)
    {
        pieces.add(piece);
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

        return start.getPiece().isMove(target);
    }




}
