package tcas;


/**
 * Partie affichage du TCAS (sonore et auditif)
 * @author Maelle
 *
 */
public class TCASRendering {
	
	private TCAS.LevelAlert alert; 

	
	public TCASRendering() {
	}

	public TCAS.LevelAlert getAlert() {
		return alert;
	}

	public void setAlert(TCAS.LevelAlert alert) {
		this.alert = alert;
	}

	public void printOnScreen(String element) {
		System.out.println("Alerte " + this.alert + " " + element);
	}
	
	public void vocalMessage(String message) {
		System.out.println("Alerte " + this.alert + " " + message);
	}

}
