package src.main.java;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private Boolean play = false;
    private int numBricks = 21;
    private int score = 0;

    private int playerPosX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballDirX = -1;
    private int ballDirY = -2;


    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }

    public void paint(Graphics g) {

        // Paint background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // Paint borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        // Paint player
        g.setColor(Color.green);
        g.fillRect(playerPosX, 550, 100, 8);

        // Paint ball
        g.setColor(Color.yellow);
        g.fillRect(ballPosX, ballPosY, 20, 20);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerPosX >= 600) {
                playerPosX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerPosX <= 10) {
                playerPosX = 0;
            } else {
                moveLeft();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void moveRight() {
        play = true;
        playerPosX += 20;
    }
    public void moveLeft() {
        play = true;
        playerPosX -= 20;
    }

}