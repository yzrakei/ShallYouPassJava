import java.util.Random;

public class Enemy{
    Random random = new Random();

    private int health;
    private int difficulty;
    private String enemyName;
    private String[] easyEnemies = {"Rotten Goblin", "Mangled Skeleton", "Decayed Rat", "Rancid Larvae", "Putrid Slime"};
    private String[] mediumEnemies = {"Large Mangled Skeleton", "Giant Decayed Rat", "Undead Archer", "Undead Knight"};
    private String[] hardEnemies = {"Rotten Orc", "Cursed Undead Knight", "Cursed Undead Archer", "Giant Rancid Larvae"};

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

    public int getHealth(){
        return health;
    }

    public void takeDamage(int damage){
        this.health -= damage;
        if(!isAlive()){
            System.out.println("Enemy perished. You have defeated " + enemyName + "!");
            this.health = 0;
        }
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int getEnemyDifficulty() {
        return difficulty;
    }
}
