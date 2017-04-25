

import java.net.* ;
import java.rmi.* ;
import java.net.MalformedURLException ;



public class Clienttype
{


  public static void main(String [] args)
  {
    if (args.length != 7)
    {
      System.out.println("Usage : java Client <machine du Serveur> <port du rmiregistry> <noclient> <personnalite> <nbclient> <observation> <vol>") ;
      System.exit(0) ;
    }
    try
    {
      Client_thread t = new Client_thread();
      ClientImpl objLocal = new ClientImpl (t) ;
      Personnalite p = Personnalite.valueOf(args[3]);

      Naming.rebind( "rmi://localhost:6666/Client"+args[2] ,objLocal) ;
      System.out.println("Client "+ args[2] + " de personnalité : " + args[3]);

      t.setOptions(objLocal,p,Integer.parseInt(args[2]),Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]));
      System.out.println("Client " + args[2] +" pret") ;
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
