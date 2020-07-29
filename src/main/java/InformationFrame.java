import javax.swing.*;
import java.awt.*;

public class InformationFrame extends JFrame {
    public InformationFrame(int kills){
        super("Statistics");
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setLayout(null);
        JLabel kill=new JLabel("Kills:"+kills);
        kill.setBounds(135,75,100,50);
        kill.setFont(new Font("Serif", Font.PLAIN, 25));
        JLabel round=new JLabel("Round:"+10);
        round.setBounds(135,25,100,50);
        round.setFont(new Font("Serif", Font.PLAIN, 25));
        panel.add(round);
        panel.add(kill);
        add(panel);
        revalidate();
    }
}
