

import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.util.ArrayList;
import java.rmi.* ;
import java.net.MalformedURLException ;

public class CoordinateurImpl
  extends UnicastRemoteObject
  implements Coordinateur
{


  protected int classement=1;
  protected ArrayList<Boolean> tour;
  protected ArrayList<Integer> tableauclassement;
  protected ArrayList<Client> clientlist;

  protected int ordonnees = 0; // client ordonnees ?
	protected int humain = 0; // humain qui joue ?
  protected int fin = 0; // true = premier qui fini / false = tout le monde à fini
  protected int nbclient=0; // nombre de client

  protected int tourfini=0;

  protected Product o;
  protected Product a;
  protected Product b;



  public CoordinateurImpl (int nbclient,int ordonnees,int humain, int fin)
    throws RemoteException
  {
    super();
    this.nbclient = nbclient;
    this.ordonnees = ordonnees;
    this.humain = humain;
    this.fin = fin;
    tableauclassement = new ArrayList<Integer>();

    if ( this.ordonnees == 1 )
    {
        tour= new ArrayList<Boolean>();
        for ( int i = 0 ; i < nbclient ; i++ )
        {
        		boolean client = false;
        		tour.add(client);
        }
    }
    System.out.println("Coordinateur prêt");

    } ;

  public void lancement()
  throws RemoteException
  {

    try {
    this.o = (Product) Naming.lookup( "rmi://localhost:6666/Productor" ) ;
    this.a = (Product) Naming.lookup( "rmi://localhost:6666/Productargent" ) ;
    this.b = (Product) Naming.lookup( "rmi://localhost:6666/Productbronze" ) ;

    this.clientlist = new ArrayList<Client>();

    Client client;
    for ( int i = 1 ; i <= this.nbclient ; i++ )
    {
        client = (Client) Naming.lookup( "rmi://localhost:6666/Client"+i );
        System.out.println("Client "+i +" " + client.getPersonnalite());
        clientlist.add(client);
    }
    }
    catch (NotBoundException re) { System.out.println(re) ; }
    catch (RemoteException re) { System.out.println(re) ; }
    catch (MalformedURLException e) { System.out.println(e) ; }

    o.lancement();
    a.lancement();
    b.lancement();
    for (Client object: clientlist) {
        object.lancement(ordonnees);

      }

    if ( ordonnees == 1)
      lancementJeuTourParTour();

    System.out.println("Lancement de la partie");

  }

  public void lancementJeuTourParTour()
  throws RemoteException
  {
    while(true)
    {
      for (Client object: clientlist) {
          tourfini=0;
          try{
          object.tonTour();
          while ( tourfini == 0)
          {

            Thread.sleep(100);

          }
          }catch (InterruptedException re) { System.out.println(re) ; }
          catch (RemoteException re) { System.out.println(re) ; }
        }

    }
  }

  public synchronized void tourFini ()
  throws RemoteException
  {
    this.tourfini=1;
  }


  public synchronized void fini (int client)
  throws RemoteException
  {

     if ( fin == 0 )
     {
	   System.out.println("Le client "+ client + "(" + this.clientlist.get(client-1).getPersonnalite() + ") " + " termine à la position "+ classement  );
     classement++;
     tableauclassement.add(client);

     if ( tableauclassement.size() == this.nbclient )
     {
       o.stopProduction();
       a.stopProduction();
       b.stopProduction();

       System.out.println("Fin de la partie");
       System.out.println("Voici le classement");
       for ( int i = 0 ; i < tableauclassement.size() ; i++ )
       {
          System.out.println(i+1 +". Client" + tableauclassement.get(i));
       }
     }

    }
    else
    {
      System.out.println("Le client "+ client + "(" + this.clientlist.get(client-1).getPersonnalite() + ") " + " gagne la partie");
      o.stopProduction();
      a.stopProduction();
      b.stopProduction();

      for (Client object: clientlist) {
          object.stopRecolte();
        }


    }
  }

}
