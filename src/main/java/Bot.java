
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import static java.lang.Thread.sleep;

public class Bot extends JLabel {
    private double speed;
    private int yCoordinate;
    private int xCoordinate;
    private int count;
    private Thread thread;
    private Timer timer;
    public static int kills = 0;

    public Bot(final double speed, final int xCoordinate, final int yCoordinate, ImageIcon imageIcon) {
        super(imageIcon);
        this.speed = speed;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent mouseEvent) {
                SongHelper.gunBang();
                addKills();
                setIcon(new ImageIcon("dead.gif"));
                timer = new Timer(13, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (count == 13) {
                            setVisible(false);
                            timer.stop();
                            thread.stop();
                        } else {
                            count++;
                        }
                    }
                });
                timer.start();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = xCoordinate;
                double speed1 = 0;
                if (i > 1)
                    speed1 = -speed;
                else
                    speed1 = speed;
                while (i < 1320) {
                    setLocation(i += 5 * speed1, yCoordinate);
                    try {
                        sleep(24);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i < -200) {
                        thread.stop();
                        timer.stop();
                        break;
                    }
                }
                removeNotify();
                revalidate();
            }
        });
        thread.start();
        revalidate();
    }

    public static int getKills() {
        return kills;
    }
    public static void startKills(){
        kills=0;
    }
    public static void addKills(){
        kills++;
    }
}



