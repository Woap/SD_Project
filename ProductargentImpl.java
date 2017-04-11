import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;

public class ProductargentImpl 
  extends UnicastRemoteObject
  implements Product
{

  public int or = 0; // 100
  public int argent = 100; // 500
  public int bronze = 0 ; // 1000

  public ProductargentImpl ()
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
	System.out.println("Un client demande " + argent + " argent " );
	if ( this.argent >= argent  && argent <= 25)
        {
		this.argent -=argent;
		System.out.println("Livraison de la commande de " + argent + " argent " );
		System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
		return argent;
	}
        else
	{
		System.out.println("Ressource insuffisante " );
		System.out.println("Ressource disponible : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
		return 0;
        }
        
  }
 

  public int getBronze(int bronze)
   {	
	if ( this.bronze >= bronze  && bronze <= 50)
		return bronze;
        else
		return 0;
   }

}
