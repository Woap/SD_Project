

import java.net.* ;
import java.rmi.* ;

public class Productargent
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
      ProductImpl objLocal = new ProductImpl (t,false,true,false,0,2,0) ;
      Naming.rebind( "rmi://localhost:6666/Productargent" ,objLocal) ;
      t.setOptions(objLocal,false,true,false);
      System.out.println("Productargent pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
