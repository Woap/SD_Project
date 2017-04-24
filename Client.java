

import java.rmi.Remote ;
import java.rmi.RemoteException ;

public interface Client extends Remote
{
  public void lancement()
    throws RemoteException ;

  public void addOr(int value)
    throws RemoteException ;

  public void addArgent(int value)
    throws RemoteException ;

  public void addBronze(int value)
    throws RemoteException ;

  public int[] getAmountRess()
    throws RemoteException ;

  public Personnalite getPersonnalite()
    throws RemoteException ;


}
