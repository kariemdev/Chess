import javax.swing.*;
import javax.swing.*;
import java.util.ArrayList;

public class Bishop extends ChessPiece {
    private ImageIcon blackBishopPng = new ImageIcon("assests/black_bishop.png");
    private ImageIcon whiteBishopPng = new ImageIcon("assests/white_bishop.png");



    public Bishop(String color ){
        super(color , "Bishop"  );
        if (color.equals("black")) {
            setIcon( rescalePiece(this.blackBishopPng));



        } else {
            setIcon( rescalePiece(this.whiteBishopPng));
        }
    }


    @Override
    public ArrayList<ChessTile> validPath() {
        return null;
    }

}