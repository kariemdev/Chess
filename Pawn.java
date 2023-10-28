import javax.swing.*;
import java.util.ArrayList;

public class Pawn extends ChessPiece {
    private ImageIcon blackPawnPng = new ImageIcon("assests/black_pawn.png");
    private ImageIcon whitePawnPng = new ImageIcon("assests/white_pawn.png");
    private boolean isFirstMove = false;



    public Pawn(String color ){
        super(color , "Pawn"  );
        if (color.equals("black")) {
            setIcon( rescalePiece(this.blackPawnPng));


        } else {
            setIcon( rescalePiece(this.whitePawnPng));

        }
    }


    @Override
    public ArrayList<ChessTile> validPath() {
        return null;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }
}
