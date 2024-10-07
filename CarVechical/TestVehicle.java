package c.v;

class Vehicle {
    protected String license_number;
    protected String make;
    protected String model;
    protected double price;
    
    public Vehicle(String l_n, String v_make, String v_model, double v_price)
    {
        license_number = l_n;
        make = v_make;
        model = v_model;
        price = v_price;
    }
    
    public String getVehicleName()
    {
        return make+" "+model;
    }
    
    public double price()
    {
        return price;
    }
    
    public String toString()
    {
        String result;
        
        result = "License # : "+ license_number+ "\nVehicle Name : "+getVehicleName()+"\nPrice : "+price;
        return result;
    }
}


class Rental extends Vehicle
{
    protected int number_of_days_for_rental;
    protected double rate_per_day;
    
    public Rental(String l_n, String v_make, String v_model, double v_price, int no_of_day, double r_p_d)
    {
        super(l_n,v_make,v_model,v_price);
        number_of_days_for_rental = no_of_day;
        rate_per_day = r_p_d;
    }
    
    public double price()
    {
        return number_of_days_for_rental*rate_per_day;
    }
    
    public String toString()
    {
        String result;
        result = super.toString();
        
        result += "\nRetal Info: "+"\n\tNumber of Days: "+number_of_days_for_rental+"\n\tRate per day: "+rate_per_day+"\n\tTotal: "+price();
        return result;
    }
}



class Bus extends Rental
{
    private double driver_cost;
    
    public Bus(String l_n, String v_make, String v_model, double v_price, int no_of_day, double r_p_d, double dri_cost)
    {
        super(l_n,v_make,v_model,v_price,no_of_day, r_p_d);
        driver_cost = dri_cost;
    }
    
    public void setDriverCost(double s_driver)
    {
        driver_cost = s_driver;
    }
    
    public String toString()
    {
        String result;
        double final_cost = driver_cost + super.price();
        result = super.toString();
        
        result += "\n\tDriver Cost: "+driver_cost+"\n\tFinal cost: "+final_cost;
        return result;
    }
    
    public double price()
    {
        return super.price()+driver_cost;
    }
}


class Sale extends Vehicle
{
   private double depreciation,sell_price;
   private double rate;
   
   public Sale(String vehnum,String make,String model, double p, double depval)
   {
       super(vehnum,make,model,p);
       depreciation = depval;
       sell_price = p;
   }
   
   public void setDepreciation(double rate)
   {
       depreciation = (rate/100)*super.price();
   }
   
   public double price()
   {
      sell_price = super.price() - depreciation;
      return sell_price;
   }
   
   public String toString()
   {
       return (super.toString() + "\n Depreciation\t:"+ depreciation +"\n Selling Price \t:"+ price());
   }
   
}

public class TestVehicle {
	 
    public static void main(String[] arg)
    {
        Vehicle[]comp1161_motors = new Vehicle [4];
        Sale veh1 = new Sale("FG1000", "Honda", "Civic", 100000.00, 25000);
        Rental veh2 = new Rental("GH7000", "Mercedez", "BClass", 125000.00, 5, 300);
        Bus veh3 = new Bus("AY3000","Bugatti","Veyron",300000.00, 5, 300,10000);
        Sale veh4 = new Sale("HI2000", "Nissan", "Sunny",75000.00,6000.00);
        
        Vehicle me = new Bus("BW1099","Nissan","Tida", 120000, 10, 400, 1000);
        comp1161_motors[0] = veh1;
        comp1161_motors[1] = veh2;
        comp1161_motors[2] = veh3;
        comp1161_motors[3] = veh4;
        
        ((Sale)comp1161_motors[0]).setDepreciation(20.0);
       ((Bus)comp1161_motors[2]).setDriverCost(15000.0);
        
        for( Vehicle veh : comp1161_motors)
        {
            System.out.println();
            System.out.println(veh);
        }
    }
 
}
