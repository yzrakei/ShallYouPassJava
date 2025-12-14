import java.util.Scanner;
// Main game class to manage game flow
public class Game {
  private Scanner scanner;
  private int currentDifficulty;

  // Current question being asked
  private Questions currentQuestion;
  private int currentRoom = 0;
  private final int Total_Rooms = 3;

  //Game States for managing the different phases of the game. At any time, the game is in one of these states.
  private final int STATE_START = 0;
  private final int STATE_STORY = 1;
  private final int STATE_IN_DUNGEON = 2;
  private final int STATE_BOSS_FIGHT = 3;
  private final int STATE_GAME_OVER = 4;
  
  private int gameState;

  //difficulty states
  private final int DIFFICULTY_EASY = 1;
  private final int DIFFICULTY_MEDIUM = 2;  
  private final int DIFFICULTY_HARD = 3;
  private final int DIFFICULTY_BOSS = 4;

  // Constructor
  
  public Game(){
    this.scanner = new Scanner(System.in);
    this.gameState = STATE_START;
  }

  // Main game loop
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
        gameState = STATE_STORY;
        break;

      case STATE_STORY:
        level1();
        break;

      case STATE_IN_DUNGEON:
        if (!player.isAlive()) {
            System.out.println("THE SETUP WIZARD: \"Another vanguard fallen...\"");
            gameState = STATE_GAME_OVER;
          }
          else if (currentRoom < Total_Rooms) {
            processRoom();
          }
          else {
            if (currentDifficulty == DIFFICULTY_HARD) {
              gameState = STATE_BOSS_FIGHT;
            } 
            else if(currentDifficulty < DIFFICULTY_HARD){
              System.out.println("\nYou sense the power of the dungeon growing stronger...\n");
              currentDifficulty++;
              this.dungeon = new Dungeon(currentDifficulty, Total_Rooms);
              currentRoom = 0;
            }
            else{
              gameState = STATE_GAME_OVER;
          }
  
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


  private void displayIntro(){
    System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    THE DUNGEON OF JAVA                                 ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════════╝\n");
    
    System.out.println("You awake in the depths of a dark dungeon...");
    System.out.println("The only visible is the dim light emitted from a lone chromebook on a pedestal.\n");
    System.out.println("      __...--~~~~~-._   _.-~~~~~--...__\n" + //
            "    //               `V'               \\ \n" + //
            "   //                 |                 \\ \n" + //
            "  //__...--~~~~~~-._  |  _.-~~~~~~--...__\\ \n" + //
            " //__.....----~~~~._\\ | /_.~~~~----.....__\\\\\n" + //
            "====================\\|//====================\n" + //
            "                    `---`\n" );

    
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
    System.out.println("\"You must make it to the end of this dungeon, and take Evil Computer down once and for all.\nHere's what you have to do:\"\n");
    
    System.out.println("═════════════════════ THE RULES ═════════════════════");
    System.out.println(" 1. Navigate through 10 rooms filled with challenges");
    System.out.println(" 2. Answer questions correctly to proceed");
    System.out.println(" 3. Wrong answers will cost you health");
    System.out.println(" 4. Survive and escape to claim your Chromebook!");
    System.out.println("═════════════════════════════════════════════════════\n");

    System.out.println("Before we begin, what shall we call you wanderer? ");
  
    System.out.println("- Enter your player name: -");
    String playerName = scanner.nextLine();
    if(playerName == null || playerName.trim().isEmpty()){
      playerName = "Gerard";
    }
    this.player = new Player(playerName);

    System.out.println("Prepare yourself "+ playerName + "... Your journey begins now...\n\n═══════════════════════════════════════════════════════════════════════════\n");
  }
   
  private void level1(){
    int option = 0;
    System.out.println("There are two pathways ahead of you:\n1. A narrow dusty hallway\n2. A dark spiral staircase (Option 2)\nWhich path do you choose? (1 or 2)\n");
    while (option < 1 || option > 2) {
      System.out.print("Enter your choice (1-2): ");
      try {
        option = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number between 1 and 2.");
      }
    }
    if(option == 1){
      System.out.println("You tread carefully down the hall...\n");
      currentDifficulty = DIFFICULTY_HARD;
      this.dungeon = new Dungeon(currentDifficulty, Total_Rooms);
      gameState = STATE_IN_DUNGEON;
    } else {
      System.out.println("You descend into the unknown...\n");
      currentDifficulty = DIFFICULTY_EASY;  
      this.dungeon = new Dungeon(currentDifficulty, Total_Rooms);
      gameState = STATE_IN_DUNGEON;
    }
    
  }

  private void selectMCQuestion(){
    if (currentQuestion instanceof MultipleChoice) {
      MultipleChoice mcQuestion = (MultipleChoice) currentQuestion;
      String[] options = mcQuestion.getOptions();
      for (int i = 0; i < options.length; i++) {
        System.out.println((i + 1) + ". " + options[i]);
      }
    }
  }

  private void processRoom() {
    if (!player.isAlive()) return;

    Enemy enemy = new Enemy(currentDifficulty);
    System.out.println("\n- Room " + (currentRoom + 1) + " -\n");
    System.out.println(enemy.getEnemyName() + " challenges you!\n");

    while(enemy.isAlive() && player.isAlive()) {

      currentQuestion = dungeon.getNextQuestion();
      
      /*if(currentQuestion == null) {
        System.out.println("No more questions available.");
        currentRoom = Total_Rooms;
        return;
      }*/

      System.out.println("Question: " + currentQuestion.getQuestionText());
      selectMCQuestion();

      System.out.print("Your answer: ");
      String playerAnswer = scanner.nextLine();

      if(currentQuestion.checkAnswer(playerAnswer)){
        enemy.takeDamage(1);
        System.out.println("Correct! You damaged the enemy. Enemy Life: " + enemy.getHealth() + "\n");

        if(!enemy.isAlive()){
            System.out.println("\nYou defeated " + enemy.getEnemyName());
            currentRoom++;
            System.out.println("You move onward to the next room.\n");
            return;
        }

      }else{
        player.takeDamage(1);
        System.out.println("Incorrect! " + enemy.getEnemyName() + " attacks! Current Life: " + player.getHealth());

        if(!player.isAlive()){
            System.out.println("You have been defeated...");
            return;
        }
      }
    }
  }

  private void bossFight(){
    Boss boss = dungeon.getFinalBoss();

    System.out.println("You have reached the final room...\nYou step into the murky fog...\n");
    System.out.println("\\`._`--','    )   o `.    <       :     \\  ( ( ,. \\  \\\\\r\n" + //
            "\\\\\\  `-,'     /  ,-.___`-.  :      |      \\  \\ `' ) )  \\\r\n" + //
            "\\\\     |      |  `-.   `-'  |     ,'-._   \\`._`--','    )   o\r\n" + //
            " \\     :       >    `. o   (    ,',--. `.\\\\\\  `-,'     /  ,-.\r\n" + //
            "  )   o `.    <       :     \\  ( ( ,. \\  \\\\     |      |  `-.\r\n" + //
            " /  ,-.___`-.  :      |      \\  \\ `' ) )  \\     :       >\r\n" + //
            " |  `-.   `-'  |     ,'-._   \\`._`--','    )   o `.    <\r\n" + //
            "  >    `. o   (    ,',--. `.\\\\\\  `-,'     /  ,-.___`-.  :\r\n" + //
            " <       :     \\  ( ( ,. \\  \\\\     |      |  `-.   `-'  |\r\n" + //
            "  :      |      \\  \\ `' ) )  \\     :       >    `. o   (    ,\r\n" + //
            "  |     ,'-._   \\`._`--','    )   o `.    <       :     \\  (\r\n" + //
            " (    ,',--. `.\\\\\\  `-,'     /  ,-.___`-.  :      |      \\  \\\r\n" + //
            "  \\  ( ( ,. \\  \\\\     |      |  `-.   `-'  |     ,'-._   \\`._\r\n" + //
            "   \\  \\ `' ) )  \\     :       >    `. o   (    ,',--. `.\\\\\\\r\n" + //
            "   \\`._`--','    )   o `.    <       :     \\  ( ( ,. \\  \\\\\r\n" + //
            "`.\\\\\\  `-,'     /  ,-.___`-.  :      |      \\  \\ `' ) )  \\\r\n" + //
            "  \\\\     |      |  `-.   `-'  |     ,'-._   \\`._`--','    )\r\n" + //
            ")  \\     :       >    `. o   (    ,',--. `.\\\\\\  `-,'     /  ,\r\n" + //
            "    )   o `.    <       :     \\  ( ( ,. \\  \\\\     |      |  `\r\n" + //
            "   /  ,-.___`-.  :      |      \\  \\ `' ) )  \\     :       >\r\n" + //
            "   |  `-.   `-'  |     ,'-._   \\`._`--','    )   o `.    <\r\n" + //
            "    >    `. o   (    ,',--. `.\\\\\\  `-,'     /  ,-.___`-.  :\r\n" + //
            "   <       :     \\  ( ( ,. \\  \\\\     |      |  `-.   `-'  |\r\n" + //
            "-.  :      |      \\  \\ `' ) )  \\     :       >    `. o   (\r\n" + //
            "-'  |     ,'-._   \\`._`--','    )   o `.    <       :     \\\r\n" + //
            "   (    ,',--. `.\\\\\\  `-,'     /  ,-.___`-.  :      |      \\\r\n" + //
            "    \\  ( ( ,. \\  \\\\     |      |  `-.   `-'  |     ,'-._   \\`\r\n" + //
            "     \\  \\ `' ) )  \\     :       >    `. o   (    ,',--. `.\\\\\\\r\n" + //
            "._   \\`._`--','    )   o `.    <       :     \\  ( ( ,. \\  \\\\\r\n" + //
            ". `.\\\\\\  `-,'     /  ,-.___`-.  :      |      \\  \\ `' ) )  \\\r\n" + //
            " \\  \\\\     |      |  `-.   `-'  |     ,'-._   \\`._`--','    )\r\n" + //
            ") )  \\     :       >    `. o   (    ,',--. `.\\\\\\  `-,'/  ,\r\n");

    System.out.println(boss.getIntroLine());

    while(player.isAlive() && !boss.isDefeated()){
      
      Questions currentQuestion = dungeon.getBossQuestion();

      if (currentQuestion == null) {
        System.out.println("You took too long to defeat the boss...The Encapuslator has encapsulated your soul for the rest of eternity...\n");
        break;
      }

      System.out.println("\nBoss Question: " + currentQuestion.getQuestionText());

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
        System.out.println("Current Life: " + player.getHealth());
      }
    }

    if (boss.isDefeated()) {
      System.out.println("\n══════════════════════════════════════════════\n- GREAT ENEMY VANQUISHED! -\n══════════════════════════════════════════════\n");
      System.out.println("              />\r\n" + //
                " (           //------------------------------------------------------(\r\n" + //
                "(*)OXOXOXOXO(*>                  --------                             \\\r\n" + //
                " (           \\\\--------------------------------------------------------)\r\n" + //
                "              \\>\n");
      System.out.println("Three more pillars remain...\n\nWhat will your future await?");
    } else {
      System.out.println("You have been defeated by " + boss.getName() + "...\n");
    }
    gameState = STATE_GAME_OVER;

  }

  public static void main(String[] args) {
    Game game = new Game();
    game.run();
    //run main game here
  }
}