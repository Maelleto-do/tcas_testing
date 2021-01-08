package tcas;

import externalObjects.Plane;

/**
 * Cette classe permet d'effectuer les actions 
 * pour l'avion qui possède le TCAS (monter, descendre, etc)
 * @author Maelle
 *
 */
public class TCASAction {
	
	public TCASAction(Plane localPlane) {
		super();
	}

	public void descend() {
		TCAS.localPlane.move(-2.5, -2.5);
	}
	
	public void climb() {
		TCAS.localPlane.move(+2.5, +2.5);
	}

}
