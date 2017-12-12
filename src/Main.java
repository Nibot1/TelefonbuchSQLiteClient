import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Tobin Rosenau
 *
 */
public class Main {
	static Socket sock; 
	public static void main(String[] args) throws IOException, UnknownHostException {
		// Start a Socket on localhost port 6001
		sock= new Socket("localhost",6001);
		//Closes the Server when the Program is closed;
		  Runtime.getRuntime().addShutdownHook(new Thread()
			{
			    @Override
			    public void run()
			    {
			        try {
						sock.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});
		  //Send a String to the Server
	      PrintWriter Ausgabe= new PrintWriter(sock.getOutputStream(),true);
	      Ausgabe.println("Guten Tag und viel Spass!");
	      //Reads the answer from  the Server
	      InputStreamReader PortLeser = new InputStreamReader(sock.getInputStream());
	      BufferedReader Eingabe= new BufferedReader(PortLeser);
	      String S=Eingabe.readLine();
	      //Prints the answer from the Server
	      System.out.println("Antwort vom Server: "+S);

	}

	}