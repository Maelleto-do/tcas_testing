package tcas; 
import java.util.LinkedList;
import java.util.Queue;
import java.util.TimerTask;

import objects.Plane;

/**
 * Partie écoute du TCAS et analyse d'information
 * @author Maelle
 *
 */
public class TCASListener extends TimerTask {
	
	public static enum LevelAlert {INTRUDER, TA, RA} ;  
	
    //Liste d'informations percues, taille <= 45
    private Queue<Plane> detectedPlanes = new LinkedList<Plane>(); 
    private Queue<Plane> environmentPlanes = new LinkedList<Plane>(); 
    
    
    public TCASListener(Queue<Plane> environmentPlanes) {
		super();
		this.environmentPlanes = environmentPlanes;
	}

	public void run() {
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
        	Plane p = detectedPlanes.remove();
        	
        	//Intruder zone
        	if (p.getX() < 6 &&  -1200 < p.getY() && p.getY() < 1200 ) {
        		this.intruder_alert(); 
        	}
        	//TA zone
        	if (p.getX() < 3.3 &&  -850 < p.getY() && p.getY() < 850 ) {
        		this.ta_alert(); 
        	}
        	//RA zone
        	if (p.getX() < 2.1 &&  -600 < p.getY() && p.getY() < 600 ) {
        		this.ra_alert(); 
        	}	
    	}
    	
    }
    
    //pas d'alerte auditive
    //losange évidé ou plein, blanc ou bleu
	private void intruder_alert() {
		TCASRendering screenAndMicro = new TCASRendering(LevelAlert.INTRUDER); 
		screenAndMicro.printOnScreen("losange bleu");
		screenAndMicro.vocalMessage(" ");
	}    
	
	//Annonce vocale
	//Cercle plein orange
	private void ta_alert() {
		TCASRendering screenAndMicro = new TCASRendering(LevelAlert.TA); 
		screenAndMicro.printOnScreen("Cercle plein orange");
		screenAndMicro.vocalMessage("Traffic; Traffic");
	}
	
	//Action à effectuer 
	//Carré plein rouge
	private void ra_alert() {
		
	}

	public Queue<Plane> getDetectedPlanes() {
		return detectedPlanes;
	}

	public void setDetectedPlanes(Queue<Plane> detectedPlanes) {
		this.detectedPlanes = detectedPlanes;
	}

}
