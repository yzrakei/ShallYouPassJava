import java.util.Scanner;
// Main game class to manage game flow
public class Game {
  private Player player;
  private Dungeon dungeon;
  private Scanner scanner;

  // Current question being asked
  private Questions currentQuestion;
  private int currentRoom = 0;
  private final int Total_Rooms = 10;

  //Game States for managing the different phases of the game. At any time, the game is in one of these states.
  private final int STATE_START = 0;
  private final int STATE_IN_DUNGEON = 1;
  private final int STATE_BOSS_FIGHT = 2;
  private final int STATE_GAME_OVER = 3;
  
  private int gameState;

  // Constructor
  
  public Game(){
    this.scanner = new Scanner(System.in);
    this.gameState = STATE_START;
  }

  // Main game loop
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
        gameState = STATE_IN_DUNGEON;
        break;

      case STATE_IN_DUNGEON:
      if (!player.isAlive()) {
          System.out.println("You have perished in the dungeon.");
          gameState = STATE_GAME_OVER;
        }
        else if (currentRoom < Total_Rooms) {
          processRoom();
        }
        
        else {
         if (player.getSelectedDifficulty() == 3) {
            gameState = STATE_BOSS_FIGHT;
          } else {
            gameState = STATE_GAME_OVER;
            System.out.println("Congratulations! You have successfully navigated the dungeon! You HAVE PASSED JAVA! ENJOY YOU NEW CHROMEBOOK!");
          }
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


  private void displayIntro(){
    System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    THE DUNGEON OF JAVA                                 ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════════╝\n");
    
    System.out.println("You awake in the depths of a dark dungeon...");
    System.out.println("The only visible is the dim light emitted from a lone chromebook on a pedestal.\n");
    
    System.out.println("You boot up the strange apparatus to a threatening red glow and a sinister voice echoes through the screen:\n");
    
    System.out.println("═══════════════════════════════════════════════════════════════════════════");
  System.out.println("  \"WELCOME TO THE DUNGEON OF JAVA!\"");
    System.out.println("═══════════════════════════════════════════════════════════════════════════\n");
    
    System.out.println("\"A new contender has entered my rightful domain...tsk. Thou shall know me, for I am Evil Computer.\"");
    System.out.println("\"You shall feel the wrath, of a thousand years of my passion for Java!\"\n");
    
    System.out.println("\"It has come to my attention that you wish to exceed my expertisein Java.\"");
    System.out.println("\"I would like to see you try-\"\n");

    System.out.println("The computer suddenly turns into a bright blue.\nA new voice emerges from the speakers:\n");

    System.out.println("\"Sorry I am late, brave wanderer. I am the Setup Wizard, and you must be the new VSCode Vanguard...\"\n");
    System.out.println("\"You must make it to the end of this dungeon, and take Evil Computer down once and for all. Here's what you have to do:\"\n");
    
    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━ THE RULES ━━━━━━━━━━━━━━━━━━━━━━━━━");
    System.out.println("  • Navigate through 10 rooms filled with challenges");
    System.out.println("  • Answer questions correctly to proceed");
    System.out.println("  • Wrong answers will cost you health");
    System.out.println("  • Survive and escape to claim your Chromebook!");
    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

    System.out.println("Before we begin, what shall we call you wanderer? ");
  
    System.out.println("Enter your player name: ");
    String playerName = scanner.nextLine();
    this.player = new Player(playerName);

    System.out.println("Prepare yourself... Your journey begins now...\n");
  }
   

    
  private void handleDifficultySelection(){

    System.out.println("Select Difficulty Level:");
    System.out.println("1. Easy ");
    System.out.println("2. Medium ");
    System.out.println("3. Hard ");

    int difficulty = 0;
    while (difficulty < 1 || difficulty > 3) {
      System.out.print("Enter your choice (1-3): ");
      try {
        difficulty = Integer.parseInt(scanner.nextLine());
        if (difficulty < 1 || difficulty > 3) {
          System.out.println("Invalid choice. Please select a valid difficulty level.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 3.");
      }
    }
    player.setSelectedDifficulty(difficulty);

    this.dungeon = new Dungeon(difficulty, Total_Rooms);
    System.out.println("Welcome, " + player.getPlayerName() + "!");
  }

  private void processRoom(){

    if (!player.isAlive()){
      return;
    }
    currentQuestion= dungeon.getNextQuestion();
    
    if (currentQuestion ==null){
      System.out.println("No more questions available.");
      currentRoom = Total_Rooms;
      return;
   }
   
    System.out.println("You enter room " + (currentRoom + 1) + " of the dungeon.");
    System.out.println("Question: " + currentQuestion.getQuestionText());

   if (currentQuestion instanceof MultipleChoice) {
      MultipleChoice mcQuestion = (MultipleChoice) currentQuestion;
      String[] options = mcQuestion.getOptions();
      for (int i = 0; i < options.length; i++) {
        System.out.println((i + 1) + ". " + options[i]);
      }
    }

    System.out.print("Your answer: ");
    String playerAnswer = scanner.nextLine();

    if (currentQuestion.checkAnswer(playerAnswer)) {
      System.out.println("Correct! You may proceed to the next room.");
      // currentQuestion.applyReward(player); // Uncomment when applyReward is implemented
      currentRoom++;
    } else {
      System.out.println("Incorrect! You take damage.");
      player.takeDamage(1);
    }
  }

  private void bossFight(){
    Boss boss = dungeon.getFinalBoss();

    System.out.println("You have reached the final room and face the Dungeon Boss!");
  
    while(player.isAlive() && !boss.isDefeated()){
      
      Questions currentQuestion = dungeon.getBossQuestion();

      if (currentQuestion == null) {
        System.out.println("No more boss questions available.");
        break;
      }
      
      System.out.println("Boss Question: " + currentQuestion.getQuestionText());

      if (currentQuestion instanceof MultipleChoice) {
        MultipleChoice mcQuestion = (MultipleChoice) currentQuestion;
        String[] options = mcQuestion.getOptions();
        for (int i = 0; i < options.length; i++) {
          System.out.println((i + 1) + ". " + options[i]);
        }
      }
      System.out.print("Your answer: ");
      String playerAnswer = scanner.nextLine();

      if (currentQuestion.checkAnswer(playerAnswer)) {
        System.out.println("Correct! You dealt damage to the Boss.");
        boss.takeDamage(1);
      } else {
        System.out.println("Incorrect! " + boss.getAttackLine());
        boss.attacks(player);
      }
    }

    if (boss.isDefeated()) {
      System.out.println("You have defeated the Dungeon Boss! You may now escape the dungeon.");
    } else {
      System.out.println("You have been defeated by the Dungeon Boss.");
    }
    gameState = STATE_GAME_OVER;

  }

  public static void main(String[] args) {
    Game game = new Game();
    game.run();
    //run main game here
  }
}
