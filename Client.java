import java.rmi.* ; 
import java.net.MalformedURLException ; 

public class Client
{
  public static int or = 0; // 100
  public static int argent = 0; // 500
  public static int bronze = 0 ; // 1000  

  public static void main(String [] args)
  {
    if (args.length != 2)
    {
      System.out.println("Usage : java Client <machine du Serveur> <port du rmiregistry>") ;
      System.exit(0) ;
    }
    try
    {
      Product o = (Product) Naming.lookup( "rmi://localhost:6666/Productor" ) ;
      Product a = (Product) Naming.lookup( "rmi://localhost:6666/Productargent" ) ;
      Product b = (Product) Naming.lookup( "rmi://localhost:6666/Productbronze" ) ;
      
      System.out.println("Ressource client : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+ bronze );
      System.out.println("Je recolte 10 or "); or += o.getOr(10);
      
System.out.println("Ressource client après : OR -> "+ or +" ARGENT -> "+ argent +" BRONZE -> "+bronze );
 
      System.out.println("Je recolte 25 argent "); argent += a.getArgent(25);
      System.out.println("Je recolte 10 or "); or += o.getOr(10);
 
      System.out.println("Ressource client après + : OR -> "+or +" ARGENT -> "+argent +" BRONZE -> "+bronze );
      
    }
    catch (NotBoundException re) { System.out.println(re) ; }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
