import javax.swing.*;
import java.util.ArrayList;

public class Rook extends ChessPiece {
    private ImageIcon blackRookPng = new ImageIcon("assests/black_rook.png");
    private ImageIcon whiteRookPng = new ImageIcon("assests/white_rook.png");



    public Rook(String color ){
        super(color , "Rook");
        if (color.equals("black")) {
            setIcon( rescalePiece(this.blackRookPng));


        } else {
            setIcon( rescalePiece(this.whiteRookPng));

        }
    }


    @Override
    public ArrayList<ChessTile> validPath() {
        return null;
    }
}