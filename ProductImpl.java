

import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;

public class ProductImpl 
  extends UnicastRemoteObject
  implements Product
{

  public int or = 0; // 100
  public int argent = 0; // 500
  public int bronze = 0 ; // 1000
  
  protected boolean Por = false;
	protected boolean Pargent = false;
	protected boolean Pbronze = false;

  public ProductImpl (boolean Por, boolean Pargent, boolean Pbronze, int or, int argent, int bronze)
    throws RemoteException
  {
    super() ;
    this.Por = Por;
    this.Pargent = Pargent;
    this.Pbronze = Pbronze;
    
    this.or = or;
    this.argent = argent;
    this.bronze = bronze;
    System.out.println("Ressource disponible : OR -> "+ this.or +" ARGENT -> "+ this.argent +" BRONZE -> "+ this.bronze );
  } ;

  public String messageDistant()
    throws RemoteException
  { 
    return( "Salut" ) ;
  }


  public int getOr(int or)
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


  public int getArgent(int argent)
  {	
  System.out.println("Un client demande " + argent + " argent " );
	if ( this.argent >= argent  && argent <= 25)
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
 

  public int getBronze(int bronze)
   {
   System.out.println("Un client demande " + bronze + " bronze " );	
	if ( this.bronze >= bronze  && bronze <= 50)
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

}
