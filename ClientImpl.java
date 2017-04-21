import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;

public class ClientImpl 
  extends UnicastRemoteObject
{
	public int or = 0; // 100
  public int argent = 0; // 500
  public int bronze = 0 ; // 1000

	public ClientImpl ()
    throws RemoteException
  {
    System.out.println("Ressource disponible : OR -> "+ this.or +" ARGENT -> "+ this.argent +" BRONZE -> "+ this.bronze );
  } ;
  
}
