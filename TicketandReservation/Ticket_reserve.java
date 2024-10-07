package t1;

import java.util.*;

public class Ticket_reserve {

	  private static int counter=100;
	  List<String> BookingList=new ArrayList<String>();
	  ArrayList<Integer> AgeList=new ArrayList<Integer>();

	  public void reservation(){
	    System.out.println("Enter the tickets needed:");
	    Scanner tkts=new Scanner(System.in);
	    int tickets=tkts.nextInt();
	    if(tickets<=counter){
	      System.out.println("Name and age please");
	      System.out.println("age:");
	      Scanner age=new Scanner(System.in);
	      int Age=age.nextInt();
	      if(Age<18){
	        System.out.println("You're under 18.Booking cancelled");
	      }else{
	        for(int i=0;i<tickets;i++){
	          System.out.println("Name:");
	          Scanner nom=new Scanner(System.in);
	          String name=nom.nextLine();
	          BookingList.add(name);
	          AgeList.add(Age);
	          counter--;
	        }
	      }
	    }else{
	      System.out.println(tickets+"tickets  unavailable");
	    }
	    System.out.println("Names: "+BookingList+","+"Age:"+AgeList);
	  }


	  public static void main(String[] args) {
	    Ticket_reserve t1=new Ticket_reserve();
	    t1.reservation();
	  }
	}