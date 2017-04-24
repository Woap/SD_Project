

import java.rmi.Remote ;
import java.rmi.RemoteException ;

public interface Coordinateur extends Remote
{

  public void fini (int client)
    throws RemoteException ;

  public void lancement()
    throws RemoteException ;


}
