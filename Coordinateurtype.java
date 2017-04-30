/*
 * \file Coordinateurtype.java
 * \author IBIS Ibrahim
 * \date 30 avril 2017
 *
 * Classe permettant le lancement et instanciation des différentes classes liées au coordinateur
 *
 */

import java.rmi.*;
import java.util.ArrayList;
import java.net.MalformedURLException;


public class Coordinateurtype {

    public static void main(String[] args) {
        if (args.length != 8) {
            System.out.println("Usage : java Coordinateur <port du rmiregistry> <nbclients> <ordonnees> <humain> <fin> <nbpor> <nbpargen> <nbpbronze>");
            System.exit(0);
        }

        System.out.println("-Coordinateur-");
        System.out.println("Nombre de clients en jeu : " + Integer.parseInt(args[1]));
        System.out.println("Nombre producteur d'or : " + Integer.parseInt(args[5]));
        System.out.println("Nombre producteur d'argent : " + Integer.parseInt(args[6]));
        System.out.println("Nombre producteur de bronze : " + Integer.parseInt(args[7]));

        try {
            Coordinateur_thread t = new Coordinateur_thread();
            CoordinateurImpl objLocal = new CoordinateurImpl(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]), t);
            Naming.rebind("rmi://localhost:6666/Coordinateur", objLocal);
            objLocal.lancement(); // Lancement de la partie

        } catch (RemoteException re) {
            System.out.println(re);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
    }
}
