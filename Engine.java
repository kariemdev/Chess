public class Engine
{
    private static Engine instance;
    private boolean playerTurn;
    private boolean whitePlayerClicked;
    private boolean blackPlayerClicked;

    public Engine (){
    }
    public static Engine getInstance(){
        if(instance == null){
            instance = new Engine();
        }
        return instance;
    }



    public boolean getPlayerTurn(){
        return this.playerTurn;
    }
    public boolean isWhitePlayerClicked(){
        return this.whitePlayerClicked;
    }
    public boolean isBlackPlayerClicked(){
        return this.blackPlayerClicked;
    }
}
