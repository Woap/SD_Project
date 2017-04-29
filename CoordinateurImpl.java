

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
  protected ArrayList<Integer> tableauclassement;
  protected ArrayList<Client> clientlist;
  protected ArrayList<Product> o;
  protected ArrayList<Product> a;
  protected ArrayList<Product> b;

  protected Boolean premier=false;
  protected int ordonnees = 0; // client ordonnees ?
	protected int humain = 0; // humain qui joue ?
  protected int fin = 0; // true = premier qui fini / false = tout le monde à fini
  protected int nbclient=0; // nombre de client

  protected int tourfini=0;

  private final Object lock = new Object();

  protected int nbpor;
	protected int nbpargent;
	protected int nbpbronze;



  public CoordinateurImpl (int nbclient,int ordonnees,int humain, int fin,int por,int pargent,int pbronze)
    throws RemoteException
  {
    super();
    this.nbclient = nbclient;
    this.ordonnees = ordonnees;
    this.humain = humain;
    this.fin = fin;
    this.nbpor = por;
    this.nbpargent= pargent;
    this.nbpbronze= pbronze;
    this.tableauclassement = new ArrayList<Integer>();
    System.out.println("Coordinateur prêt ");
    } ;

  public void lancement()
  throws RemoteException
  {

    try {
      Product p;
      this.o = new ArrayList<Product>();
      this.a = new ArrayList<Product>();
      this.b = new ArrayList<Product>();

      for ( int i = 1 ; i <= this.nbpor ; i++ )
      {
        p = (Product) Naming.lookup( "rmi://localhost:6666/Productor"+i ) ;
        o.add(p);
      }

      for ( int i = 1 ; i <= this.nbpargent ; i++ )
      {
        p = (Product) Naming.lookup( "rmi://localhost:6666/Productargent"+i ) ;
        a.add(p);
      }

      for ( int i = 1 ; i <= this.nbpbronze ; i++ )
      {
        p = (Product) Naming.lookup( "rmi://localhost:6666/Productbronze"+i ) ;
        b.add(p);
      }


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

    for (Client object: clientlist) {
        object.lancement(ordonnees);
      }

      for (Product object: o) {
          object.lancement();
        }

        for (Product object: a) {
            object.lancement();
          }

          for (Product object: b) {
              object.lancement();
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

       for (Product object: o) {
           object.stopProduction();
         }

         for (Product object: a) {
             object.stopProduction();
           }

           for (Product object: b) {
               object.stopProduction();
             }

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

      if ( !premier )
      {
        synchronized (lock) {
        premier = true;
        }

      System.out.println("Le client "+ client + "(" + this.clientlist.get(client-1).getPersonnalite() + ") " + " gagne la partie");
      for (Product object: o) {
          object.stopProduction();
        }

        for (Product object: a) {
            object.stopProduction();
          }

          for (Product object: b) {
              object.stopProduction();
            }
      for (Client object: clientlist) {
          object.stopRecolte();
        }

      }


    }
  }

}
