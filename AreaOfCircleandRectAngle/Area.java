 package shape.area;

 import java.util.Scanner;

 abstract  class Shape {
		protected double area;
		
		abstract public void acceptRecords(Scanner sc) ;

		abstract public void calculateArea();
			
		final public void printRecord() {
			System.out.println("Area:: "+ area);

		}
		
		
	}

 
 
 
 class Circle extends Shape {
 	private double radius;
 	
 	public void acceptRecords(Scanner sc) {
 		System.out.print("radius:: ");
 		radius = sc.nextDouble();
 	}
 	
 	public void calculateArea() {
 		this.area= Math.PI * Math.pow(radius, 2);
 	}
 	
 	
 	
 }
 
class Rectangle extends Shape {
		private double length;
		private double breadth;
		
		public void acceptRecords(Scanner sc) {
			System.out.print("Lenght:: ");
			length = sc.nextDouble();
			System.out.print("Breadth:: ");
			breadth = sc.nextDouble();
		}

		public void calculateArea() {
			this.area = length*breadth;
		}

		
		
	}
 
 
 

 
 public class Area {
		private static Scanner sc = new Scanner(System.in);
		
		public static int menuList() {
			System.out.println("0 Exit");
			System.out.println("1 Rectangle");
			System.out.println("2 Circle");
			int choice = sc.nextInt();
			return choice;
			
		}
		
		public static void main(String[] args) {
			int choice;
			
			while((choice=Area.menuList())!=0) {
				Shape shape = null;
				switch (choice) {
				case 1:
					shape = new Rectangle(); //Upcasting
					break;
				case 2:
					shape = new Circle(); //upcasting
					
					break;
				}
				if(shape!=null) {
					shape.acceptRecords(sc); //Dynamic Method Dispatch
					shape.calculateArea();  //Dynamic Method Dispatch
					shape.printRecord(); //
				}
			}
			
			
			
			/*while((choice=Program.menuList())!=0) {
				Shape shape = null;
				switch (choice) {
				case 1:
					Rectangle rect = new Rectangle();
					rect.acceptRecords(sc);
					rect.calculateArea();
					rect.printRecord();
					break;
				case 2:
					Circle circle = new Circle();
					circle.acceptRecords(sc);
					circle.calculateArea();
					circle.printRecord();
					break;*/
			
			
			
			
			
			
			
			
			
			
			
		}

	}
