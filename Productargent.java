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
      ProductargentImpl objLocal = new ProductargentImpl () ;
      Naming.rebind( "rmi://localhost:6666/Productargent" ,objLocal) ;
      System.out.println("Productargent pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
