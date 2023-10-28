import javax.swing.*;
import java.util.ArrayList;

public class Knight extends ChessPiece {
    private ImageIcon blackKnightPng = new ImageIcon("assests/black_knight.png");
    private ImageIcon whiteKnightPng = new ImageIcon("assests/white_knight.png");



    public Knight(String color ){
        super(color , "Knight");
        if (color.equals("black")) {
            setIcon( rescalePiece(this.blackKnightPng));


        } else {
            setIcon( rescalePiece(this.whiteKnightPng));

        }




    }


    @Override
    public ArrayList<ChessTile> validPath() {
        return null;
    }
}
