package objects;


/*
 * 
 * Description d'un avion avec ses coordonnées
 */
public class Plane {

	private double x;
	private double y;
	
	public Plane(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	} 
	
}
