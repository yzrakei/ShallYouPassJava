import java.util.Scanner;

public class Game {
  private Player player;
  private Dungeon dungeon;
  private Scanner scanner;

  private Question currentQuestion;
  private int currentRoom = 0;
  private final int Total_Rooms = 10;

  private final int STATE_START = 0;
  private final int STATE_IN_DUNGEON = 1;
  private final int STATE_BOSS_FIGHT = 2;
  private final int STATE_GAME_OVER = 3;
  
  private int gameState;
  }

  public Game(){
    this.scanner = new Scanner(System.in);
    this.gameState = STATE_START;
  }

  public void run() {
    while (gameState != STATE_GAME_OVER) {
      handleGameState();
    }
  }

  private void handleGameState() {
    switch (gameState) {
      case STATE_START:
        displayIntro();
        handleDifficultySelection();
        break;

      case STATE_IN_DUNGEON:
      if (player.isAlive()) {
        if (currentRoom < Total_Rooms-1) {
          processRoom();
        } else {
         if (player.getSelectedDifficulty() == 3) {
            gameState = STATE_BOSS_FIGHT;
          } else {
            gameState = STATE_GAME_OVER;
          }
        else{
          System.out.println("You have perished in the dungeon.");
          gameState = STATE_GAME_OVER;
        }
        break;
      case STATE_BOSS_FIGHT:
        bossFight();
        break;

      case STATE_GAME_OVER:
        break;
      
      default:
        System.out.println("Unknown game state!");
        gameState = STATE_GAME_OVER;
        break;
      }
  }

public class Game {
  public static void main(String[] args) {
    //run main game here
  }
}
