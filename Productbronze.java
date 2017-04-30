/*
 * \file Client_thread.java
 * \author IBIS Ibrahim
 * \date 30 avril 2017
 *
 * Classe permettant le lancement et instanciation des différentes classes liées au producteur de bronze
 *
 */

import java.net.*;
import java.rmi.*;

public class Productbronze {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage : java Serveur <port du rmiregistry> <epuisable> <nb>");
            System.exit(0);
        }
        try {
            Product_thread t = new Product_thread();
            Product_log_thread t_log = new Product_log_thread();
            ProductImpl objLocal = new ProductImpl(t, false, false, true, 0, 0, 2);
            Naming.rebind("rmi://localhost:6666/Productbronze" + args[2], objLocal);
            t.setOptions(objLocal, false, false, true, Integer.parseInt(args[1]), t_log);
            t_log.setOptions(objLocal, Integer.parseInt(args[2]), 2);
            System.out.println("Productbronze " + args[2] + " pret");
        } catch (RemoteException re) {
            System.out.println(re);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
    }
}
