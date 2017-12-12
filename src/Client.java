import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Tobin Rosenau
 *
 */
public class Client {
	static Socket sock; 
	public static void main(String[] args) throws IOException, UnknownHostException {
		// Start a Socket on localhost port 6002
		sock= new Socket("localhost",6002);
		 PrintWriter Ausgabe= new PrintWriter(sock.getOutputStream(),true);
	      InputStreamReader PortLeser = new InputStreamReader(sock.getInputStream());
	      BufferedReader Eingabe= new BufferedReader(PortLeser);
		//Closes the Server when the Program is closed;
		  Scanner scanner = new Scanner(System.in);
		  System.out.print("Wähle eine Option aus 1:Erstellen 2:Anzeigen 3:Löschen 4:Suchen : ");
		  int option = scanner.nextInt();
		  if(option == 1) {
			  //Create a StringBuilder and fill it in Json Format with the User input
			  StringBuilder sb = new StringBuilder();
			  System.out.print("Gib die Daten von dem neuen Kontakt ein.");
			  sb.append("{");
			  System.out.print("Vorname: ");
			  sb.append("\"Vorname\":\""+scanner.next()+"\",");
			  System.out.print("Nachname: ");
			  sb.append("\"Nachname\":\""+scanner.next()+"\",");
			  System.out.print("Straße: ");
			  sb.append("\"Straße\":\""+scanner.next()+"\",");
			  System.out.print("Hausnummer: ");
			  sb.append("\"Hausnummer\":\""+scanner.next()+"\",");
			  System.out.print("Postleitzahl: ");
			  sb.append("\"Postleitzahl\":\""+scanner.next()+"\",");
			  System.out.print("Ort: ");
			  sb.append("\"Ort\":\""+scanner.next()+"\",");
			  System.out.print("Telefonnummer: ");
			  sb.append("\"Telefonnummer\":\""+scanner.next()+"\",");
			  System.out.print("Faxnummer: ");
			  sb.append("\"Faxnummer\":\""+scanner.next()+"\",");
			  System.out.print("Handynummer: ");
			  sb.append("\"Handynummer\":\""+scanner.next()+"\",");
			  System.out.print("Email: ");
			  sb.append("\"Email\":\""+scanner.next()+"\"");
			  sb.append("}");
			  //Send a String to the Server
		      Ausgabe.println(sb.toString());
		      //Reads the answer from  the Server
		      String S=Eingabe.readLine();
		      //Prints the answer from the Server
		      System.out.println(S);
			  
		  }if(option ==2) {
			//Send a String to the Server
		      Ausgabe.println("anzeigen");
		      //Reads the answer from  the Server
		      String S=Eingabe.readLine();
		      //Prints the answer from the Server
		      System.out.println(S);
		      scanner.close();

			  
		  }if(option == 3) {
			//Send a String to the Server
		      Ausgabe.println("löschen");
		      //Reads the answer from  the Server
		      String S=Eingabe.readLine();
		      //Prints the answer from the Server
		      System.out.println(S);
		      scanner.close();

			  
		  }if(option == 4) {
			//Send command String to the Server
		      Ausgabe.println("suchen");
		      //Send search String to the Server
		      System.out.print("Geben sie das Suchwort ein: ");
		      Ausgabe.println(scanner.next());
		      //Reads the answer from  the Server
		      String S=Eingabe.readLine();
		      //Prints the answer from the Server
		      System.out.println(S);
		      scanner.close();
			  
		  }else {
			  scanner.close();
		  }
		  scanner.close();
	}
	}

	