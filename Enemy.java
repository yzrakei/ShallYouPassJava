import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Enemy{
    Random random = new Random();

    private int health;
    private int difficulty;
    private String enemyName;
    private String type;
    private Map<String, Integer> enemyTypes = new HashMap<>();{{
        setEnemyTypes();
    }};

    public Enemy(){
        this.type = (String)enemyTypes.keySet().toArray()[random.nextInt(enemyTypes.size())];
        this.difficulty = enemyTypes.get(type);
        this.enemyName = type;
        switch(difficulty) {
            case 1:
                this.health = 3;
                break;
            case 2:
                this.health = 5;
                break;
            case 3:
                this.health = 10;
                break;
            default:
                this.health = 3;
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

    private void setEnemyTypes(){
        enemyTypes.put("Rotten Goblin", 1);
        enemyTypes.put("Mangled Skeleton", 1);
        enemyTypes.put("Decayed Rat", 1);
        enemyTypes.put("Rancid Larvae", 1);
        enemyTypes.put("Putrid Slime", 1);
        enemyTypes.put("Large Mangled Skeleton", 2);
        enemyTypes.put("Giant Decayed Rat", 2);
        enemyTypes.put("Undead Archer", 2);
        enemyTypes.put("Undead Knight", 2);
        enemyTypes.put("Rotten Orc", 3);
        enemyTypes.put("Cursed Undead Knight", 3);
        enemyTypes.put("Cursed Undead Archer", 3);
        enemyTypes.put("Giant Rancid Larvae", 3); 
    }
}
