
import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.util.ArrayList;
import java.rmi.* ;
import java.net.MalformedURLException ;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;


class Product_log_thread extends Thread {

	protected Product c;
	protected int noproduct;
  protected int iteration=0;
  protected int fin=0;
  protected int type; // 0 -> Productor / 1 -> Producargent / 2 -> Productbronze

	public void Client_log_thread(){}

  public void setOptions(ProductImpl c,int noproduct,int type)
  {
	this.c = c; // Parent
	this.noproduct = noproduct;
  this.type= type;
  }

	public void lancement()
	{
		this.start();
		System.out.println("Lancement des logs " );
	}

  public void arret_log()
	{
		this.fin = 1;

	}


	public void run(){

    try{
      int[] r;
      FileWriter writer ;
      if ( type == 0)
      writer = new FileWriter("productor"+noproduct+"log.js", true);
      else if ( type == 1 )
      writer = new FileWriter("productargent"+noproduct+"log.js", true);
      else
      writer = new FileWriter("productbronze"+noproduct+"log.js", true);
      writer.append("var data = [");
    while( fin == 0)
    {
    r = c.getAmountRess();


    writer.append("\n{ t: '"+iteration+"', or: '"+r[0]+"', argent: '"+r[1]+"', bronze: '"+r[2]+"'},");
    writer.flush();
    sleep(100);
    iteration++;



  }
  String nom;
  if ( type == 0 ) nom ="productor"; else if ( type == 1) nom ="productargent"; else nom ="productbronze";
  r = c.getAmountRess();
  writer.append("\n{ t: '"+iteration+"', or: '"+r[0]+"', argent: '"+r[1]+"', bronze: '"+r[2]+"'}");
  writer.flush();
  writer.append("\n],\n\tconfig = {\n\t\tdata: data,\n\t\txkey: 't',\n\t\tykeys: ['or', 'argent','bronze'],\n\t\tlabels: ['or', 'argent','bronze'],\n\t\tfillOpacity: 0.8,\n\t\thideHover: 'auto',\n\t\tbehaveLikeLine: true,\n\t\tresize: true,\n\t\tpointFillColors:['#ffffff'],\n\t\tpointStrokeColors: ['black'],\n\t\tparseTime:false,\n\t\tlineColors:['#f4bf42','red','blue']\n\t};\nconfig.element = '"+nom+noproduct+"';\nMorris.Line(config);");
    writer.close();

  } catch (IOException e) { System.out.println(e) ; }
    catch (InterruptedException e) { System.out.println(e) ; }


 }
 }
