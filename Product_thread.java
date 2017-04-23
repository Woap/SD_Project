
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



  }

	public void lancement()
	{
		this.start();
		System.out.println("Lancement de la production" );
	}


	public void run(){
    while(true)
    {
      try
    {
    	if (or && t.or < 10000)
    	{
      	t.or+=(t.or/2)+1;
      	System.out.println("Production de 10 or ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(300);
      }
      else if ( argent && t.argent < 10000)
      {
      	t.argent+=(t.argent/2)+1;
      	System.out.println("Production de 10 argent ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(300);
      }
      else if ( bronze && t.bronze < 10000)
      {
      	t.bronze+=(t.bronze/2)+1;
      	System.out.println("Production de 10 bronze ");
      	System.out.println("Ressource disponible : OR -> "+ t.or +" ARGENT -> "+ t.argent +" BRONZE -> "+ t.bronze );
      	sleep(300);
      }
      }
    	catch (InterruptedException re) { System.out.println(re) ; }
    }
  }


}
