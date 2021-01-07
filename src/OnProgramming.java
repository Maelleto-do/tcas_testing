import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import objects.Plane;
import tcas.TCAS; 

public class OnProgramming {

	//TCAS à l'allumage
    public static void main(String[] args) {
    	Plane localPlane = new Plane(3, 500); //notre avion
        final Queue<Plane> environmentPlanes = new LinkedList<Plane>(); //avions autours de notre avion

        Timer timer = new Timer();

    	Plane plane1 = new Plane(2,1000); 
    	Plane plane3 = new Plane(2,2000); 
    	Plane plane2 = new Plane(4,700); 
    	
    	environmentPlanes.add(plane1); 
    	environmentPlanes.add(plane2); 
    	environmentPlanes.add(plane3); 
    	
    	TCAS tcas = new TCAS(environmentPlanes, localPlane);
    	//lancement du TCAS
        timer.schedule(tcas, 0, 5000);  //écoute chaque seconde
    }


}