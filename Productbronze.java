

import java.net.* ;
import java.rmi.* ;

public class Productbronze
{
  public static void main(String [] args)
  {
    if (args.length != 1)
    {
      System.out.println("Usage : java Serveur <port du rmiregistry>") ;
      System.exit(0) ;
    }
    try
    {
      Product_thread t = new Product_thread();
      ProductImpl objLocal = new ProductImpl (t,false,false,true,0,0,2) ;
      Naming.rebind( "rmi://localhost:6666/Productbronze" ,objLocal) ;
      t.setOptions(objLocal,false,false,true);
      System.out.println("Productbronze pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
