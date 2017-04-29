
import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.util.ArrayList;
import java.rmi.* ;
import java.net.MalformedURLException ;
import java.util.Date;
import java.io.PrintWriter;
import java.io.IOException;

// ./construit 0 0 1 1 0 1 1 1 1 0 1 1 1 1



class Client_thread extends Thread {

	protected ClientImpl c;
	protected ArrayList<Product> or; // obj 100
	protected ArrayList<Product> argent; // obj 500
	protected ArrayList<Product> bronze; // obj 1000
	protected Personnalite p;
	protected Tache t = Tache.RECOLTE;
	protected Coordinateur coord;
	protected int noclient;
	protected int nbclient;
	protected int nbpor;
	protected int nbpargent;
	protected int nbpbronze;

	protected int ordonnees;
	protected boolean recolte= false;


	protected boolean observation=false;
	protected boolean vol=false;

	protected boolean stop=false;

	protected Client_log_thread log;
	protected ArrayList<Client> clientlist;

	public void Client_thread(){}

  public void setOptions(ClientImpl c,Personnalite p,int noclient, int nbclient,int por,int pargent,int pbronze, int observation, int vol,Client_log_thread log)
  {
	this.log = log;
	this.c = c; // Parent
  this.p = p;
	this.noclient = noclient;
	this.nbclient = nbclient;
	this.nbpor = por;
	this.nbpargent= pargent;
	this.nbpbronze= pbronze;


	if ( observation == 1 ) this.observation = true;
	if ( vol == 1) this.vol = true;
  }

	public void lancement(int ordonnees)
	{
		this.ordonnees = ordonnees;
		try {

			this.or = new ArrayList<Product>();
      this.argent = new ArrayList<Product>();
      this.bronze = new ArrayList<Product>();

			Product p;

      for ( int i = 1 ; i <= this.nbpor ; i++ )
      {
        p = (Product) Naming.lookup( "rmi://localhost:6666/Productor"+i ) ;
        or.add(p);
      }

      for ( int i = 1 ; i <= this.nbpargent ; i++ )
      {
        p = (Product) Naming.lookup( "rmi://localhost:6666/Productargent"+i ) ;
        argent.add(p);
      }

      for ( int i = 1 ; i <= this.nbpbronze ; i++ )
      {
        p = (Product) Naming.lookup( "rmi://localhost:6666/Productbronze"+i ) ;
        bronze.add(p);
      }

		this.coord= (Coordinateur) Naming.lookup( "rmi://localhost:6666/Coordinateur" ) ;

		this.clientlist = new ArrayList<Client>();

    Client client;
    for ( int i = 1 ; i <= this.nbclient ; i++ )
    {
				if ( i != noclient )
				{
        client = (Client) Naming.lookup( "rmi://localhost:6666/Client"+i );
        clientlist.add(client);
				}
    }

		}
		catch (NotBoundException re) { System.out.println(re) ; }
		catch (RemoteException re) { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ; }
		log.lancement();
		this.start();
		System.out.println("Lancement de la récolte" );
	}

	public Personnalite getPersonnalite()
	{
		return this.p;
	}

	public Tache getTacheActuel()
  {
    return this.t;
  }

	public void stopRecolte()
	{
		this.stop=true;
	}

	public void attenteTour()
	{
		recolte=false;
		while ( recolte == false)
		{
			try{
			Thread.sleep(100);
			}catch (InterruptedException re) { System.out.println(re) ; }
		}
	}

	public void tonTour()
	{
		System.out.println("C'est mon tour");
		recolte=true;
	}


	public void run(){

	try {
	int[] r = c.getAmountRess();
	int[] productress;
	int ior=0;
	int iar=0;
	int ibr=0;
	if ( ordonnees == 1 ) attenteTour();
  if ( this.p == Personnalite.INDIVIDUALISTE )
  {
  		while( !this.stop && (r[0] < 100 || r[1] < 100 || r[2] < 100) )
    	{
    			if (!this.stop &&  r[0] < 100)
    			{
    	  		c.addOr(or.get(ior).getOr(10));


						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						r = c.getAmountRess();

    	  	}

    	  	if (!this.stop && r[0] == 100 && r[1] < 100 )
    	  	{
    	  		c.addArgent(argent.get(iar).getArgent(10));
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						r = c.getAmountRess();

      		}


      		if (!this.stop && r[1] == 100 && r[2] < 100 )
      		{
      			c.addBronze(bronze.get(ibr).getBronze(10));
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
      			sleep(100);
						r = c.getAmountRess();

      		}
					System.out.println("Ressource disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

    	}
  	}
  	else if ( this.p == Personnalite.COOPERATIF )
  	{
  		int recu=1;
  		while( !this.stop && (r[0] < 100 || r[1] < 100 || r[2] < 100) )
    	{

    			while (!this.stop &&  r[0] < 100 && recu != 0)
    			{
    				recu = or.get(ior).getOr(10);
    	  		c.addOr(recu);
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						r = c.getAmountRess();
						ior= (ior + 1) %(nbpor);
    	  	}
    	  	recu = 1;
					System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

    	  	while (!this.stop && r[1] < 100 && recu != 0 )
    	  	{
    	  		recu = argent.get(iar).getArgent(10);
    	  		c.addArgent(recu);
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						r = c.getAmountRess();
						iar= (iar + 1) %(nbpargent);

      		}
      		recu = 1;
					System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

      		while  (!this.stop&& r[2] < 100 && recu != 0 )
      		{
						recu = bronze.get(ibr).getBronze(10);
      			c.addBronze(recu);
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
      			sleep(100);
						r = c.getAmountRess();
						ibr= (ibr + 1) %(nbpbronze);
      		}
      		recu = 1;
					System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

    	}

  	}
		else if ( this.p == Personnalite.VOLEUR )
  	{
			int recu=1;
			int vol=0;
			int i=0;
  		while( !this.stop && (r[0] < 100 || r[1] < 100 || r[2] < 100) )
    	{

    			while ( !this.stop && r[0] < 100 && recu != 0)
    			{
    				recu = or.get(ior).getOr(10);
    	  		c.addOr(recu);
						if (recu != 0)
							System.out.println("Je recupère 10 or");
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						r = c.getAmountRess();

    	  	}

					// VOL SI JAMAIS PLUS D'OR DISPONIBLE
					if ( !this.stop && this.vol )
					{
					if ( r[0] < 100 )
					{
					t = Tache.VOL;
					vol = clientlist.get(i %(nbclient-1)).volOr(10);
					t = Tache.RECOLTE;

					if (vol >= 0)
						System.out.println("Je vole 10 or" + " du client "+ (i+1));
					if ( vol != -1)
					{
						c.addOr(vol);
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
					}
					else
					{
						System.out.println("Je me suis fait voir pendant le vol, pénalité de 2 sec");
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
						sleep(2000);
					}
					sleep(100);
					r = c.getAmountRess();
					i= (i+1) %(nbclient-1) ;
					}
					}

					ior= (ior + 1) %(nbpor);




    	  	recu = 1;


    	  	while ( !this.stop && r[1] < 100 && recu != 0 )
    	  	{
    	  		recu = argent.get(iar).getArgent(10);
    	  		c.addArgent(recu);
						if (recu != 0)
							System.out.println("Je recupère 10 argent");
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						r = c.getAmountRess();
      		}

					// VOL SI JAMAIS PLUS D'ARGENT DISPONIBLE
					if ( !this.stop && this.vol )
					{
					if ( r[1] < 100 )
					{
					t = Tache.VOL;
					vol = clientlist.get(i %(nbclient-1)).volArgent(10);
					t = Tache.RECOLTE;
					if (vol >= 0)
					System.out.println("Je vole 10 argent" + " du client "+ (i+1));
					if ( vol != -1)
					{
						c.addArgent(vol);
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
					}
						else
						{
							System.out.println("Je me suis fait voir pendant le vol, pénalité de 2 sec");
							if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
							sleep(2000);
						}

					r = c.getAmountRess();
					sleep(100);
					i= (i+1) %(nbclient-1) ;
					}
					}


					iar= (iar + 1) %(nbpargent);


      		recu = 1;


      		while  ( !this.stop && r[2] < 100 && recu != 0 )
      		{
						recu = bronze.get(ibr).getBronze(10);
      			c.addBronze(recu);
						if (recu != 0)
							System.out.println("Je recupère 10 bronze");
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
      			sleep(100);
						r = c.getAmountRess();
      		}

					// VOL SI JAMAIS PLUS DE BRONZE DISPONIBLE
					if ( !this.stop && this.vol )
					{
					if ( r[2] < 100 )
					{
					t = Tache.VOL;
					vol = clientlist.get(i %(nbclient-1)).volBronze(10);
					t = Tache.RECOLTE;
					if (vol >= 0)
						System.out.println("Je vole 10 bronze" + " du client "+ (i+1));
					if ( vol != -1)
					{
						c.addBronze(vol);
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
					}
					else
					{
						System.out.println("Je me suis fait voir pendant le vol, pénalité de 2 sec");
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
						sleep(2000);
					}

					r = c.getAmountRess();
					sleep(100);
					i= (i+1) %(nbclient-1) ;
					}
					}

					System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

					ibr= (ibr + 1) %(nbpbronze);
      		recu = 1;


    	}
			t = Tache.RECOLTE;
		}
		else if ( this.p == Personnalite.PRUDENT )
  	{
			int recu=1;
			int vol=0;
			int i=0;
  		while(!this.stop && r[0] < 100 || r[1] < 100 || r[2] < 100)
    	{

    			while (!this.stop &&  r[0] < 100 && recu != 0)
    			{
    				recu = or.get(ior).getOr(10);
    	  		c.addOr(recu);
						if (recu != 0)
							System.out.println("Je recupère 10 or");
						if ( observation ) t = Tache.OBSERVATION;
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						t = Tache.RECOLTE;
						r = c.getAmountRess();
						ior= (ior + 1) %(nbpor);
    	  	}

					System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

    	  	recu = 1;


    	  	while (!this.stop && r[1] < 100 && recu != 0 )
    	  	{
    	  		recu = argent.get(iar).getArgent(10);
    	  		c.addArgent(recu);
						if (recu != 0)
							System.out.println("Je recupère 10 argent");
						if ( observation ) t = Tache.OBSERVATION;
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						t = Tache.RECOLTE;
						r = c.getAmountRess();
						iar= (iar + 1) %(nbpargent);
      		}

					System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

      		recu = 1;


      		while  (!this.stop && r[2] < 100 && recu != 0 )
      		{
						recu = bronze.get(ibr).getBronze(10);
      			c.addBronze(recu);
						if (recu != 0)
							System.out.println("Je recupère 10 bronze");
						if ( observation ) t = Tache.OBSERVATION;
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
		 				sleep(100);
		 				t = Tache.RECOLTE;
						r = c.getAmountRess();
						ibr= (ibr + 1) %(nbpbronze);
      		}

					System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

      		recu = 1;


    	}

		}
		else if ( this.p == Personnalite.COOPERATIFMALIN )
  	{
  		int recu=1;

  		while(!this.stop && r[0] < 100 || r[1] < 100 || r[2] < 100)
    	{

    			while (!this.stop && r[0] < 100 && recu != 0)
    			{
						if ( observation )
						{ t = Tache.OBSERVATION;
						productress = or.get(ior).getAmountRess();
						t = Tache.RECOLTE;
						if (productress[0] > 10)
    					recu = or.get(ior).getOr(Math.min(100-r[0],10));
						else
							recu = or.get(ior).getOr(Math.min(100-r[0],productress[0]));
						}
						else
						{
							recu = or.get(ior).getOr(10);
						}
    	  		c.addOr(recu);
						if ( observation ) t = Tache.OBSERVATION;
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						t = Tache.RECOLTE;
						r = c.getAmountRess();
						System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );
						ior= (ior + 1) %(nbpor);
    	  	}
    	  	recu = 1;


    	  	while (!this.stop && r[1] < 100 && recu != 0 )
    	  	{
						if ( observation )
						{
						t = Tache.OBSERVATION;
						productress = argent.get(iar).getAmountRess();
						t = Tache.RECOLTE;
						if ( productress[1] > 10)
    					recu = argent.get(iar).getArgent(Math.min(100-r[1],10));
						else
							recu = argent.get(iar).getArgent(Math.min(100-r[1],productress[1]));
						}
						else
						{
							recu = argent.get(iar).getArgent(10);
						}
    	  		c.addArgent(recu);
						if ( observation ) t = Tache.OBSERVATION;
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						t = Tache.RECOLTE;
						r = c.getAmountRess();
						System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );
						iar= (iar + 1) %(nbpargent);
      		}
      		recu = 1;


      		while  (!this.stop && r[2] < 100 && recu != 0 )
      		{
						if ( observation )
						{
						t = Tache.OBSERVATION;
						productress = bronze.get(ibr).getAmountRess();
						t = Tache.RECOLTE;
						if ( productress[2] > 10 )
    					recu = bronze.get(ibr).getBronze(Math.min(100-r[2],10));
						else
							recu = bronze.get(ibr).getBronze(Math.min(100-r[2],productress[2]));
						}
						else
						{
							recu = bronze.get(ibr).getBronze(10);
						}
      			c.addBronze(recu);
						if ( observation ) t = Tache.OBSERVATION;
						if ( ordonnees == 1 ){coord.tourFini(); attenteTour(); }
    	  		sleep(100);
						t = Tache.RECOLTE;
						r = c.getAmountRess();
						System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );
						ibr= (ibr + 1) %(nbpbronze);
      		}
      		recu = 1;
					System.out.println("Ressources disponible : OR -> "+ r[0] +" ARGENT -> "+ r[1] +" BRONZE -> "+ r[2] );

    	}

  	}

	}
	catch (RemoteException re) { System.out.println(re) ; }
	catch (InterruptedException re) { System.out.println(re) ; }

		try{
		log.arret_log();
		int[] r = c.getAmountRess();

		if( r[0] == 100 && r[1] == 100 && r[2] == 100 )
		{
    System.out.println("Ressources disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );

		coord.fini(noclient);

    System.out.println("J'ai fini " );
		}
		else
		{
			System.out.println("Ressources disponible : OR -> "+ c.or +" ARGENT -> "+ c.argent +" BRONZE -> "+ c.bronze );
			System.out.println("Partie terminée" );
		}
		}catch (RemoteException re) { System.out.println(re) ; }


  }


}
