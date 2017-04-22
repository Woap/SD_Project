
import java.net.* ;
import java.rmi.* ;

public class Productor
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
      ProductImpl objLocal = new ProductImpl (true,false,false,5,0,0) ;
      Product_thread t = new Product_thread(); 
      t.setOptions(objLocal,true,false,false);
      Naming.rebind( "rmi://localhost:6666/Productor" ,objLocal) ;
      System.out.println("Productor pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
