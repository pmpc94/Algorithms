
public class People implements Comparable<People>{
	
	private int x;
	private int y;
	private int price;
	
	public People(int x, int y, int price){
		this.x = x;
		this.y = y;
		this.price=price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public int compareTo(People other) {
		if(this.getPrice()>other.getPrice())
			return 1;
		else if(this.getPrice()<other.getPrice())
			return -1;
		else
		return 0;
	}
	
	

}
