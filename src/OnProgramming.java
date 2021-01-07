import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import objects.Plane;
import tcas.Listener; 

public class OnProgramming {

	//TCAS à l'allumage
    public int main(String[] args) {
        Timer timer = new Timer();
        final Queue<Plane> environmentPlanes = new LinkedList<Plane>(); 

    	Plane plane1 = new Plane(3,5); 
    	Plane plane3 = new Plane(3,5); 
    	Plane plane2 = new Plane(3,5); 

    	environmentPlanes.add(plane1); 
    	environmentPlanes.add(plane2); 
    	environmentPlanes.add(plane3); 
    	
        timer.schedule(new Listener(environmentPlanes), 0, 5000);  //listen every second
        return 0; 
    }


}