public class Player {
  private int health;
  private int maxHealth;
  private int selectedDifficulty;
  private String playerName;

  public Player(String playerName) {
    this.playerName = playerName;
    this.health = 3;
    this.maxHealth = 3;
    this.selectedDifficulty = 0; 
  }

  
  public int getHealth() {
    return health;
  }

  public void takeDamage(int damage) {
    this.health -= damage;
    if (this.health <= 0) {
      System.out.println("Game Over! " + playerName + " shall NOT pass Java!");
      this.health = 0;
    }
  }
  public void increaseMaxHealth(int amount) {
    this.maxHealth += amount;
    this.health = this.maxHealth;
    System.out.println("Max health increased! Current max health: " + this.maxHealth);
    }

  
  public int getSelectedDifficulty() {
    return selectedDifficulty;
  }

  public void setSelectedDifficulty(int selectedDifficulty) {
    this.selectedDifficulty = selectedDifficulty;
  }
  
  public String getPlayerName() {
    return playerName;
  }

  public boolean isAlive() {
    return this.health > 0;
  }
}
