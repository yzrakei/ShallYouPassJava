import java.util.Scanner;
import java.util.Random;

public class Game {
  private Scanner scanner;
  private Random random;

  private Player player;
  //private Dungeon dungeon;

  private Question[] questions;
  private Question currentQuestion;
  private int currentRoom = 0;
  private final int TOTAL_ROOMS = 10;

  private final int STATE_START = 0;
  private final int STATE_IN_DUNGEON = 1;
  private final int STATE_BOSS_FIGHT = 2;
  private final int STATE_GAME_OVER = 3;

  private int gameState;

  public Game(){
    this.scanner = new Scanner(System.in);
    this.gameState = STATE_START;
  }

  public void run() {
    while (gameState != STATE_GAME_OVER) {
      handleGameState();
      try {
        Thread.sleep(1000);
      } 
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void handleGameState() {
    switch (gameState) {
      case STATE_START:
        displayIntro();
        //handleDifficultySelection();
        break;

      case STATE_IN_DUNGEON:
        if (player != null && player.isAlive()) {

          if (currentRoom < TOTAL_ROOMS - 1) {
          processRoom();
          currentRoom++;
          } 
          else {
            if(player.getSelectedDifficulty() == 3) {
              gameState = STATE_BOSS_FIGHT;
            } 
            else {
              gameState = STATE_GAME_OVER;
            }
          }
        } 
        else {
          System.out.println("You have perished in the dungeon.");
          gameState = STATE_GAME_OVER;
        }
        break;

      case STATE_BOSS_FIGHT:
        bossFight();
        break;

      case STATE_GAME_OVER:
        System.out.println("GAME OVER!");
        break;

      default:
        System.out.println("Unknown game state!");
        gameState = STATE_GAME_OVER;
        break;
    }
  }

  private void generateQuestions() {
    //load questions from a file or database
  }
  
  private void processRoom() {
    System.out.println("Entered room " + (currentRoom + 1));
    //LOAD CURRENT QUESTION
    
    System.out.println(currentQuestion.getQuestionText());
    System.out.print("Your answer: ");
    String playerAnswer = scanner.nextLine();

    if (currentQuestion.checkAnswer(playerAnswer)) {
      System.out.println("CORRECT");
      currentRoom++;
    } 
    else {
      System.out.println("INCORRECT.");
      player.takeDamage(1);
      System.out.println("Current Health: " + player.getHealth());

      if (!player.isAlive()) {
        gameState = STATE_GAME_OVER;
      }
    }
  }

  private void bossFight() {
    System.out.println("A slight chill runs down your spine as you step into the room...");
    //spawn boss entity
    //ititiate fight sequence
    //when won: "GREAT ENEMY VANQUISHED"
    gameState = STATE_GAME_OVER;
  }

  private void displayIntro() {
    System.out.println("You find yourself in a dark room, ");
    System.out.println("where the only thing you can view is a dim white light. ");
    System.out.println("Picking up the apparatus, a voice speaks to you:");
    System.out.println("'Welcome, brave wanderer, to the Dungeons of Java...'");
    System.out.println("'Will you become a VSCode Vanguard or will you fall to the endless bugs that torment your code?'");

    System.out.println("\nPress ENTER to begin your quest...");
    String input = scanner.nextLine();
    if(input.isEmpty()) {
      System.out.println("You step into the foggy veil that opens before you...");
      gameState = STATE_IN_DUNGEON;
    }
  } 
  

  public static void main(String[] args) {
    Game game = new Game();
    game.run();
  }
}