import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ChessController {

    @FXML
    private GridPane board;

    @FXML
    private VBox vb;

    final static int BOARD_X = 8;
    final static int BOARD_Y = 8;
    public static Position[][] grid = new Position[BOARD_X][BOARD_Y];

    Player whitePlayer = new Player();

    Player blackPlayer = new Player();




    public void initialize() {
        double height = vb.getPrefHeight();
        double width = vb.getPrefWidth();



        for(int i = 0; i < BOARD_X ; i++)
            for(int j = 0; j<BOARD_Y; j++)
            {
                Position pos = new Position(i,j);
                pos.setPrefWidth(width/BOARD_Y);
                pos.setPrefHeight(height/BOARD_X);

                pos.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        handlePiece(actionEvent);

                    }
                });



                pos.setStyle("-fx-border-color: black;");
                board.add(pos, j, BOARD_X -i);
                grid[i][j] = pos;
            }


        /*-----------setting up the board--------- */

        //pawns
        for(int i = 0; i < BOARD_X ; i++)
            grid[1][i].setPiece(new WhitePawn(grid[1][i]));
        for(int i = 0; i < BOARD_X ; i++)
            grid[BOARD_X -2][i].setPiece(new BlackPawn(grid[BOARD_X -2][i]));

        //rooks
        grid[0][0].setPiece(new WhiteRook(grid[0][0]));
        grid[0][BOARD_Y-1].setPiece(new WhiteRook(grid[0][BOARD_Y-1]));
        grid[BOARD_X -1][0].setPiece(new BlackRook(grid[BOARD_X -1][0]));
        grid[BOARD_X -1][BOARD_Y-1].setPiece(new BlackRook(grid[BOARD_X -1][BOARD_Y-1]));

        //knights
        grid[0][1].setPiece(new WhiteKnight(grid[0][1]));
        grid[0][BOARD_Y-2].setPiece(new WhiteKnight(grid[0][BOARD_Y-2]));
        grid[BOARD_X -1][1].setPiece(new BlackKnight(grid[BOARD_X -1][1]));
        grid[BOARD_X -1][BOARD_Y-2].setPiece(new BlackKnight(grid[BOARD_X -1][BOARD_Y-2]));

        //bishops
        grid[0][2].setPiece(new WhiteBishop(grid[0][2]));
        grid[0][BOARD_Y-3].setPiece(new WhiteBishop(grid[0][BOARD_Y-3]));
        grid[BOARD_X -1][2].setPiece(new BlackBishop(grid[BOARD_X -1][2]));
        grid[BOARD_X -1][BOARD_Y-3].setPiece(new BlackBishop(grid[BOARD_X -1][BOARD_Y-3]));

        //queens
        grid[0][3].setPiece(new WhiteQueen(grid[0][3]));
        grid[BOARD_X -1][3].setPiece(new BlackQueen(grid[BOARD_X -1][3]));

        //kings
        grid[0][4].setPiece(new WhiteKing(grid[0][4]));
        grid[BOARD_X -1][4].setPiece(new BlackKing(grid[BOARD_X -1][4]));


        updatePlayers();

        updateView();






    }

    private Position start = null;
    private Position target = null;

    private Position errorStart = null;
    private Position errorTarget = null;
    private boolean isError = false;

    Player currentPlayer = whitePlayer;

    private int errorIndex = 0;

    public void handlePiece(ActionEvent actionEvent)
    {


        Position pos = (Position)actionEvent.getSource();

        if(start == null){
            if(isError)
            {
                endError(errorIndex);

            }
            if(!currentPlayer.containsPiece(pos.getPiece())) return;
            pos.setStyle("-fx-border-color: black; -fx-border-width: 5px");
            start = pos;
        }
        else
        {
            target = pos;
            if(start == target) return;
            else if(!currentPlayer.isMove(start, target))
                error();
            else
            {
                movePiece(start,target);
                currentPlayer = (currentPlayer == whitePlayer ? blackPlayer : whitePlayer);

            }

            start = null;

        }






    }

    public void error()
    {
        isError = true;
        errorStart = start;
        errorTarget = target;
        errorStart.setStyle("-fx-border-color: red; -fx-border-width: 5px");
        errorTarget.setStyle("-fx-border-color: red; -fx-border-width: 5px");

        int currentErrorIndex = errorIndex;
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    endError(currentErrorIndex);
                }
                )

        );

        timeline.play();
    }

    public void endError(int errorIndex)
    {
        if(!isError || errorIndex < this.errorIndex) return;
        isError = false;

        errorStart.setStyle("-fx-border-color: black;");
        errorTarget.setStyle("-fx-border-color: black;");
        this.errorIndex ++;
    }

    public void updateView()
    {
        for(int i = 0; i < BOARD_X ; i++)
            for(int j = 0; j<BOARD_Y; j++)
            {
                Image img = null;
                if(grid[i][j].getPiece() != null)
                    img = new Image("C:\\Users\\User\\Dudi\\Projects\\Chess\\img\\" + grid[i][j].getPiece().toString() + ".png");
                ImageView view = new ImageView(img);
                view.setFitHeight(60);
                view.setPreserveRatio(true);
                grid[i][j].setGraphic(view );


            }

    }


    private void movePiece(Position start, Position target)
    {
        Piece moved = start.getPiece();
        moved.setPos(target);
        try{
            target.getPiece().setPos(null);}
        catch(NullPointerException e)
        {

        }
        target.setPiece(moved);
        start.setPiece(null);
        updateView();
        start.setStyle("-fx-border-color: black;");
    }


    private void updatePlayers()
    {
        for(int i = 0; i < BOARD_X ; i++)
            for(int j = 0; j < BOARD_Y ; j++)
            {
                Piece currentPiece = grid[i][j].getPiece();
                if(currentPiece== null) continue;
                else if(currentPiece instanceof White) whitePlayer.addPiece(currentPiece);
                else if(currentPiece instanceof Black) blackPlayer.addPiece(currentPiece);

            }
    }
    public void foo(String s)
    {
        System.out.println(s);
    }


}


