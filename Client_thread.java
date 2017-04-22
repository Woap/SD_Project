
import java.rmi.* ; 
import java.net.MalformedURLException ; 



class Client_thread extends Thread {

	

	public ClientImpl c;
	protected Product or; // obj 100
	protected Product argent; // obj 500
	protected Product bronze; // obj 1000
	protected int p;
	//public Personnalite p;
	
	public void Client_thread(){  
  }
  
  public void setOptions(ClientImpl c,Product o, Product a, Product b,int p)
  {	
	this.c = c; // Parent
	this.or = o; // Producteur d'or
  this.argent = a; // Producteur d'argent 
  this.bronze = b; // Producteur de bronze
  this.p = p; 
  
	this.start();
	System.out.println("Lancement de la récolte" );
	     
  }
   
  

  public void run(){
  
  switch (this.p) {
  	case /*CUPIDE*/ 0:
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
    	  		c.argent += argent.getArgent(25);
    	  		sleep(100);
      		}
      	
      		if ( c.argent == 500 && c.bronze < 1000 )
      		{
      			c.bronze += bronze.getBronze(25);
      			sleep(100);
      		}	
      	}
    		catch (InterruptedException re) { System.out.println(re) ; }
    		catch (RemoteException re) { System.out.println(re) ; }
    	 
    	}
  		break;
  		
  	case /*RUSE*/ 1:
  		int recu=1;
  		while(c.or < 100 || c.argent < 500 || c.bronze < 1000)
    	{
      	try
    		{
    			while ( c.or < 100 && recu != 0)
    			{
    				recu = or.getOr(10);
    	  		c.or += recu;
    	  		sleep(100);
    	  	}
    	  	recu = 1;
    	  
    	  	while ( c.argent < 500 && recu != 0 )
    	  	{
    	  		recu = argent.getArgent(25);
    	  		c.argent += recu;
    	  		sleep(100);
      		}
      		recu = 1;
      	
      		while  ( c.bronze < 1000 && recu != 0 )
      		{
      			c.bronze += bronze.getBronze(25);
      			sleep(100);
      		}	
      		recu = 1;
      	}
    		catch (InterruptedException re) { System.out.println(re) ; }
    		catch (RemoteException re) { System.out.println(re) ; }
    	 
    	}
  	
  		break;
  		
  	default :
  		break;
  	
  	}
    
    System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );
    System.out.println("J'ai fini " );
  }

  
}
