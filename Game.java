import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {

      JFrame window = new JFrame();
      window.setSize(600, 600);
      window.setResizable(false);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setTitle("SHALL YOU PASS JAVA?");
      window.getContentPane().setBackground(Color.BLACK);
      window.setLayout(null);
      
      JPanel titlePanel = new JPanel();
      titlePanel.setBackground(Color.BLACK);
      titlePanel.setBounds(50, 100, 500, 1000);
      window.add(titlePanel);

      JLabel titleText = new JLabel("SHALL YOU PASS JAVA?");
      titleText.setHorizontalAlignment(JLabel.CENTER);
      titleText.setForeground(Color.RED);
      titleText.setFont(new Font("High Tower Text", Font.BOLD, 36));
      titleText.setBounds(50, 100, 500, 100);
      titlePanel.add(titleText);

      JButton startButton = new JButton("START GAME");
      startButton.setFont(new Font("Perpetua", Font.PLAIN, 18));
      startButton.setFocusPainted(false);
      startButton.setBorderPainted(false);
      startButton.setForeground(Color.WHITE);
      startButton.setBackground(Color.BLACK);
      startButton.setBounds(200, 300, 200, 50);
      titlePanel.add(startButton);

      window.setVisible(true);

      startButton.addActionListener(e -> {
          System.out.println("Game Started!");
          titlePanel.setVisible(false);
      });

  }

}