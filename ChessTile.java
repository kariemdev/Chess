import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessTile extends JPanel {

    private boolean hasPiece;
    private  ChessPiece piece;
    private int color;
    private int i;
    private int j;
    private int cellWidth = 50;
    private int cellHeight = 45;
    private int WIDTH = 600;
    private int HEIGHT = 500;
    private int rows = 8;
    private int cols  =8 ;
    private ChessBoard game;
    private  boolean drawCircle;

    public ChessTile(ChessBoard game , int i , int j  ){

        this.i = i;
        this.j = j;
        this.game = game;

    }



    public void setPiece(ChessPiece piece ){
        this.piece = piece;
        this.hasPiece = true;
        if(piece.getColor().equals("white"))
            game.getWhitePieces().add(piece);
        else
            game.getBlackPieces().add(piece);

        piece.setX(this.j);
        piece.setY(this.i);
        piece.setTile(this);
        game.getBoard().renderPiece(piece , this.i , this.j);

    }












    public int getXLocation(){return this.j;}
    public int geYLocation(){return this.i;}
    public boolean hasPiece(){return this.hasPiece;}
    public ChessPiece getPiece(){return this.piece;}

    public int getYCords(){return this.i ;}
    public int geXCords(){return this.j ;}
    public void removePiece(){
        this.piece = null ;
        this.hasPiece = false ;
    }


}
