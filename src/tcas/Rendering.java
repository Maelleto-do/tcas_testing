package tcas;


//Rendu visuel et auditif
public class Rendering {
	
	private Listener.LevelAlert alert; 

	
	public Rendering(Listener.LevelAlert alert) {
		this.alert = alert;
	}

	public void printOnScreen(String element) {
		System.out.println("Alerte " + this.alert + " " + element);
	}
	
	public void vocalMessage(String message) {
		System.out.println("Alerte " + this.alert + " " + message);
	}

}
