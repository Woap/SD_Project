

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

  public void lancement()
  throws RemoteException
  {
    t.lancement();
  }

  public void addOr(int value)
  throws RemoteException
  {
    this.or +=value;
  }

  public void addArgent(int value)
  throws RemoteException
  {
    this.argent +=value;
  }

  public void addBronze(int value)
  throws RemoteException
  {
    this.bronze +=value;
  }

  public int[] getAmountRess()
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

}
