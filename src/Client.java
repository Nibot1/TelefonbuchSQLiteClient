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
		  System.out.println("Wähle eine Option aus 1:Erstellen 2:Anzeigen 3:Löschen 4:Suchen : ");
		  String option = scanner.nextLine();
		  if(option.toLowerCase() == "erstellen" || option == "1") {
			  StringBuilder sb = new StringBuilder();
			  System.out.println("Gib die Daten von dem neuen Kontakt ein.");
			  sb.append("{");
			  System.out.println("Vorname: ");
			  sb.append("\"Vorname\":\""+scanner.next()+"\",");
			  System.out.println("Nachname: ");
			  sb.append("\"Nachname\":\""+scanner.next()+"\",");
			  System.out.println("Straße: ");
			  sb.append("\"Straße\":\""+scanner.next()+"\",");
			  System.out.println("Hausnummer: ");
			  sb.append("\"Hausnummer\":\""+scanner.next()+"\",");
			  System.out.println("Postleitzahl: ");
			  sb.append("\"Postleitzahl\":\""+scanner.next()+"\",");
			  System.out.println("Ort: ");
			  sb.append("\"Ort\":\""+scanner.next()+"\",");
			  System.out.println("Telefonnummer: ");
			  sb.append("\"Telefonnummer\":\""+scanner.next()+"\",");
			  System.out.println("Faxnummer: ");
			  sb.append("\"Faxnummer\":\""+scanner.next()+"\",");
			  System.out.println("Handynummer: ");
			  sb.append("\"Handynummer\":\""+scanner.next()+"\",");
			  System.out.println("Email: ");
			  sb.append("\"Email\":\""+scanner.next()+"\"");
			  sb.append("}");
			  //Send a String to the Server
		      Ausgabe.println(sb.toString());
		      //Reads the answer from  the Server
		      String S=Eingabe.readLine();
		      //Prints the answer from the Server
		      System.out.println(S);
		      //scanner.close();
			  
		  }if(option.toLowerCase() == "anzeigen" || option == "2") {
			  
			//Send a String to the Server
		      
		      Ausgabe.println("anzeigen");
		      //Reads the answer from  the Server
		      String S=Eingabe.readLine();
		      //Prints the answer from the Server
		      System.out.println(S);
			  
		  }if(option.toLowerCase() == "löschen" || option == "3") {
			  
			//Send a String to the Server
		      Ausgabe.println("löschen");
		      //Reads the answer from  the Server
		      String S=Eingabe.readLine();
		      //Prints the answer from the Server
		      System.out.println(S);
			  
		  }if(option.toLowerCase() == "suchen" || option == "4") {
			//Send command String to the Server
		      Ausgabe.println("suchen");
		      
		      //Send search String to the Server
		      System.out.print("Geben sie das Suchwort ein: ");
		      Ausgabe.println(scanner.next());
		      //Reads the answer from  the Server
		      String S=Eingabe.readLine();
		      //Prints the answer from the Server
		      System.out.println(S);
			  
		  }else {
			  //scanner.close();
		  }
		  //scanner.close();
	}
	}

	