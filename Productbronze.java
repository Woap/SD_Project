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
      ProductImpl objLocal = new ProductImpl (false,false,true,0,0,500) ;
      Product_thread t = new Product_thread(); 
      t.setOptions(objLocal,false,false,true);
      Naming.rebind( "rmi://localhost:6666/Productbronze" ,objLocal) ;
      System.out.println("Productbronze pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
