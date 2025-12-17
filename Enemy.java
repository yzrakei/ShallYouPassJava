import java.util.Random;
// To create varied enemy encounters, we define an Enemy class that generates enemies based on difficulty level. Each enemy has a name and health points corresponding to its difficulty. 
//Implemented by Rachel.

// The Enemy class encapsulates enemy attributes and behaviors, allowing for a variety of foes in the game. Better than simply asking the question, it works to immerse the player in a dungeon-crawling experience.
public class Enemy{
    Random random = new Random();

    private int health;
    private int difficulty;
    private String enemyName;
    private String[] easyEnemies = {"Rotten Goblin", "Mangled Skeleton", "Decayed Rat", "Rancid Larvae", "Putrid Slime"};
    private String[] mediumEnemies = {"Large Mangled Skeleton", "Giant Decayed Rat", "Undead Archer", "Undead Knight"};
    private String[] hardEnemies = {"Rotten Orc", "Cursed Undead Knight", "Cursed Undead Archer", "Giant Rancid Larvae"};

    // Constructor for Enemy class, initializes enemy based on difficulty level.
    public Enemy(int difficulty){
        this.difficulty = difficulty;
        switch(difficulty) {
            case 1:
                this.health = 1;
                this.enemyName = easyEnemies[random.nextInt(easyEnemies.length)];
                break;
            case 2:
                this.health = 3;
                this.enemyName = mediumEnemies[random.nextInt(mediumEnemies.length)];
                break;
            case 3:
                this.health = 5;
                this.enemyName = hardEnemies[random.nextInt(hardEnemies.length)];
                break;
            default:
                this.health = 1;
                this.enemyName = easyEnemies[random.nextInt(easyEnemies.length)];
                break;
        }
    }

   
    public boolean isAlive() {
        return this.health > 0;
    }
    // Method to apply damage to the enemy and check if it has perished.
    public void takeDamage(int damage){
        this.health -= damage;
        if(!isAlive()){
            System.out.println("Enemy perished. You have defeated " + enemyName + "!");
            this.health = 0;
        }
    }

    // Getter methods for enemy attributes, including health, difficulty, and name.
     public int getHealth(){
        return health;
    }
    public int getDifficulty() {
        return difficulty;
    }

    public String getEnemyName() {
        return enemyName;
    }


    public int getEnemyDifficulty() {
        return difficulty;
    }
}
