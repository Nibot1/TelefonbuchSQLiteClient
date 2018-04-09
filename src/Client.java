import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.*;
import org.w3c.dom.Document;

public class Client {
	static Socket sock;
	static int port = 6000;
	static String ip = "localhost";
	public static void main(String[] args) throws IOException, UnknownHostException {
		int option = 0;
		//Read config file
				try {
				File file = null;
				file = new File("config.xml");
				if(file.exists()) {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				        .newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(file);
				try {
				port = Integer.parseInt(document.getElementsByTagName("port").item(0).getTextContent());
				}catch(Exception e) {
					System.out.println("Bitte �berpr�fen sie ob in der Konfigurationsdatei eine Zahl als Port eingegeben ist.");
				}
				ip = document.getElementsByTagName("server-ip").item(0).getTextContent();
				}else {
					System.out.println("Keine Konfigurationsdatei gefunden");
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
		// Start a Socket on localhost port 6000
		try {
		sock = new Socket(ip, port);
		}catch(ConnectException ce) {
			System.out.println("Fehler keine verbindung zum Server m�glich");
			System.out.println("Starte bitte zuerst den Server");
			System.exit(0);
		}
		PrintWriter Ausgabe = new PrintWriter(sock.getOutputStream(), true);
		InputStreamReader PortLeser = new InputStreamReader(sock.getInputStream());
		BufferedReader Eingabe = new BufferedReader(PortLeser);
		// Closes the Server when the Program is closed;
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					sock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		// Start user input listener
		Scanner scanner = new Scanner(System.in);
		// Ask the User to input an action
		System.out.print("W�hle eine Option aus 1:Erstellen 2:Anzeigen 3:L�schen 4:Suchen : ");
		// catch the Userinput
		try {
			option = scanner.nextInt();
		} catch (InputMismatchException ie) {
			System.out.println("Gib eine Zahl ein und kein wort");
			sock.close();
			System.exit(0);
		}
		if (option == 1) {
			// Create a StringBuilder and fill it in Json Format with the User input
			StringBuilder sb = new StringBuilder();
			System.out.println("Gib die Daten von dem neuen Kontakt ein.");
			sb.append("{");
			System.out.print("Vorname: ");
			sb.append("\"Vorname\":\"" + scanner.next() + "\",");
			System.out.print("Nachname: ");
			sb.append("\"Nachname\":\"" + scanner.next() + "\",");
			System.out.print("Stra�e: ");
			sb.append("\"Strasse\":\"" + scanner.next() + "\",");
			System.out.print("Hausnummer: ");
			sb.append("\"Hausnummer\":\"" + scanner.next() + "\",");
			System.out.print("Postleitzahl: ");
			sb.append("\"Postleitzahl\":\"" + scanner.next() + "\",");
			System.out.print("Ort: ");
			sb.append("\"Ort\":\"" + scanner.next() + "\",");
			System.out.print("Telefonnummer: ");
			sb.append("\"Telefonnummer\":\"" + scanner.next() + "\",");
			System.out.print("Faxnummer: ");
			sb.append("\"Faxnummer\":\"" + scanner.next() + "\",");
			System.out.print("Handynummer: ");
			sb.append("\"Handynummer\":\"" + scanner.next() + "\",");
			System.out.print("Email: ");
			sb.append("\"Email\":\"" + scanner.next() + "\"");
			sb.append("}");
			// Send the command to the Server
			Ausgabe.println("1");
			// Send a String to the Server
			Ausgabe.println(sb.toString());
			// Reads the answer from the Server
			String S = Eingabe.readLine();
			// Prints the answer from the Server
			System.out.println(S);

		} else if (option == 2) {
			// Send the command to the Server
			Ausgabe.println("2");
			// Reads the answer from the Server
			String S = Eingabe.readLine();
			//System.out.println(S);
			// Parse the JsonString to an JsonArray
			if(!S.equals("[]")) {
			printKontakt(S);
			}else {
				System.out.println("Dein Telefonbuch ist leer");
			}

			scanner.close();

		} else if (option == 3) {
			// Send the command to the Server
			Ausgabe.println("3");
			//Get userinput
			System.out.print("Geben sie die ID des zu L�schenden Kontaktes an: ");
			Ausgabe.println(scanner.nextInt());
			// Reads the answer from the Server
			String S = Eingabe.readLine();
			// Prints the answer from the Server
			System.out.println(S);
			scanner.close();

		} else if (option == 4) {
			// Send the command to the Server
			Ausgabe.println("4");
			// Send search String to the Server
			System.out.print("Geben sie das Suchwort ein: ");
			Ausgabe.println(scanner.next());
			// Reads the answer from the Server
			String S = Eingabe.readLine();
			if(S.equals("[]")) {
				System.out.println("Kein Ergebnis gefunden");
			}
			printKontakt(S);
			
			}
			scanner.close();

		} else {
			scanner.close();
		}
		scanner.close();
	}
	public void printKontakt(String S){
	// Parse the JsonString to an JsonArray
		JSONArray kontakte = new JSONArray(S);
			// Prints the answer from the Server
			int i = 0;
			while (i <= kontakte.length() - 1) {
				try {
					JSONObject kontakt = kontakte.getJSONObject(i);
					System.out.println("Id:                 " + kontakt.getInt("id"));
					System.out.println("Vorname:            " + kontakt.getString("Vorname"));
					System.out.println("Nachname:           " + kontakt.getString("Nachname"));
					System.out.println("Stra�e, Hausnummer: " + kontakt.getString("Strasse") + ", "
							+ kontakt.getString("Hausnummer"));
					System.out.println(
							"PLZ, Ort:           " + kontakt.getString("Plz") + ", " + kontakt.getString("Ort"));
					System.out.println("Tel.:               " + kontakt.getString("Telefonnummer"));
					System.out.println("Fax.:               " + kontakt.getString("Faxnummer"));
					System.out.println("Handynr.:           " + kontakt.getString("Handynummer"));
					System.out.println("Email:              " + kontakt.getString("Emailadresse"));
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;
			}
	}
}
