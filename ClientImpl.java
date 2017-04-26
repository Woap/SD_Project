

import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;

public class ClientImpl
  extends UnicastRemoteObject
  implements Client
{
	protected int or = 0; // 100
  protected int argent = 0; // 500
  protected int bronze = 0 ; // 1000
  protected Client_thread t;

	public ClientImpl (Client_thread t)
    throws RemoteException
  {
    super();
    this.t = t;
  	System.out.println("Lancement d'un client");
    System.out.println("Ressource acquise : OR -> "+ this.or +" ARGENT -> "+ this.argent +" BRONZE -> "+ this.bronze );
  } ;

  public void lancement(int ordonnees)
  throws RemoteException
  {
    t.lancement(ordonnees);
  }

  public void stopRecolte()
    throws RemoteException
  {
    t.stopRecolte();
  }

  public Tache getTacheActuel()
    throws RemoteException
  {
    return t.getTacheActuel();
  }

  public synchronized void addOr(int value)
  throws RemoteException
  {
    this.or +=value;
  }

  public synchronized void addArgent(int value)
  throws RemoteException
  {
    this.argent +=value;
  }

  public synchronized void addBronze(int value)
  throws RemoteException
  {
    this.bronze +=value;
  }

  public synchronized int volOr(int value)
  throws RemoteException
  {
    if ( this.or != 100 && this.argent != 100 && this.bronze != 100)
    {
      if ( t.getTacheActuel() != Tache.OBSERVATION ) // Si pendant le vol je suis en observation
      {

      if ( this.or >= value  && value <= 10 )
      {
        System.out.println("Je me fait voler "+ value + " or" );
        this.or -=value;
        return value;
      }
      else
        return 0;

      }
      else
      {
      System.out.println("J'étais en observation pendant le vol, pénalité pour le voleur" );
      return -1; // Pénalité
      }
    }
    else
      return 0;
  }

  public synchronized int volArgent(int value)
  throws RemoteException
  {
    if ( this.or != 100 && this.argent != 100 && this.bronze != 100)
    {
      if ( t.getTacheActuel() != Tache.OBSERVATION ) // Si pendant le vol je suis en observation
      {

        if ( this.argent >= value  && value <= 10)
        {
          System.out.println("Je me fait voler "+ value + " argent" );
          this.argent -=value;
          return value;
        }
        else
        return 0;
      }
      else
      {
      System.out.println("J'étais en observation pendant le vol, pénalité pour le voleur" );
      return -1; // Pénalité
      }
    }
    else
      return 0;
  }

  public synchronized int volBronze(int value)
  throws RemoteException
  {
    if ( this.or != 100 && this.argent != 100 && this.bronze != 100)
    {
      if ( (t.getTacheActuel() != Tache.OBSERVATION) ) // Si pendant le vol je suis en observation
      {
      if ( this.bronze >= value && value <= 10)
      {
      System.out.println("Je me fait voler "+ value + " bronze" );
      this.bronze -=value;
      return value;
      }
      else
      return 0;

      }
      else
      {
      System.out.println("J'étais en observation pendant le vol, pénalité pour le voleur" );
      return -1; // Pénalité
      }
    }
    else
      return 0;
  }

  public synchronized int[] getAmountRess()
    throws RemoteException
  {
    int[] intArray = new int[3];
    intArray[0] = this.or;
    intArray[1] = this.argent;
    intArray[2] = this.bronze;

    return intArray;
  }

  public Personnalite getPersonnalite()
	{
		return t.getPersonnalite();
	}

  public void tonTour()
    throws RemoteException
  {
    t.tonTour();
  }

}
