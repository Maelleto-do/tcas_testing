import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import objects.Plane;
import tcas.TCAS; 

public class OnProgramming {

	//TCAS à l'allumage
    public int main(String[] args) {
    	Plane localPlane = new Plane(3, 2); //notre avion
        final Queue<Plane> environmentPlanes = new LinkedList<Plane>(); //avions autours de notre avion

        Timer timer = new Timer();

    	Plane plane1 = new Plane(3,5); 
    	Plane plane3 = new Plane(3,5); 
    	Plane plane2 = new Plane(3,5); 
    	environmentPlanes.add(plane1); 
    	environmentPlanes.add(plane2); 
    	environmentPlanes.add(plane3); 
    	
    	//lancement du TCAS
        timer.schedule(new TCAS(environmentPlanes, localPlane), 0, 5000);  //écoute chaque seconde
        return 0; 
    }


}