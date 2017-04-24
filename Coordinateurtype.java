


import java.rmi.* ;
import java.util.ArrayList;
import java.net.MalformedURLException ;



public class Coordinateurtype
{

  public static void main(String [] args)
  {
    if (args.length != 5)
    {
      System.out.println("Usage : java Coordinateur <port du rmiregistry> <nbclients> <ordonnees> <humain> <fin>") ;
      System.exit(0) ;
    }

    System.out.println("-Coordinateur-");
    System.out.println("Nombre de clients en jeu : "+ Integer.parseInt(args[1]));

    try
    {
      CoordinateurImpl objLocal = new CoordinateurImpl (Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4])) ;
      Naming.rebind( "rmi://localhost:6666/Coordinateur" ,objLocal) ;
      objLocal.lancement(); // Lancement de la partie
    }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
