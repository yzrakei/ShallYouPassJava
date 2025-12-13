public class Boss{
    private String name;
    private int health;
    private final int maxHealth;
    private final String[] attackLines;

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
    }


    public void takeDamage(int amount) {
        this.health -= amount;
        System.out.println("Ouch! That hurts! My health is now: " + this.health);

        if (this.health <= 0) {
            System.out.println("No! I have been defeated! You may pass java... this time...");
            this.health = 0;
        }
    }

    public boolean isDefeated() {
        return this.health <= 0;
    }

    public void attacks(Player player) {
        System.out.println("The " + name + " attacks you!");
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
}