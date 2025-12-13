public class Boss{
    private String name;
    private int health;
    private final int maxHealth;


    public Boss(){

        this.name= "EVIL COMPUTER";
        this.maxHealth = 3;
        this.health = maxHealth;
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