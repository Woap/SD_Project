import java.net.* ;
import java.rmi.* ;

public class Producteur2
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
      ProductImpl objLocal = new ProductImpl () ;
      Naming.rebind( "rmi://localhost:6666/Productargent" ,objLocal) ;
      System.out.println("Producteur pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
