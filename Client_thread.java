

import java.rmi.* ; 
import java.net.MalformedURLException ; 

class Client_thread extends Thread {

	public ClientImpl c;
	protected Product or; // obj 100
	protected Product argent; // obj 500
	protected Product bronze; // obj 1000
	
	public void Client_thread(){  
  }
  
  public void setOptions(ClientImpl c,Product o, Product a, Product b)
  {	
	this.c = c; // Parent
	this.or = o; // Producteur d'or
  this.argent = a; // Producteur d'argent 
  this.bronze = b; // Producteur de bronze 
  
	this.start();
	System.out.println("Lancement de la récolte" );
	     
  }
   
  

  public void run(){
    while(c.or < 100 || c.argent < 500 || c.bronze < 1000)
    {
      try
    	{
    		if ( c.or < 100)
    		{
    	  	c.or += or.getOr(10);
    	  	sleep(100);
    	  }
    	  
    	  if ( c.or == 100 && c.argent < 500 )
    	  {
    	  	c.argent += argent.getArgent(10);
    	  	sleep(100);
      	}
      	
      	if ( c.argent == 500 && c.bronze < 1000 )
      	{
      		c.bronze += bronze.getBronze(10);
      		sleep(100);
      	}	
      }
    	catch (InterruptedException re) { System.out.println(re) ; }
    	catch (RemoteException re) { System.out.println(re) ; }
    	 
    }
    System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );
    System.out.println("J'ai fini " );
  }

  
}
