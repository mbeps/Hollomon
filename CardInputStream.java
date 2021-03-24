import java.io.*;

public class CardInputStream extends InputStream{
    BufferedReader cardInputStream;
    
    public CardInputStream(InputStream input) throws IOException {
        cardInputStream = new BufferedReader(new InputStreamReader(input));
        cardInputStream.mark(1000);
    }

    //^ Getters:
    public BufferedReader getCardInputStream() {
        return cardInputStream;
    }

    //^Setters:
    public void setCardInputStream(BufferedReader cardInputStream) {
        this.cardInputStream = cardInputStream;
    }

    
    public Card readCard() throws IOException {
        String currentStream;
        while ((currentStream = readResponse()) != null) {
            if (currentStream.equals("CARD")) {
                return (new Card(Long.parseLong(readResponse()), readResponse(), Rank.valueOf(readResponse())));
            } else if (currentStream.contains("OK")) {
                return null;
            }
        }
        return null;
    }

    public String readResponse() throws IOException {
        return (cardInputStream.readLine());
    }

    //^ Overrides:
    @Override
	public int read() throws IOException {
		return 0;
	}

    @Override
    public void close() throws IOException {
        cardInputStream.close();
    }
}
