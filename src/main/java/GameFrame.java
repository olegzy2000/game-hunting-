


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;


public class GameFrame extends JFrame {
    private JPanel optionsPanel;
    private JPanel gamePanel;
    private JComboBox comboBox;
    private JButton start;
    public GameFrame() {
        super("Saphary");
        setSize(1300, 850);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initOptionsPanel();
        initGamePanel();
        revalidate();
        SongHelper.playSong();
    }

    private void initGamePanel() {
        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Image im = null;
                try {
                    im = ImageIO.read(new File("game.jpg"));
                } catch (IOException e) {
                }
                g.drawImage(im, 0, 0, null);
            }
        };
        gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        gamePanel.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent ee) {
                gamePanel.remove(ee.getComponent());
                SongHelper.gunBang();
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        add(gamePanel);
    }
    private void initOptionsButton() {
        optionsPanel=new JPanel();
        JButton exit = new JButton("Exit");
        start = new JButton("Start");
        optionsPanel.add(start);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    start.setEnabled(false);
                    initStart();
            }

        });
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        optionsPanel.add(exit);
        add(optionsPanel, BorderLayout.WEST);
    }

    private void initStart(){
         Timer timer = new Timer("Timer");
         TimerTask gameProcess=new TimerTask() {
             int amountRound=0;
             @Override
            public void run() {
                amountRound++;
              gamePanel.removeAll();
              gamePanel.revalidate();
              gamePanel.add(randomBird(amountRound));
              gamePanel.add(randomDog(amountRound));
              gamePanel.revalidate();
              if(amountRound==10) {
                  start.setEnabled(true);
                  revalidate();
                  cancel();
              }
            }
        };
        timer.scheduleAtFixedRate(gameProcess, 1000L, 5000L);
    }

    private Bot randomBird(double speed){
        double indexRandoma=Math.random()*3;
        if(indexRandoma>1){
            return new Bot((1.2+speed*0.2), -50, 0, new ImageIcon("birdRight.gif"));
        }
        else{
           return new Bot((1.2+speed*0.2), 1300, 0, new ImageIcon("birdLeft.gif"));
        }
    }
    private Bot randomDog(double speed){
        double indexRandoma=Math.random()*2;
        if(indexRandoma>1){
            return new Bot((1.2+(speed*0.2)), 1250, 600, new ImageIcon("dogLeft.gif"));
        }
        else{
           return new Bot((1.2+(speed*0.2)), -50, 600, new ImageIcon("dogRight.gif"));
        }
    }


    private void initOptionsPanel() {
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        initOptionsButton();
    }
}
