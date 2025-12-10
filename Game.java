import javax.swing.JFrame;
import java.awt.Color;

public class Game {
  public static void main(String[] args) {
    //run main game here
    JFrame window = new JFrame();
    window.setSize(800, 720);
    window.setResizable(false);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setTitle("SHALL YOU PASS JAVA?");
    window.getContentPane().setBackground(Color.BLACK);

    //window.pack(); --> when adding panels

    window.setVisible(true);
  }
}
