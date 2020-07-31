import javax.swing.*;
import java.awt.*;

public class InformationFrame extends JFrame {
    public InformationFrame(int kills){
        super("Statistics");
        setSize(400, 200);
        setVisible(true);
        JPanel panel=new JPanel();
        panel.setLayout(null);
        JLabel kill=new JLabel("Kills:"+kills);
        kill.setBounds(135,75,100,50);
        kill.setFont(new Font("Serif", Font.PLAIN, 25));
        JLabel animals=new JLabel("Animals:"+22);
        animals.setBounds(135,25,120,50);
        animals.setFont(new Font("Serif", Font.PLAIN, 25));
        panel.add(animals);
        panel.add(kill);
        add(panel);
        revalidate();
    }
}
