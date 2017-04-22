


import java.rmi.* ; 
import java.net.MalformedURLException ; 



public class Client 
{
	
	
  public static void main(String [] args)
  {
    if (args.length != 3)
    {
      System.out.println("Usage : java Client <machine du Serveur> <port du rmiregistry> <personnalite>") ;
      System.exit(0) ;
    }
    try
    {
    	//Personnalite p = Personnalite.valueOf(args[2]);
      ClientImpl objLocal = new ClientImpl () ;
      Product o = (Product) Naming.lookup( "rmi://localhost:6666/Productor" ) ;
      Product a = (Product) Naming.lookup( "rmi://localhost:6666/Productargent" ) ;
      Product b = (Product) Naming.lookup( "rmi://localhost:6666/Productbronze" ) ;
      Client_thread t = new Client_thread();
      t.setOptions(objLocal,o,a,b,Integer.parseInt(args[2]));
      
      while (true)
      {
      	try
      	{
      		System.out.println("Ressource acquise : OR -> "+ objLocal.or +" ARGENT -> "+ objLocal.argent +" BRONZE -> "+ objLocal.bronze );
      		Thread.sleep(2500);
      	} catch (InterruptedException re) { System.out.println(re) ; }
      }
      
      
      
    }
    catch (NotBoundException re) { System.out.println(re) ; }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
