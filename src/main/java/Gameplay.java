package src.main.java;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import java.util.logging.Logger;


public class Gameplay extends JPanel implements ActionListener{

    private static final Logger LOGGER = Logger.getLogger(Gameplay.class.getName());

    private Boolean play = false;
    private int numBricks = 21;
    private int score = 0;
    private int delay = 8;

    private int playerPosX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballDirX = -1;
    private int ballDirY = -2;

    private Timer timer;


    public Gameplay() {
        Timer gameTimer = new Timer(0, this);
        
        setKeyBindings();
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
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
        g.fillOval(ballPosX, ballPosY, 20, 20);

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        timer.start();
        LOGGER.info("aight");
        
    }

    private void moveRight() {
        if ( playerPosX >= 600 ) {
            playerPosX = 600;
        } else {
            playerPosX += 20;
        }
    }
    private void moveLeft() {
        if ( playerPosX <= 10 ) {
            playerPosX = 10;
        } else {
            playerPosX -= 20;
        }
    }

    private void setKeyBindings() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "A");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "D");

        Action moveLeftAction = new AbstractAction() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
                LOGGER.info("Moved left");
                moveLeft();
                repaint();
            }
        };

        Action moveRightAction = new AbstractAction(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
                LOGGER.info("Moved right");
                moveRight();
                repaint();
                
            }
        };

        am.put("LEFT", moveLeftAction);
        am.put("RIGHT", moveRightAction);
        am.put("A", moveLeftAction);
        am.put("D", moveRightAction);
    }

}