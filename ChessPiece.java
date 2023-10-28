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
                printTIles();

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

            case "Pawn":
                ArrayList < int[]> directionsList = new ArrayList<>() ;
                if (this.color.equals("black"))
                {

                    if (y < 7 && game.getTileReference()[this.y+1][this.x].hasPiece() != true )
                    {
                        directionsList.add(new int[] { 0, 1 });
                    }

                    if (checkPawnDiagMove(this.x + 1 , this.y + 1))
                    {
                        directionsList.add(new int[] { 1, 1 });
                    }
                    if (checkPawnDiagMove( this.x - 1, this.y + 1))
                    {
                        directionsList.add(new int[] { -1, 1 });
                    }
                    directions = convertArrayListTo2DArray(directionsList);


                }
                else
                {
                    if (y > 0 && game.getTileReference()[this.y-1 ][this.x].hasPiece() != true)
                    {
                        directionsList.add( new int[]  { 0, -1 } );
                    }
                    if (checkPawnDiagMove( this.x- 1, this.y- 1))
                    {
                        directionsList.add(new int[]  { -1, -1 } );
                    }
                    if (checkPawnDiagMove(this.x+ 1 , this.y - 1))
                    {
                        directionsList.add(new int[]  { 1, -1 } );
                    }

                    directions = convertArrayListTo2DArray(directionsList);

                }
                break;
            default:
                System.out.println("Error");
                break;
        }
        if(directions !=null){
        int directionsCount = directions.length;
        for (int i = 0; i < directionsCount; i++) {
            int dx = directions[i][0];
            int dy = directions[i][1];
            int new_x = x + dx;
            int new_y = y + dy;


            do {

                if(ifInBounds(new_x , new_y) && !ifHasSameColorPiece(new_x ,new_y) ) {
                    moves.add(game.getTiles()[new_x][new_y]);

                }

                    if (ifInBounds(new_x , new_y) && game.getTiles()[new_y][new_x].hasPiece()) {
                        break;
                    }


                new_x += dx;
                new_y += dy;

            } while ((ifInBounds(new_x , new_y)) &&(this.getTypePiece().equals("Queen") || this.getTypePiece().equals("Rook")  ||this.getTypePiece().equals("Bishop")));
        }}
        return moves;
    }

    public static int[][] convertArrayListTo2DArray(ArrayList<int[]> arrayList) {
        int numRows = arrayList.size();
        if(numRows ==0 )
            return null;
        int numCols = arrayList.get(0).length;

        int[][] twoDArray = new int[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                twoDArray[row][col] = arrayList.get(row)[col];
            }
        }

        return twoDArray;
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
    public boolean checkPawnDiagMove(int x , int y ){
        if(this.color.equals("white")){

            if(ifInBounds(x , y ) && game.getTileReference()[y][x].hasPiece() && game.getTileReference()[y][x].getPiece().getColor().equals("black")){
                return true ;
            }
            else {
                return false;
            }
        }
        else {
            if(ifInBounds(x , y ) && game.getTileReference()[y][x].hasPiece() && game.getTileReference()[y][x].getPiece().getColor().equals("white")){
                return true ;}
            else {
                return false;
            }

        }

    }
    public boolean ifInBounds(int x , int y){
        if(x < 8 && x>=0 && y<8 && y>=0)
            return true;
        return false ;

    }
    public boolean ifHasSameColorPiece(int x , int y ){
        if(game.getTileReference()[y][x].hasPiece() && game.getTileReference()[y][x].getPiece().getColor().equals(this.color) )
            return true ;
        else
            return false ;
    }
    public  void printTIles(){
        ChessTile [][] board = game.getTileReference();
        for(int i = 0 ; i<8 ; i++){
            for(int j = 0 ; j<8 ;j++){
                System.out.print(board[i][j].hasPiece() + "  -   " );
            }
            System.out.println();
        }

    }

}
