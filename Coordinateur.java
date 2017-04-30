/*
 * \file Coordinateur.java
 * \author IBIS Ibrahim
 * \date 30 avril 2017
 *
 * Interface de la classe Coordinateur
 *
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Coordinateur extends Remote {

    public void fini(int client)
    throws RemoteException;

    public void lancement()
    throws RemoteException;

    public void tourFini()
    throws RemoteException;

    public void lancementJeuTourParTour()
    throws RemoteException;


}
