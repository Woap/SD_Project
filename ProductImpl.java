

import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;

public class ProductImpl
  extends UnicastRemoteObject
  implements Product
{

  protected int or = 0; // 100
  protected int argent = 0; // 500
  protected int bronze = 0 ; // 1000

  protected boolean Por = false;
	protected boolean Pargent = false;
	protected boolean Pbronze = false;

  protected boolean epuisable=true;

  protected Product_thread t;

  public ProductImpl (Product_thread t,boolean Por, boolean Pargent, boolean Pbronze, int or, int argent, int bronze)
    throws RemoteException
  {
    super() ;
    this.t = t;
    this.Por = Por;
    this.Pargent = Pargent;
    this.Pbronze = Pbronze;

    this.or = or;
    this.argent = argent;
    this.bronze = bronze;

    System.out.println("Ressource disponible : OR -> "+ this.or +" ARGENT -> "+ this.argent +" BRONZE -> "+ this.bronze );
  } ;

  public void lancement()
  throws RemoteException
  {
    t.lancement();
  }


  public synchronized int getOr(int or)
  {
	System.out.println("Un client demande " + or + " or " );
	if ( this.or >= or  && or <= 10)
	{
		this.or -=or;
		System.out.println("Livraison de la commande de " + or + " or " );
		return or;
	}
    else
	{
		System.out.println("Ressource insuffisante " );
		return 0;
	}

  }


  public synchronized int getArgent(int argent)
  {
  System.out.println("Un client demande " + argent + " argent " );
	if ( this.argent >= argent  && argent <= 10)
        {
		this.argent -=argent;
		return argent;
	}
        else
	{
	  System.out.println("Ressource insuffisante " );
		return 0;
        }

  }


  public synchronized int getBronze(int bronze)
   {
   System.out.println("Un client demande " + bronze + " bronze " );
	if ( this.bronze >= bronze  && bronze <= 10)
	{
	this.bronze -=bronze;
	return bronze;

	}
   else
  {
    System.out.println("Ressource insuffisante " );
		return 0;
	}

  }

  public synchronized int[] getAmountRess()
    throws RemoteException
  {
    int[] intArray = new int[3];
    intArray[0] = this.or;
    intArray[1] = this.argent;
    intArray[2] = this.bronze;
    return intArray;


  }

  public void stopProduction()
    throws RemoteException
    {
      t.stopProduction();
    }

}
