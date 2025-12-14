public class Player {
  private int health;
  private int maxHealth;
  private String playerName;

  public Player(String playerName) {
    this.playerName = playerName;
    this.health = 3;
    this.maxHealth = 3;
  }

  
  public int getHealth() {
    return health;
  }

  public void takeDamage(int damage) {
    this.health -= damage;
    if (!isAlive()) {
      System.out.println("YOU PERISHED! " + playerName + " shall NOT pass Java!");
      this.health = 0;
    }
  }
  public void increaseMaxHealth(int amount) {
    this.maxHealth += amount;
    this.health = this.maxHealth;
    System.out.println("Max health increased! Current max health: " + this.maxHealth);
    }
  
  public String getPlayerName() {
    return playerName;
  }

  public boolean isAlive() {
    return this.health > 0;
  }
}
