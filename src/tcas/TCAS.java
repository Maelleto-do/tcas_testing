package tcas; 
import java.util.LinkedList;
import java.util.Queue;
import java.util.TimerTask;

import externalObjects.Plane;

/**
 * Partie écoute du TCAS et analyse d'information
 * @author Maelle
 *
 */
public class TCAS extends TimerTask {
	
	public static enum LevelAlert {CC, INTRUDER, TA, RA} ;  
	private TCASAction tcasAction; 
	private TCASDisplayer tcasRendering; 
	private Queue<Plane> detectedPlanes = new LinkedList<Plane>(); 
    private Queue<Plane> environmentPlanes = new LinkedList<Plane>(); 
    public static Plane localPlane; 
    
    
    public TCAS(Queue<Plane> environmentPlanes, Plane localPlane) {
		super();
		this.environmentPlanes = environmentPlanes;
		this.localPlane = localPlane; 
		tcasAction = new TCASAction(localPlane); 
		tcasRendering = new TCASDisplayer(); 
	}

	public void run() {
		System.out.println("Detection en cours ...");
    	this.detect();
    	this.ask();
    }
	
    public void detect() {
    	if (! environmentPlanes.isEmpty()) {
        	Plane p = environmentPlanes.remove();
        	detectedPlanes.add(p);	
    	}
    }
    
    public void ask() {
    	
    	if (!detectedPlanes.isEmpty()) {
        	Plane detectedPlane = detectedPlanes.remove();

        	//Intruder zone
        	if ( Math.abs(detectedPlane.getX() - localPlane.getX()) < 6 &&  Math.abs(detectedPlane.getY() - localPlane.getY()) < 1200 ) {
        		this.intruder_alert(); 
        	}
        	//TA zone
        	if (  Math.abs(detectedPlane.getX() - localPlane.getX()) < 3.3 &&  Math.abs(detectedPlane.getY() - localPlane.getY()) < 850 ) {
        		this.ta_alert(); 
        	}
        	//RA zone
        	if (  Math.abs(detectedPlane.getX() - localPlane.getX()) < 2.1 &&  Math.abs(detectedPlane.getY() - localPlane.getY()) < 600 ) {
        		this.ra_alert(detectedPlane); 
        	}	
    	}
    	
    }
    
    //pas d'alerte auditive
    //losange évidé ou plein, blanc ou bleu
	private void intruder_alert() {
		tcasRendering.setAlert(LevelAlert.INTRUDER); 
		tcasRendering.printOnScreen("losange bleu");
		tcasRendering.vocalMessage(" ");
	}    
	
	//Annonce vocale
	//Cercle plein orange
	private void ta_alert() {
		tcasRendering.setAlert(LevelAlert.TA); 
		tcasRendering.printOnScreen("Cercle plein orange");
		tcasRendering.vocalMessage("Traffic; Traffic");
	}
	
	//Action à effectuer 
	//Carré plein rouge
	private void ra_alert(Plane detectedPlane) {
		tcasRendering.setAlert(LevelAlert.RA); 
		tcasRendering.printOnScreen("Carr� plein rouge");
		if (detectedPlane.getY() > localPlane.getY()) {
			tcasRendering.vocalMessage("Descend; Descend");
			tcasAction.descend(); 
		}
		else {
			tcasRendering.vocalMessage("Climb; Climb");
			tcasAction.climb(); 
		}
	}

	public Queue<Plane> getDetectedPlanes() {
		return detectedPlanes;
	}

	public void setDetectedPlanes(Queue<Plane> detectedPlanes) {
		this.detectedPlanes = detectedPlanes;
	}

}
