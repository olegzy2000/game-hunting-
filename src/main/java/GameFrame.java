


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


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
                    initFirstLevel();
                }
                else if(comboBox.getSelectedIndex()==1){
                    initSecondLevel();
                }
                else
                    initThirdLevel();
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
    private void initFirstLevel(){
        gamePanel.removeAll();
        Bot dog = new Bot(1, -50, 600, new ImageIcon("dogRight.gif"));
        Bot bird = new Bot(1, 1300, 0, new ImageIcon("birdLeft.gif"));
        gamePanel.add(dog);
        gamePanel.add(bird);
        gamePanel.revalidate();
    }
    private Timer secondTask;
    private int time=0;
    private void initSecondLevel(){
        gamePanel.removeAll();
        Bot dog = new Bot(1, 1250, 600, new ImageIcon("dogLeft.gif"));
        Bot bird = new Bot(1, -50, 0, new ImageIcon("birdRight.gif"));
        gamePanel.add(dog);
        gamePanel.add(bird);
        secondTask=new Timer(13,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                time++;
                if(time==230){
                    gamePanel.removeAll();
                    Bot dog1 = new Bot(1, -50, 600, new ImageIcon("dogRight.gif"));
                    Bot bird1 = new Bot(1, 1300, 0, new ImageIcon("birdLeft.gif"));
                    gamePanel.add(dog1);
                    gamePanel.add(bird1);
                    gamePanel.revalidate();
                    time=0;
                    secondTask.stop();
                }
            }
        });
        secondTask.start();
        gamePanel.revalidate();
    }
    private void initThirdLevel(){
        gamePanel.removeAll();
        Bot dog = new Bot(2, -50, 600, new ImageIcon("dogRight.gif"));
        Bot bird = new Bot(2, 1300, 0, new ImageIcon("birdLeft.gif"));
        gamePanel.add(dog);
        gamePanel.add(bird);
        gamePanel.revalidate();
    }
    private void initOptionsPanel() {
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        initOptionsButton();
    }
}
