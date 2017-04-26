
import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.util.ArrayList;
import java.rmi.* ;
import java.net.MalformedURLException ;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;


class Client_log_thread extends Thread {

	protected ClientImpl c;
	protected int noclient;
  protected int iteration=0;
  protected int fin=0;

	public void Client_log_thread(){}

  public void setOptions(ClientImpl c,int noclient)
  {
	this.c = c; // Parent
	this.noclient = noclient;
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

      FileWriter writer = new FileWriter("client"+noclient+"log.js", true);
      writer.append("var data = [");
    while( fin == 0)
    {
    r = c.getAmountRess();


    writer.append("\n{ t: '"+iteration+"', or: '"+r[0]+"', argent: '"+r[1]+"', bronze: '"+r[2]+"'},");
    writer.flush();
    sleep(100);
    iteration++;



  }
  r = c.getAmountRess();
  writer.append("\n{ t: '"+iteration+"', or: '"+r[0]+"', argent: '"+r[1]+"', bronze: '"+r[2]+"'}");
  writer.flush();
  writer.append("\n],\n\tconfig = {\n\t\tdata: data,\n\t\txkey: 't',\n\t\tykeys: ['or', 'argent','bronze'],\n\t\tlabels: ['or', 'argent','bronze'],\n\t\tfillOpacity: 0.6,\n\t\thideHover: 'auto',\n\t\tbehaveLikeLine: true,\n\t\tresize: true,\n\t\tpointFillColors:['#ffffff'],\n\t\tpointStrokeColors: ['black'],\n\t\tparseTime:false,\n\t\tlineColors:['gray','red','blue']\n\t};\nconfig.element = 'client"+noclient+"';\nMorris.Line(config);");
    writer.close();

  } catch (IOException e) { System.out.println(e) ; }
    catch (InterruptedException e) { System.out.println(e) ; }


 }
 }
