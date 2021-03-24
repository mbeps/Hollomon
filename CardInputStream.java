import java.io.*;

/**Handles reading streams of data from server. 
 * 
 */
public class CardInputStream extends InputStream{
	BufferedReader cardInputStream;
	
	/**Constructor initiates instance of buffered reader from input stream. 
	 * 
	 * @param input (InputStream): initiates instance of buffered reader from input stream
	 * @throws IOException
	 */
	public CardInputStream(InputStream input) {
		try {
			cardInputStream = new BufferedReader(new InputStreamReader(input));
			cardInputStream.mark(1000);
		} catch (IOException e) {
			System.out.println("Error");
		}  
	}
   
	/**Reads card and creates an equivalent card. 
	 * Checks whether the is response. 
	 * While there is a response details sent are read and checked. 
	 * Once the "CARD" tag is read, the details after are used to create new equivalent card. 
	 * If "OK" is read then there is no card details therefore null is returned. 
	 * If there is a failure null is still returned by default. 
	 * @return (Card): new equivalent card from reading
	 * @throws IOException
	 * @see {@link #readCard()}: reading socket response 
	 */
	public Card readCard() {
		String currentStream;
		
		try {
			while ((currentStream = readResponse()) != null) { // Only works while there is a response
				if (currentStream.equals("CARD")) { // From this point the details sent are card details
					return (new Card(Long.parseLong(readResponse()), readResponse(), Rank.valueOf(readResponse())));
				} else if (currentStream.contains("OK")) { // No card details 
					return null;
				}
			}
		} catch (IOException e) {
			return null;
		}
		
		return null;
	}

	/**Returns full read response from socket. 
	 * 
	 * @return (BufferedReader): returns data that was read from socket response
	 * @throws IOException
	 */
	public String readResponse() throws IOException {
		return (cardInputStream.readLine());
	}

	//^ Overrides:
	@Override
	public int read() throws IOException {
		return 0;
	}

	@Override
	/**Only closes the current input stream instead of all input streams. 
	 * 
	 */
	public void close() throws IOException {
		cardInputStream.close();
	}
}
