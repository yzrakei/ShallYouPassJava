import java.util.Random;

public class Boss{
    Random random = new Random();
    private String name;
    private int health;
    private final int maxHealth;
    private String[] attackLines;
    private String[] takeDamageLines;

    public Boss(){

        this.name = "The Encapsulator, Lord of POJOs";
        this.maxHealth = 3;
        this.health = maxHealth;
        attackLines = new String[]{
            name + " takes away your ;",
            name + " menacingly gets a String!",
            name + " won't let you install JUnit testing!",
            name + " sets corruption to your fields!",
            name + " retrieves an attack in O(1) time!"
        };
        takeDamageLines = new String[]{
            name + " loses it's constructor! ",
            name + " falls from a syntax error! ",
            name + "'s parameters get cut!",
            name + " malfunctions! ",
        };
    }


    public void takeDamage(int amount) {
        this.health -= amount;
        System.out.println(takeDamageLines[random.nextInt(takeDamageLines.length)] + name + " has " + this.health + " life points.\n");

        if (this.health <= 0) {
            System.out.println(name + ": \"You may pass java... this time...\"");
            this.health = 0;
        }
    }

    public boolean isDefeated() {
        return this.health <= 0;
    }

    public void attacks(Player player) {
        System.out.println(name + " remains relentless.\n\n");
        player.takeDamage(1);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getAttackLine() {
        return attackLines[random.nextInt(attackLines.length)];
    }
}