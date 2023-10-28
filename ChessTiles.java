import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ChessTiles extends JPanel {

    private boolean hasPiece;
    private  ChessPiece piece;

    private int cellWidth = 50;
    private int cellHeight = 45;
    private int WIDTH = 600;
    private int HEIGHT = 500;
    private int rows = 8;
    private int cols  =8 ;

    private ArrayList<ChessTile> tilesCircle;

    private boolean DrawShape = false;

    public ChessTiles( ){

    }
    public void renderPiece(ChessPiece piece , int y , int x){
        this.add(piece);
        piece.setBounds(x * cellWidth +2  , y* cellHeight, 40 , 40 );
        this.revalidate();




    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Loop through the rows and columns to draw the chessboard
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Alternate the color of the squares
                if ((row + col) % 2 == 0) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.gray);
                }

                // Draw the square
                g.fillRect(col * 50, row * 45, 50, 45);
            }
        }
        // You can add code to draw chess pieces on the board here.
        if (DrawShape && tilesCircle!= null) {
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = 50;
            for(ChessTile tile : tilesCircle){
                int x = tile.geXCords();
                int y = tile.getYCords();
                  // Set the color to gray
                g.setColor(Color.pink);

                 // Draw a filled gray circle
                  g.fillOval(y*50 + 9  , x*45 + 7  , 30, 30);

                 // Clear the flag after drawing
                this.DrawShape = false ;

            }
        }


    }
    public void setDrawCircle(boolean flag){
        if(tilesCircle != null){
        this.DrawShape = flag;
        repaint();

        }

    }
    public void setTilesCircle(ArrayList<ChessTile> tiles){
        this.tilesCircle =  tiles ;
    }








}
