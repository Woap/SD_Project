/*
 * \file CoordinateurImpl.java
 * \author IBIS Ibrahim
 * \date 30 avril 2017
 *
 * Implementation de la classe Coordinateur
 *
 */

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.*;
import java.net.MalformedURLException;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;

public class CoordinateurImpl
extends UnicastRemoteObject
implements Coordinateur {


    protected int classement = 1;
    protected ArrayList < Integer > tableauclassement;
    protected ArrayList < Client > clientlist; // Liste clients
    protected ArrayList < Product > o; // Liste producteur d'or
    protected ArrayList < Product > a; // Liste producteur d'argent'
    protected ArrayList < Product > b; // Liste producteur de bronze

    protected Boolean premier = false;
    protected int ordonnees = 0; // client ordonnees ?
    protected int humain = 0; // humain qui joue ?
    protected int fin = 0; // Mode fin de partie
    protected int nbclient = 0; // nombre de client

    protected int tourfini = 0;
    protected int fini =0;

    private final Object lock = new Object();
    private final Object lock2 = new Object();
    protected Coordinateur_thread t;

    protected int nbpor; // Nombre producteur d'or
    protected int nbpargent;// Nombre producteur d'argent'
    protected int nbpbronze; // Nombre  producteur de bronze



    public CoordinateurImpl(int nbclient, int ordonnees, int humain, int fin, int por, int pargent, int pbronze, Coordinateur_thread t)
    throws RemoteException {
        super();
        this.nbclient = nbclient;
        this.ordonnees = ordonnees;
        this.humain = humain;
        this.fin = fin;
        this.nbpor = por;
        this.nbpargent = pargent;
        this.nbpbronze = pbronze;
        this.t = t;
        this.tableauclassement = new ArrayList < Integer > ();
        System.out.println("Coordinateur prêt ");
    };

    /**
    * \brief Methode permettant lorsque tout les clients et producteurs sont prêts leurs lancements et donc le lancement de la partie
    */
    public void lancement()
    throws RemoteException {

        try {
            Product p;
            this.o = new ArrayList < Product > ();
            this.a = new ArrayList < Product > ();
            this.b = new ArrayList < Product > ();

            for (int i = 1; i <= this.nbpor; i++) {
                p = (Product) Naming.lookup("rmi://localhost:6666/Productor" + i);
                o.add(p);
            }

            for (int i = 1; i <= this.nbpargent; i++) {
                p = (Product) Naming.lookup("rmi://localhost:6666/Productargent" + i);
                a.add(p);
            }

            for (int i = 1; i <= this.nbpbronze; i++) {
                p = (Product) Naming.lookup("rmi://localhost:6666/Productbronze" + i);
                b.add(p);
            }


            this.clientlist = new ArrayList < Client > ();


            Client client;
            for (int i = 1; i <= this.nbclient; i++) {
                client = (Client) Naming.lookup("rmi://localhost:6666/Client" + i);
                System.out.println("Client " + i + " " + client.getPersonnalite());
                clientlist.add(client);


            }
        } catch (NotBoundException re) {
            System.out.println(re);
        } catch (RemoteException re) {
            System.out.println(re);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

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

        if (ordonnees == 1)
        {
            t.lancement();
            lancementJeuTourParTour();
        }
        else
        {
          t.lancement();
          System.out.println("Lancement de la partie");
        }



    }


    /**
    *
    * \brief Cas partie tour par tour
    */
    public void lancementJeuTourParTour()
    throws RemoteException {
        while (fini == 0) {

            for (Client object: clientlist) {
                tourfini = 0;
                try {

                    object.tonTour();

                    while (tourfini == 0) {

                        Thread.sleep(100);

                    }
                } catch (InterruptedException re) {
                    System.out.println(re);
                } catch (RemoteException re) {
                    System.out.println(re);
                }
            }


        }
    }

    public synchronized void tourFini()
    throws RemoteException {
        this.tourfini = 1;
    }


    /**
    * \brief Methode utilisé par les clients pour signaler leurs fins.
    */
    public synchronized void fini(int client)
    throws RemoteException {

        if (fin == 0) {

            System.out.println("Le client " + client + "(" + this.clientlist.get(client - 1).getPersonnalite() + ") " + " termine à la position " + classement);

            classement++;
            tableauclassement.add(client);





            if (tableauclassement.size() == this.nbclient) {


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
                System.out.println("Durée de la partie : " + t.getTime() + "ms");
                System.out.println("Voici le classement");


                for (int i = 0; i < tableauclassement.size(); i++) {
                    System.out.println(i + 1 + ". Client" + tableauclassement.get(i));

                }
                this.fini = 1;

            }

        } else {

            if (!premier) {
                synchronized(lock) {
                    premier = true;
                }
                System.out.println("Le client " + client + "(" + this.clientlist.get(client - 1).getPersonnalite() + ") " + " gagne la partie");
                System.out.println("Durée de la partie : " + t.getTime() + "ms");
                for (Client object: clientlist) {
                    object.stopRecolte();
                    object.tonTour();
                }

                for (Product object: o) {
                    object.stopProduction();
                }

                for (Product object: a) {
                    object.stopProduction();
                }

                for (Product object: b) {
                    object.stopProduction();
                }

                this.fini = 1;
            }
        }
    }
}
