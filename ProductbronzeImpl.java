import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;

public class ProductbronzeImpl 
  extends UnicastRemoteObject
  implements Product
{

  public int or = 0; // 100
  public int argent = 0; // 500
  public int bronze = 100 ; // 1000

  public ProductbronzeImpl ()
    throws RemoteException
  {
    super() ;
    System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
  } ;

  public String messageDistant()
    throws RemoteException
  { 
    return( "Salut" ) ;
  }


  public int getOr(int or)
  {	
	if ( this.or >= or  && or <= 10)
	{
		this.or -=or;
                System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
		return or;
	}
        else
	{
		System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
		return 0;
	}
        
  }


  public int getArgent(int argent)
  {	
	if ( this.argent >= argent  && argent <= 25)
        {
		this.argent -=argent;
		System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
		return argent;
	}
        else
	{
		System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
		return 0;
        }
        
  }
 

  public int getBronze(int bronze)
   {	
	System.out.println("Un client demande " + bronze + " bronze " );
	if ( this.bronze >= bronze  && bronze <= 50)
	{
		this.bronze -=bronze;
		System.out.println("Livraison de la commande de" + bronze + " bronze " );
		System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );

		return bronze;
	}        
	else
	{
		System.out.println("Ressource insuffisante " );
		System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
		return 0;
   	}
   }

}
