
import java.util.Timer;
import java.util.TimerTask;
import tcas.Listener; 

public class Plane {


    int public int main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new Listener(), 0, 5000);  //listen every second
        return 0; 
    }


}