

import java.net.* ;
import java.rmi.* ;

public class Productargent
{
  public static void main(String [] args)
  {
    if (args.length != 3)
    {
      System.out.println("Usage : java Serveur <port du rmiregistry> <epuisable> <nb>") ;
      System.exit(0) ;
    }
    try
    {
      Product_thread t = new Product_thread();
      Product_log_thread t_log = new Product_log_thread();
      ProductImpl objLocal = new ProductImpl (t,false,true,false,0,2,0) ;
      Naming.rebind( "rmi://localhost:6666/Productargent"+args[2] ,objLocal) ;
      t.setOptions(objLocal,false,true,false,Integer.parseInt(args[1]),t_log);
      t_log.setOptions(objLocal,Integer.parseInt(args[2]),1);
      System.out.println("Productargent " +args[2] +  " pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
