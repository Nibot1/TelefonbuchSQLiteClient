import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.*;

public class Client {
	static Socket sock;

	public static void main(String[] args) throws IOException, UnknownHostException {
		// init vars
		int option = 0;
		// Start a Socket on localhost port 6000
		try {
		sock = new Socket("localhost", 6000);
		}catch(ConnectException ce) {
			System.out.println("Fehler keine verbindung zum Server möglich");
			System.out.println("Starte bitte zuerst den Server auf port 6000");
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
		System.out.print("Wähle eine Option aus 1:Erstellen 2:Anzeigen 3:Löschen 4:Suchen : ");
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
			System.out.print("Straße: ");
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
			JSONArray kontakte = new JSONArray(S);
			// Prints the answer from the Server
			int i = 0;
			while (i <= kontakte.length() - 1) {
				try {
					JSONObject kontakt = kontakte.getJSONObject(i);
					System.out.println("Id:                 " + kontakt.getInt("id"));
					System.out.println("Vorname:            " + kontakt.getString("Vorname"));
					System.out.println("Nachname:           " + kontakt.getString("Nachname"));
					System.out.println("Straße, Hausnummer: " + kontakt.getString("Straße") + ", "
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

			scanner.close();

		} else if (option == 3) {
			// Send the command to the Server
			Ausgabe.println("3");
			//Get userinput
			System.out.print("Geben sie die ID des zu Löschenden Kontaktes an: ");
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
					System.out.println("Straße, Hausnummer: " + kontakt.getString("Straße") + ", "
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
			scanner.close();

		} else {
			scanner.close();
		}
		scanner.close();
	}
}
