import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;


public class GameFrame extends JFrame {
    private JPanel gamePanel;
    private DatabaseHelper databaseHelper;
    private Map<Integer,String>mapIdNames;
    private int defaultId=4;
    public GameFrame() {
        super("Hunting");
        setSize(1250, 850);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initDataBase();
        initMenu();
        initGamePanel();
        revalidate();
        SongHelper.playSong();
    }

    private void initDataBase() {
        databaseHelper=new DatabaseHelper();
        updateDataBase();
    }
    private void updateDataBase(){
        mapIdNames=databaseHelper.getArrayOfNamesPlayers();
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

            }
            public void mousePressed(MouseEvent e) {SongHelper.gunBang();}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        add(gamePanel);
    }


    private void initStart(){
        Bot.startKills();
         Timer timer = new Timer("Timer");
         TimerTask gameProcess=new TimerTask() {
             int amountRound=0;
             @Override
            public void run() {
                 if(amountRound<=10) {
                     amountRound++;
                     gamePanel.removeAll();
                     gamePanel.revalidate();
                     gamePanel.add(randomBird(amountRound));
                     gamePanel.add(randomDog(amountRound));
                     gamePanel.revalidate();
                 }
                 else {
                     gamePanel.removeAll();
                     InformationFrame infoFrame=new InformationFrame(Bot.getKills());
                     if(databaseHelper.getRecord(defaultId)<Bot.getKills()){
                         databaseHelper.updatePlayer(defaultId,Bot.getKills());
                     }
                     databaseHelper.updateAmountGames(defaultId);
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


    private void initMenu() {
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("New");
        JMenuItem start=new JMenuItem("Start game");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                initStart();
            }
        });
        menu.add(start);
        JMenuItem insertNewPerson=new JMenuItem("Insert new player");
        insertNewPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String result = JOptionPane.showInputDialog(
                        null,
                        "Input name of new player");
                databaseHelper.insertPlayer(result);
                updateDataBase();
            }
        });
        menu.add(insertNewPerson);


        JMenuItem choosePerson=new JMenuItem("Choose person");
        choosePerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String res = (String) JOptionPane.showInputDialog(null, "Choose your player", "Players",
                        JOptionPane.PLAIN_MESSAGE, null, mapIdNames.values().toArray(), mapIdNames.values().toArray()[0]);
                defaultId=getIdByName(res);
                System.out.println(defaultId);
            }
        });
        menu.add(choosePerson);

        JMenu menuShow=new JMenu("Show");
        JMenuItem showTable=new JMenuItem("Table");
        showTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                databaseHelper.showTable();
            }
        });
        menuShow.add(showTable);
        menuBar.add(menu);
        menuBar.add(menuShow);
        setJMenuBar(menuBar);
    }
    private int getIdByName(String value){
        Optional<Integer> result = mapIdNames
                .entrySet()
                .stream()
                .filter(entry -> value
                        .equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
            return result.get().intValue();
    }
}
