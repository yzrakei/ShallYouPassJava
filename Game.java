import javax.swing.JFrame;

public class Game {
  public static void main(String[] args) {
    //run main game here
    JFrame window = new JFrame();
    window.setResizable(false);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setTitle("SHALL YOU PASS JAVA?");

    window.pack();
    window.setVisible(true);
  }
}
