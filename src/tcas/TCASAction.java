package tcas;

import objects.Plane;

/**
 * Cette classe permet d'effectuer les actions 
 * pour l'avion qui possède le TCAS (monter, descendre, etc)
 * @author Maelle
 *
 */
public class TCASAction {
	
	private Plane localPlane; 
	
	
	public TCASAction(Plane localPlane) {
		super();
		this.localPlane = localPlane;
	}

	public void descend(Plane plane) {
		plane.move(-2.5, -2.5);
	}
	
	public void climb(Plane plane) {
		plane.move(+2.5, +2.5);
	}

}
