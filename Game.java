import java.awt.*;
import java.util.ArrayList;

public class Game {
    public Game(){}

    public static  void main(String [] args){
        ChessBoard game  = ChessBoard.getInstance();
        game.boardINIT(game);
        game.chessPiecesINIT(game);

        ArrayList<ChessTile> tiles = new ArrayList<>();



        game.getBoard().setTilesCircle(tiles);





        game.getFrame().revalidate();







    }

}
