import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChessBoard extends JFrame {

    private static ChessBoard instance;




    private JFrame frame = new JFrame("Chess");
    private ChessTiles board = new ChessTiles();

    private ChessTile[][] tileReference = new ChessTile[8][8];
    private ArrayList<ChessPiece> whitePieces = new ArrayList<>();
    private ArrayList<ChessPiece> blackPieces = new ArrayList<>();
    private int cellWidth = 50;
    private int cellHeight = 45;




    public ChessBoard(){
            this.frame.setSize(new Dimension(560 , 400));
            this.frame.setDefaultCloseOperation(3);
            this.frame.setVisible(true);
            this.frame.setLayout(new BorderLayout());
            this.frame.add(board);
            this.board.setLayout(null);
            this.frame.setResizable(false);

            this.frame.revalidate();

        }


    public static ChessBoard getInstance() {
        if (instance == null) {
            instance = new ChessBoard();
        }
        return instance;
    }
    public void boardINIT(ChessBoard game){



        for(int i = 0 ; i<8 ; i++){
            for(int j = 0 ; j<8 ; j++){
                    ChessTile tile = new ChessTile(game , i , j   );
                    tileReference[i][j] = tile;

                }
        }
    }





    public void chessPiecesINIT(ChessBoard game){
        for(int i = 0; i<8 ; i++){
            this.tileReference[1][i].setPiece(new Pawn("black"));

        }

        for(int i = 0 ; i<8 ; i++){
            this.tileReference[6][i].setPiece(new Pawn("white"));



        }

        this.tileReference[7][0].setPiece(new Rook("white"));
        this.tileReference[7][7].setPiece(new Rook("white"));
        this.tileReference[0][0].setPiece(new Rook("black"));
        this.tileReference[0][7].setPiece(new Rook("black"));

        this.tileReference[7][1].setPiece(new Knight("white"));
        this.tileReference[7][6].setPiece(new Knight("white"));
        this.tileReference[0][1].setPiece(new Knight("black"));
        this.tileReference[0][6].setPiece(new Knight("black"));

        this.tileReference[7][2].setPiece(new Bishop("white"));
        this.tileReference[7][5].setPiece(new Bishop("white"));
        this.tileReference[0][2].setPiece(new Bishop("black"));
        this.tileReference[0][5].setPiece(new Bishop("black"));

        this.tileReference[0][3].setPiece(new Queen("black"));
        this.tileReference[7][3].setPiece(new Queen("white"));

        this.tileReference[0][4].setPiece(new King("black"));
        this.tileReference[7][4].setPiece(new King("white"));



        this.frame.revalidate();
    }

    public ChessTile [][] getTiles(){return this.tileReference;}
    public JFrame getFrame(){return this.frame;}



    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
    }
    public int getCellWidth(){return this.cellWidth; }
    public int getCellHeight(){return this.cellHeight; }
    public void placePiece(ChessPiece piece , int y , int x ){

        tileReference[y][x].setPiece(piece);




    }
    public ArrayList<ChessPiece> getWhitePieces( ){
        return this.whitePieces;
    }
    public ArrayList<ChessPiece> getBlackPieces( ){
        return this.blackPieces;
    }
    public ChessTiles getBoard(){return this.board;}
    public ChessTile [][] getTileReference(){return this.tileReference;}




}
