import javax.swing.*;
import java.util.ArrayList;

public class King extends ChessPiece {
    private ImageIcon blackKingPng = new ImageIcon("assests/black_king.png");
    private ImageIcon whiteKingPng = new ImageIcon("assests/white_king.png");
    private boolean isKingUnderThreat;


    public King(String color ){
        super(color , "King");
        if (color.equals("black")) {
            setIcon( rescalePiece(this.blackKingPng));


        } else {
            setIcon( rescalePiece(this.whiteKingPng));
        }
    }


    @Override
    public ArrayList<ChessTile> validPath() {
        return null;
    }
    public boolean ifKingUnderThreat(){return this.isKingUnderThreat;}
    public void setKingUnderThreat(boolean threat){
        this.isKingUnderThreat = threat;
    }
}