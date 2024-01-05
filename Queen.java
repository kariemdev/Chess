import javax.swing.*;
import java.util.ArrayList;

public class Queen extends ChessPiece {
    private ImageIcon blackQueenPng = new ImageIcon("assests/black_queen.png");
    private ImageIcon whiteQueenPng = new ImageIcon("assests/white_queen.png");
    public Queen(String color ){
        super(color , "Queen");
        if (color.equals("black")) {
            setIcon( rescalePiece(this.blackQueenPng));

        } else {
            setIcon( rescalePiece(this.whiteQueenPng));

        }
    }


    @Override
    public ArrayList<ChessTile> validPath() {
        return null;
    }
}