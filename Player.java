// The Player class manages the player's health and status within the game. It includes methods for taking damage, checking if the player is alive, and increasing maximum health.
//Created by Christian as a way to create the baseline player functionality for the game.

// The Player class encapsulates player attributes and behaviors, allowing for health management and status checks.
public class Player {
  private int health;
  private int maxHealth;
  private String playerName;

  // Constructor for Player class, initializes player with a name and default health values. The player starts with 3 health points. We have an idea to introduce bonus in the future to give more health.
  public Player(String playerName) {
    this.playerName = playerName;
    this.health = 3;
    this.maxHealth = 3;
  }

  
  public boolean isAlive() {
    return this.health > 0;
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
  
  // Getter methods for player attributes. Includes name and health.
  public String getPlayerName() {
    return playerName;
  }
  public int getHealth() {
    return health;
  }
  
}
