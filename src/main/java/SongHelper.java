import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SongHelper {
    public static void gunBang(){
        try {
            final FileInputStream fileInputStream ;
            fileInputStream = new FileInputStream("gun.mp3");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Player player = null;
                    try {
                        player = new Player(fileInputStream);
                        player.play();
                    } catch (JavaLayerException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void playSong() {
        try {
            FileInputStream fileInputStream = new FileInputStream("Priroda.wav");
            Player player = new Player(fileInputStream);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }
}
