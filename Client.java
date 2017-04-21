


import java.rmi.* ; 
import java.net.MalformedURLException ; 


public class Client
{

  public static void main(String [] args)
  {
    if (args.length != 2)
    {
      System.out.println("Usage : java Client <machine du Serveur> <port du rmiregistry>") ;
      System.exit(0) ;
    }
    try
    {
      ClientImpl objLocal = new ClientImpl () ;
      Product o = (Product) Naming.lookup( "rmi://localhost:6666/Productor" ) ;
      Product a = (Product) Naming.lookup( "rmi://localhost:6666/Productargent" ) ;
      Product b = (Product) Naming.lookup( "rmi://localhost:6666/Productbronze" ) ;
      Client_thread t = new Client_thread();
      t.setOptions(objLocal,o,a,b);
      
      
      
    }
    catch (NotBoundException re) { System.out.println(re) ; }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
