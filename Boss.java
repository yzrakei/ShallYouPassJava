import java.util.Random;

public class Boss{
    Random random = new Random();
    private String name;
    private int health;
    private final int maxHealth;
    private String[] attackLines;
    private String[] takeDamageLines;

    public Boss(){

        this.name= "EVIL COMPUTER";
        this.maxHealth = 3;
        this.health = maxHealth;
        attackLines = new String[]{
            "The EVIL COMPUTER takes away your ;",
            "The EVIL COMPUTER menacingly unleashes a stack overflow!",
            "The EVIL COMPUTER won't let you install JUnit testing!",
            "The EVIL COMPUTER corrupts your files!",
            "The EVIL COMPUTER sends a missile in O(1) time!"
        };
        takeDamageLines = new String[]{
            "The EVIL COMPUTER loses it's base case! ",
            "The EVIL COMPUTER encounters a logic error! ",
            "The EVIL COMPUTER's screen cracks! ",
            "The EVIL COMPUTER malfunctions! ",
        };
    }


    public void takeDamage(int amount) {
        this.health -= amount;
        System.out.println(takeDamageLines[random.nextInt(takeDamageLines.length)] + name + " Health: " + this.health);

        if (this.health <= 0) {
            System.out.println("You may pass java... this time...");
            this.health = 0;
        }
    }

    public boolean isDefeated() {
        return this.health <= 0;
    }

    public void attacks(Player player) {
        System.out.println(name + " attacks you!");
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