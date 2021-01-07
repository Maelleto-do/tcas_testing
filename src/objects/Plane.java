package objects;


/*
 * 
 * Description d'un avion avec ses coordonnées
 */
public class Plane {

	private int x;
	private int y;
	
	public Plane(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	} 
	
}
