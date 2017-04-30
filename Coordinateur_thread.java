import java.rmi.* ;
import java.net.MalformedURLException ;

class Coordinateur_thread extends Thread {

	protected int temps;
  protected Boolean stop = false;


	public void Coordinateur_thread(){
  }

	public void lancement()
	{
    temps=0;
		this.start();
	}

	public int getTime()
	{

		this.stop = true;
    return temps;
	}


	public void run(){
    while(!this.stop)
    {
      try{
        sleep(100);
        temps += 100;
      }catch (InterruptedException e ) { System.out.println(e) ; }
    }
  }
}
