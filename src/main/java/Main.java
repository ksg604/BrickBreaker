package src.main.java;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.util.logging.Logger;



public class Main { 

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Gameplay gamePlay = new Gameplay();
        frame.setBounds(10, 10, 700, 600);
        frame.setTitle("Brick Breaker");
        frame.setResizable(false);
        frame.add(gamePlay);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}