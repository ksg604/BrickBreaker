package src.main.java;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Font;

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
    private int ballXDir = -1;
    private int ballYDir = -2;

    private Timer timer;
    private MapGenerator map;


    public Gameplay() {
        
        map = new MapGenerator(3,7);
        setKeyBindings();
        setFocusable(true);
        requestFocusInWindow();
        timer = new Timer(delay, this);
        timer.start();
        play = true;
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

        // Paint score
        g.setColor(Color.white);
        g.setFont(new Font("SERIF", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        // Paint bricks
        map.draw((Graphics2D)g);

        // Paint player
        g.setColor(Color.green);
        g.fillRect(playerPosX, 550, 100, 8);

        // Paint ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        if ( ballPosY > 570 ) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("SERIF", Font.BOLD, 30));
            g.drawString("Game Over, Score: "+score, 190, 300);

            g.setFont(new Font("SERIF", Font.BOLD, 20));
            g.drawString("Press Enter to Restart ", 230, 300);
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        timer.start();

        if ( play != null && play ) {

            if ( new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerPosX, 550, 100, 8))) {
                ballYDir = -ballYDir;
            }

            handleCollision();

            ballPosX += ballXDir;
            ballPosY += ballYDir;

            if ( ballPosX < 0 ) {
                ballXDir = -ballXDir;
            }
            if ( ballPosY < 0 ) {
                ballYDir = -ballYDir;
            }
            if ( ballPosX > 670 ) {
                ballXDir = -ballXDir;
            } 
        } 

        repaint();
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

    private void handleCollision() {
        for ( int i = 0; i < map.map.length; i++ ) {
            for ( int j = 0; j < map.map[0].length; j++ ) {
                if (map.map[i][j] > 0 ) {
                    int brickX = j * map.getBrickWidth() + 80;
                    int brickY = i * map.getBrickHeight() + 50;
                    int brickHeight = map.getBrickHeight();
                    int brickWidth = map.getBrickWidth();

                    Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);

                    if (ballRect.intersects(brickRect)) {
                        map.setBrickValue(0, i, j);
                        numBricks--;
                        score += 5;

                        if ( ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width )  {
                            ballXDir = -ballXDir;
                        } else {
                            ballYDir = -ballYDir;
                        }

                        return;
                    }
                }
            }
        }
    }

    private void setKeyBindings() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "A");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "D");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");

        Action moveLeftAction = new AbstractAction() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
                LOGGER.info("Moved left");
                moveLeft();
                repaint();
            }
        };

        Action moveRightAction = new AbstractAction() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
                LOGGER.info("Moved right");
                moveRight();
                repaint();
                
            }
        };

        Action restartGame = new AbstractAction(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                LOGGER.info("Restarted game");
                if ( play != null && !play ) {
                    play = true;
                    ballPosX = 120;
                    ballPosY = 350;
                    ballXDir = -1;
                    ballYDir = -2;
                    playerPosX = 310;
                    score = 0;
                    numBricks = 21;
                    map = new MapGenerator(3, 7);
                }
            }
        };



        am.put("LEFT", moveLeftAction);
        am.put("RIGHT", moveRightAction);
        am.put("A", moveLeftAction);
        am.put("D", moveRightAction);
        am.put("ENTER", restartGame);
    }

}