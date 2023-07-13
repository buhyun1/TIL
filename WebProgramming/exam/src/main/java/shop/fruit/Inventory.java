package shop.fruit;

public class Inventory {
	public static Inventory[] allItems = new Inventory[] {
	   new Inventory("Apple", 2000, 25), 
	   new Inventory("Orange", 3000, 40), 
	   new Inventory("Pear", 2500, 30),
	   new Inventory("Banana", 500, 30),
	   new Inventory("Cherry", 4000, 20),
	   new Inventory("Mango", 3000, 25),
	   new Inventory("Strawberry", 2500, 30),
	   new Inventory("Papaya", 3200, 41),
	   new Inventory("Guava", 2400, 24),
	   new Inventory("Melon", 3100, 42)
	};
	
	String name;
	private int price;
	private int onHandStock;
	
	public Inventory(String n, int p, int s) {
		this.name = n;
		this.price = p;
		this.onHandStock = s;
	}

	public String getName() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public int getOnHandStock() {
		return this.onHandStock;
	}
	
	public synchronized void setOnHandStock(int sold) {
	   this.onHandStock -= sold;	
	}
	
	@Override
	public String toString() {
		return "Inventory[" + this.name + ", " + this.price + ", " + this.onHandStock + "]";
	}
}
