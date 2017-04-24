
import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.util.ArrayList;
import java.rmi.* ;
import java.net.MalformedURLException ;



class Client_thread extends Thread {

	protected ClientImpl c;
	protected Product or; // obj 100
	protected Product argent; // obj 500
	protected Product bronze; // obj 1000
	protected Personnalite p;
	protected Coordinateur coord;
	protected int noclient;
	protected int nbclient;

	protected ArrayList<Client> clientlist;

	public void Client_thread(){}

  public void setOptions(ClientImpl c,Personnalite p,int noclient, int nbclient)
  {
	this.c = c; // Parent
  this.p = p;
	this.noclient = noclient;
	this.nbclient = nbclient;
  }

	public void lancement()
	{
		try {
		this.or = (Product) Naming.lookup( "rmi://localhost:6666/Productor" ) ;
		this.argent = (Product) Naming.lookup( "rmi://localhost:6666/Productargent" ) ;
		this.bronze = (Product) Naming.lookup( "rmi://localhost:6666/Productbronze" ) ;
		this.coord= (Coordinateur) Naming.lookup( "rmi://localhost:6666/Coordinateur" ) ;

		this.clientlist = new ArrayList<Client>();

    Client client;
    for ( int i = 1 ; i <= this.nbclient ; i++ )
    {
				if ( i != noclient )
				{
        client = (Client) Naming.lookup( "rmi://localhost:6666/Client"+i );
        clientlist.add(client);
				}
    }

		}
		catch (NotBoundException re) { System.out.println(re) ; }
		catch (RemoteException re) { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ; }

		this.start();
		System.out.println("Lancement de la récolte" );
	}

	public Personnalite getPersonnalite()
	{
		return this.p;
	}

	public void run(){

	try {
	int[] r = c.getAmountRess();

  if ( this.p == Personnalite.CUPIDE )
  {
  		while(r[0] < 100 || r[1] < 100 || r[2] < 100)
    	{

    			if ( r[0] < 100)
    			{
    	  		c.addOr(or.getOr(10));
    	  		sleep(100);
						r = c.getAmountRess();
    	  	}

					//System.out.println("Ressource disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

    	  	if ( r[0] == 100 && r[1] < 100 )
    	  	{
    	  		c.addArgent(argent.getArgent(10));
    	  		sleep(100);
						r = c.getAmountRess();
      		}


      		if ( r[1] == 100 && r[2] < 100 )
      		{
      			c.addBronze(bronze.getBronze(10));
      			sleep(100);
						r = c.getAmountRess();
      		}
					System.out.println("Ressource disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

    	}
  	}
  	else if ( this.p == Personnalite.RUSE )
  	{
  		int recu=1;
  		while(r[0] < 100 || r[1] < 100 || r[2] < 100)
    	{

    			while ( r[0] < 100 && recu != 0)
    			{
    				recu = or.getOr(10);
    	  		c.addOr(recu);
    	  		sleep(100);
						r = c.getAmountRess();
    	  	}
    	  	recu = 1;


    	  	while ( r[1] < 100 && recu != 0 )
    	  	{
    	  		recu = argent.getArgent(10);
    	  		c.addArgent(recu);
    	  		sleep(100);
						r = c.getAmountRess();
      		}
      		recu = 1;


      		while  ( r[2] < 100 && recu != 0 )
      		{
						recu = bronze.getBronze(10);
      			c.addBronze(recu);
      			sleep(100);
						r = c.getAmountRess();
      		}
      		recu = 1;
					System.out.println("Ressource disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

    	}

  	}

	}
	catch (RemoteException re) { System.out.println(re) ; }
	catch (InterruptedException re) { System.out.println(re) ; }

    System.out.println("Ressource disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );
		try
		{
		coord.fini(noclient);
		}catch (RemoteException re) { System.out.println(re) ; }
    System.out.println("J'ai fini " );

  }


}
