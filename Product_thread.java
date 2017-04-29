
import java.rmi.* ;
import java.net.MalformedURLException ;

class Product_thread extends Thread {

	public ProductImpl t;
	protected boolean or = false;
	protected boolean argent = false;
	protected boolean bronze = false;
	protected Coordinateur coord;

	protected boolean epuisable = true ;
	protected boolean stop = false;
	Product_log_thread t_log;

	public void Product_thread(){
  }

  public void setOptions(ProductImpl t,boolean or, boolean argent, boolean bronze,int epuisable,Product_log_thread t_log)
  {
	this.t = t; // Parent
	this.or = or; // Production d'or ?
  this.argent = argent; // Production d'argent ?
  this.bronze = bronze; // Production de bronze ?
	this.t_log =t_log;
	if ( epuisable == 0 ) this.epuisable = false;
  }



	public void lancement()
	{
		try{this.coord = (Coordinateur) Naming.lookup( "rmi://localhost:6666/Coordinateur" ) ;}
		catch (NotBoundException re) { System.out.println(re) ; }
		catch (RemoteException re) { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ; }
		t_log.lancement();
		this.start();
		System.out.println("Lancement de la production" );
	}

	public void stopProduction()
	{
		this.stop = true;
	}


	public void run(){
    while(!this.stop)
    {
      try
    	{
			int prod=0;
    	if (or && t.or < 10000)
    	{
				if ( epuisable )
				{
					prod = (t.or/2)+1;
      		t.or+=prod;
				}
				else
				{
					prod = 10000;
					t.or+=prod;
				}

				if ( t.or > 10000 ) t.or = 10000;
      	System.out.println("Production de " + prod + " or ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(100);
      }
      else if ( argent && t.argent < 10000)
      {
				if ( epuisable )
				{
					prod = (t.argent/2)+1;
      		t.argent+=prod;
				}
				else
				{
					prod = 10000;
					t.argent+=prod;
				}

				if ( t.argent > 10000 ) t.argent = 10000;
      	System.out.println("Production de " + prod + " argent ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(100);
      }
      else if ( bronze && t.bronze < 10000)
      {
				if ( epuisable )
				{
					prod = (t.bronze/2)+1;
      		t.bronze+=prod;
				}
				else
				{
					prod = 10000;
					t.bronze+=prod;
				}

				if ( t.bronze > 10000 ) t.bronze = 10000;
      	System.out.println("Production de " + prod + " bronze ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(100);
      }
			sleep(50);
      }
    	catch (InterruptedException re) { System.out.println(re) ; }

    }
		t_log.arret_log();
		System.out.println("Fin de la production");
  }


}
