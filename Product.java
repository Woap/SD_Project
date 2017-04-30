/*
 * \file Product.java
 * \author IBIS Ibrahim
 * \date 30 avril 2017
 *
 * Interface de la classe Product
 *
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Product extends Remote {

    public int getOr(int or)
    throws RemoteException;

    public int getArgent(int argent)
    throws RemoteException;

    public int getBronze(int bronze)
    throws RemoteException;

    public void lancement()
    throws RemoteException;

    public int[] getAmountRess()
    throws RemoteException;

    public void stopProduction()
    throws RemoteException;
}
