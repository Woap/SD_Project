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
      ProductImpl objLocal = new ProductImpl (false,true,false,0,200,0) ;
      Product_thread t = new Product_thread(); 
      t.setOptions(objLocal,false,true,false);
      Naming.rebind( "rmi://localhost:6666/Productargent" ,objLocal) ;
      System.out.println("Productargent pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
