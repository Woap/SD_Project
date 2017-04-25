
import java.net.* ;
import java.rmi.* ;

public class Productor
{
  public static void main(String [] args)
  {
    if (args.length != 2)
    {
      System.out.println("Usage : java Serveur <port du rmiregistry> <epuisable>") ;
      System.exit(0) ;
    }
    try
    {
      Product_thread t = new Product_thread();
      ProductImpl objLocal = new ProductImpl (t,true,false,false,2,0,0) ;
      Naming.rebind( "rmi://localhost:6666/Productor" ,objLocal) ;
      t.setOptions(objLocal,true,false,false,Integer.parseInt(args[1]));
      System.out.println("Productor pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
