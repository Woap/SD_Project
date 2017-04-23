
import java.rmi.* ;
import java.net.MalformedURLException ;



class Client_thread extends Thread {



	public ClientImpl c;
	protected Product or; // obj 100
	protected Product argent; // obj 500
	protected Product bronze; // obj 1000
	public Personnalite p;

	public void Client_thread(){
  }

  public void setOptions(ClientImpl c,Product o, Product a, Product b,Personnalite p)
  {
	this.c = c; // Parent
	this.or = o; // Producteur d'or
  this.argent = a; // Producteur d'argent
  this.bronze = b; // Producteur de bronze
  this.p = p;



  }

	public void lancement()
	{
		this.start();
		System.out.println("Lancement de la récolte" );
	}



	public void run(){

  if ( this.p == Personnalite.CUPIDE )
  {
  		while(c.or < 100 || c.argent < 100 || c.bronze < 100)
    	{
      	try
    		{
    			if ( c.or < 100)
    			{
    	  		c.or += or.getOr(10);
    	  		sleep(100);
    	  	}
					System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );

    	  	if ( c.or == 100 && c.argent < 100 )
    	  	{
    	  		c.argent += argent.getArgent(10);
    	  		sleep(100);
      		}
					System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );

      		if ( c.argent == 100 && c.bronze < 100 )
      		{
      			c.bronze += bronze.getBronze(10);
      			sleep(100);
      		}
      	}
    		catch (InterruptedException re) { System.out.println(re) ; }
    		catch (RemoteException re) { System.out.println(re) ; }

    	}
  	}
  	else if ( this.p == Personnalite.RUSE )
  	{
  		int recu=1;
  		while(c.or < 100 || c.argent < 100 || c.bronze < 100)
    	{
      	try
    		{
					System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );
    			while ( c.or < 100 && recu != 0)
    			{
    				recu = or.getOr(10);
    	  		c.or += recu;
    	  		sleep(100);
    	  	}
    	  	recu = 1;

					System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );
    	  	while ( c.argent < 100 && recu != 0 )
    	  	{
    	  		recu = argent.getArgent(10);
    	  		c.argent += recu;
    	  		sleep(100);
      		}
      		recu = 1;

					System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );
      		while  ( c.bronze < 100 && recu != 0 )
      		{
						recu = bronze.getBronze(10);
      			c.bronze += recu;
      			sleep(100);
      		}
      		recu = 1;
      	}
    		catch (InterruptedException re) { System.out.println(re) ; }
    		catch (RemoteException re) { System.out.println(re) ; }

    	}

  	}





    System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );

    System.out.println("J'ai fini " );

  }


}
