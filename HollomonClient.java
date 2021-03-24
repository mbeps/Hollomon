import java.io.*;
import java.net.*;
import java.util.*;

/**Handles connection with the server. 
 * Socket used to connect to the server. 
 * After passing username and password login is complete. 
 * Card objects can then be retrieved from server. 
 */
public class HollomonClient {
	private CardInputStream in;
	private BufferedWriter out;
	private Socket socket;

	/** Construct client to communicate with server. 
	 * Constructor takes server and port to create socket object to connect to server. 
	 * Card input stream initialized which takes the socket input stream. 
	 * Buffered reader required to write multiple stream of data by buffering data retrieved from server. 
	 * Try-catch block used as connection may not be successful. 
	 * Try with resources cannot be used as stream are closed manually later. 
	 * @param server (String): server address
	 * @param port (int): port number used for connection
	 */
	public HollomonClient(String server, int port) {
		try {
			socket = new Socket(server, port);
			in = new CardInputStream(socket.getInputStream());
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) { //* UnknownHostException already handled
			System.out.println("Failure");
		}   
	}

	//^ Getter:
	public Socket getSocket() {
		return socket;
	}

	//^ Socket:
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/** Login into server and retrieve cards. 
	 * Method will use the HTTP connection to send over the username and password for the server. #
	 * Username is sent over all in lowercase. 
	 * Password is case sensitive therefore it is not lowered. 
	 * Windows carriage return must be handled (\r\n). 
	 * Stream buffered until flushed and sent over to server. 
	 * Once the data is sent to the server, a connection is attempted to be created. 
	 * If the connection is unsuccessful (wrong credentials or exception) than null is returned.
	 * If everything is correct, card read from server and stored in the list. 
	 * @param username (string): username in lowercase to log into server
	 * @param password (Strong): password to log into server
	 * @return (List<Card>): the list of cards from the server
	 * @see {@link #CardInputStream.readCard()}: read card from server and create equivalent new card
	 */
	public List<Card> login(String username, String password) {
		try {
			//^ HTTP GET Log In (Ruben Code)
			out.write(username.toLowerCase()); // Username must be sent in lowercase
			out.write("\r\n");
			out.write(password);
			out.write("\r\n");
			out.flush(); // Output buffered reader

			String temp; // Store temporary response from server
			while ((temp = in.readResponse()) != null) { // While there is response from server
				System.out.println(temp);
				if (temp.contains("logged in successfully.")) { // Check whether connection is successful
					List<Card> cardList = new ArrayList<Card>();
					Card card;
					while ((card = in.readCard()) != null) { // While server reading is not null
						cardList.add(card); // Cards from server added to array
					}
					Collections.sort(cardList); // Sorts the unsorted array as items were not added in order
					return cardList; // Method terminates here therefore null not returned
				}
			}	
		} catch (IOException e) {
			// e.printStackTrace();
		}
		//* Unsuccessful Login
		return null; // If the try block not executed then null returned
	}

	/**Closes all streams. 
	 * Socket closed ending connection. 
	 * Both input and output stream closed avoiding leakage (requirement). 
	 * @throws IOException
	 */
	public void close() throws IOException {
		socket.close();
		in.close();
		out.close();
	}
}