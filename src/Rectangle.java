
public class Rectangle {
	private static int count = 0;
	private int id;
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public Rectangle(int px1, int py1, int px2, int py2)
	{
		this.id = Rectangle.count++;
		this.x1 = px1;
		this.y1 = py1;
		this.x2 = px2;
		this.y2 = py2;
		
		//x1 doit etre inferieur ou egal a x2. Si ce n'est pas le cas, on inverse les coordonnees
		if(x1 > x2)
		{
			x1 = px2; 
			x2 = px1;
		}
		
		//idem pour y1 et y2
		if(y1 > y2)
		{
			y1 = py2; 
			y2 = py1;
		}
	}
	
	public Rectangle(Rectangle r)
	{
		this.id = Rectangle.count++;
		this.x1 = r.x1;
		this.y1 = r.y1;
		this.x2 = r.x2;
		this.y2 = r.y2;
	}

	public int getId() {
		return id;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
}
