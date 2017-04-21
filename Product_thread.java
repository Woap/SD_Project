

import java.rmi.* ; 
import java.net.MalformedURLException ; 

class Product_thread extends Thread {

	public ProductImpl t;
	protected boolean or = false;
	protected boolean argent = false;
	protected boolean bronze = false;
	
	public void Product_thread(){  
  }
  
  public void setOptions(ProductImpl t,boolean or, boolean argent, boolean bronze)
  {	
	this.t = t; // Parent
	this.or = or; // Production d'or ? 
  this.argent = argent; // Production d'argent ?
  this.bronze = bronze; // Production de bronze ? 
  
	this.start();
	System.out.println("Lancement de la production" );
	     
  }
   
  

  public void run(){
    while(true)
    {
      try
    {
    	if (or)
    	{
      	t.or+=10;
      	System.out.println("Production de 10 or ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(1000);
      }
      else if ( argent ) 
      {
      	t.argent+=10;
      	System.out.println("Production de 10 argent ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(1000);
      }
      else if ( bronze ) 
      {
      	t.bronze+=10;
      	System.out.println("Production de 10 bronze ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(1000);
      }
      }
    	catch (InterruptedException re) { System.out.println(re) ; }
    }
  }

  
}
