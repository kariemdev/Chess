import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public abstract class ChessPiece extends JLabel {
    private String typePiece;
    private String color;
    private int x;
    private int y;
    private ChessTile currentTile;
    private Point previousPos;

    private static Point mouseOffset;

    public ChessBoard game = ChessBoard.getInstance();


    public ChessPiece(String color, String typePiece) {
        this.color = color;
        this.typePiece = typePiece;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(y + "gg" + x);
                mouseOffset = e.getPoint();
                previousPos = getLocation();

                game.getBoard().setTilesCircle(generateMoves());
                game.getBoard().setDrawCircle(true);

            }
            public void mouseReleased(MouseEvent e ){
                Point newLocation = e.getLocationOnScreen();
                // Snap the child component to the nearest square
                int x = roundUpX(newLocation.x) /  50;
                 int y = roundUpY(newLocation.y) / 45;
                 System.out.println(x);


                if(newLocation.x > 400 || newLocation.x<0 || newLocation.y>380 || newLocation.y<0){
                    setLocation(previousPos);
                }
                else {
                    if(x<8 && x>=0 && y<8 && y>=0 && generateMoves().contains(game.getTileReference()[x][y])){
                     int x1 = roundUpX(newLocation.x) + 2; // Snap to the nearest column
                     int y1 = roundUpY(newLocation.y - 25) + 3; //Snap to the nearest rowmo

                     setLocation(x1, y1);
                     movePiece(y,x);


                    }
                    else{
                        setLocation(previousPos);
                    }

            }

                game.getBoard().setTilesCircle(null);
                game.getBoard().repaint();
            }




        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Calculate the new position of the child component
                Point newLocation = e.getLocationOnScreen();
                newLocation.translate(-mouseOffset.x-5, -mouseOffset.y-20);
                setLocation(newLocation);
                System.out.println(e.getLocationOnScreen());

            }
        });



}
    public  int roundUpX(int number) {
        int multiple = 50;
        int quotient = number / multiple;
        int roundedValue;
        roundedValue = quotient * multiple;
        return roundedValue;
    }
    public int roundUpY(int number){
        int multiple =45;
        int quotient = number / multiple;
        int roundedValue;
        roundedValue = quotient * multiple;
        return roundedValue;

    }

    public abstract ArrayList<ChessTile> validPath();

    public ImageIcon rescalePiece(ImageIcon imgPiece) {
        Image img = imgPiece.getImage();
        ImageIcon scaledImg = new ImageIcon(img.getScaledInstance(40, 40,Image.SCALE_AREA_AVERAGING));
        return scaledImg;
    }


    public ArrayList<ChessTile> generateMoves() {

        ArrayList<ChessTile> moves = new ArrayList<>();
        int[][] directions = {};
        int x = this.x;
        int y = this.y;
        switch (this.getTypePiece()) {


            case "Queen":
                directions = new int[][]
                        {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
                break;
            case "Knight":

                directions = new int[][]{
                        {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
                break;
            case "Bishop":
                directions = new int[][]
                        {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
                break;
            case "Rook":
                directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                break;
            case "King":
                directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
                break;
            /*
            case "Pawn":
                ArrayList < int[]> directionsList = new ArrayList<>() ;
                if (this.color.equals("white"))
                {

                    if (y < 7 && game.tileReference[x][y+1].hasPiece() == false)
                    {
                        directionsList.add(new int[] { 0, 1 });
                    }
                    if ( this.firstMove == true &&  game.tileReference[x][y+2].hasPiece() == false )
                    {
                        directionsList.add(new int[] { 0, 2 });
                    }
                    if (pawnDiagMove(((int)transform.position.x) + 1, ((int)transform.position.y) + 1))
                    {
                        directionsList.Add(new int[] { 1, 1 });
                    }
                    if (pawnDiagMove(((int)transform.position.x) - 1, ((int)transform.position.y) + 1))
                    {
                        directionsList.Add(new int[] { -1, 1 });
                    }

                    if (directionsList.Count !=0 )
                    {
                        directions = ConvertListToRectangularArray(directionsList);
                    }

                }
                else
                {
                    if (y > 0 && gdr.board[x , y -1].hasPiece() != true)
                    {
                        directionsList.Add( new int[]  { 0, -1 } );
                    }

                    if (this.firstMove == true && gdr.board[x,y-2].hasPiece() == false )
                    {
                        directionsList.Add(new int[] { 0, -2 });
                    }
                    if (pawnDiagMove(((int)transform.position.x) - 1, ((int)transform.position.y) - 1))
                    {
                        directionsList.Add(new int[]  { -1, -1 } );


                    }
                    if (pawnDiagMove(((int)transform.position.x) + 1 , ((int)transform.position.y) - 1))
                    {
                        directionsList.Add(new int[]  { 1, -1 } );
                    }
                    if (directionsList.Count != 0 )
                    {
                        directions = ConvertListToRectangularArray(directionsList);
                    }



                }
                break;*/
            default:
                System.out.println("Error");
                break;
        }

        int directionsCount = directions.length;
        for (int i = 0; i < directionsCount; i++) {
            int dx = directions[i][0];
            int dy = directions[i][1];
            int new_x = x + dx;
            int new_y = y + dy;

            do {

                if(new_x>=0 && new_x<8 && new_y>=0 && new_y<8) {
                    moves.add(game.getTiles()[new_x][new_y]);

                    if (game.getTiles()[new_x][new_y].hasPiece()) {
                        break;
                    }
                }


                new_x += dx;
                new_y += dy;
                System.out.println(this.getTypePiece().equals("Bishop"));
            } while ((new_x>=0 && new_x<8 && new_y>=0 && new_y<8 ) &&(this.getTypePiece().equals("Queen") || this.getTypePiece().equals("Rook")  ||this.getTypePiece().equals("Bishop")));


        }

        return moves;




    }



    public String getColor(){return this.color;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public String getTypePiece(){return this.typePiece;}
    public void setLocation(int x , int y ){
        super.setLocation(x,y);

    }
    public Point getLocation(){
        return super.getLocation();
    }
    public ChessTile getTile(){return this.currentTile;}
    public void setTile(ChessTile tile ){this.currentTile = tile;  }
    public void movePiece(int y , int x ){
        currentTile.removePiece();
        game.getTileReference()[y][x].setPiece(this);



    }


}
