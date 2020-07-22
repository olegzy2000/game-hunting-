


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public class GameFrame extends JFrame {
    private JPanel optionsPanel;
    private JPanel gamePanel;
    private JComboBox comboBox;

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
        JButton start = new JButton("Start");
        optionsPanel.add(start);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(comboBox.getSelectedIndex()==0) {
                    initStart();
                }
            }

        });
        String[] items={"1 level","2 level","3 level"};
        comboBox=new JComboBox(items);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        optionsPanel.add(comboBox);
        optionsPanel.add(exit);
        add(optionsPanel, BorderLayout.WEST);
    }


    private Timer secondTimer;
    private Timer firstTimer;
    private int time1 =0;
    private int time2=0;

    private void initStart(){
        firstTimer=new Timer(45, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
               time2++;
               if(time2%25==0)
               System.out.println(time2);
               if(time1==0) {
       gamePanel.removeAll();
       gamePanel.revalidate();
       gamePanel.add(randomBird());
       gamePanel.add(randomDog());
       gamePanel.revalidate();
           secondTimer = new Timer(13, new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent actionEvent) {
                   time1++;
                   if (time1 == 250) {
                       gamePanel.removeAll();
                       gamePanel.revalidate();
                       gamePanel.add(randomBird());
                       gamePanel.add(randomDog());
                       gamePanel.revalidate();
                       time1 = 0;
                       secondTimer.stop();
                   }
               }
           });
           secondTimer.start();
       }
           }
       });
        firstTimer.start();
    }

    private Bot randomBird(){
        double indexRandoma=Math.random()*3;
        if(indexRandoma>1){
            return new Bot(1, -50, 0, new ImageIcon("birdRight.gif"));
        }
        else{
           return new Bot(1, 1300, 0, new ImageIcon("birdLeft.gif"));
        }
    }
    private Bot randomDog(){
        double indexRandoma=Math.random()*2;
        if(indexRandoma>1){
            return new Bot(1, 1250, 600, new ImageIcon("dogLeft.gif"));
        }
        else{
           return new Bot(1, -50, 600, new ImageIcon("dogRight.gif"));
        }
    }


    private void initOptionsPanel() {
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        initOptionsButton();
    }
}
