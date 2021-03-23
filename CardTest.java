import java.util.HashSet;
import java.util.Set;

class CardTest {
	public static void simpleTest() { //* Passed
		Card c0 = new Card(1, "card1", Rank.COMMON);
		Card c1 = new Card(2, "card2", Rank.COMMON);
		Card c2 = new Card(1, "card1", Rank.COMMON);
		StringBuilder sb0 = new StringBuilder("card1");
		String s0 = sb0.toString();
		Card c3 = new Card(1, s0, Rank.COMMON);
		Card c4 = new Card(3, "card1", Rank.UNIQUE);

		//^ Test Equal:
		System.out.println("c0 equal to c2: " + c0.equals(c2)); // True
		System.out.println("c0 equal to c1: " + c0.equals(c1)); // False
		System.out.println("c0 equal to c1: " + c0.equals(c3)); // True
		System.out.println("c0 equal to c1: " + c0.equals(c4)); // False
	}

	public static void hashSetTest() { //* Passed
		Card c0 = new Card(1, "card1", Rank.COMMON); // same as c2
		c0.setCardPrice(2);
		Card c1 = new Card(2, "card2", Rank.COMMON);
		c1.setCardPrice(2);
		Card c2 = new Card(1, "card1", Rank.COMMON); // same as c0
		c0.setCardPrice(6);

		Set<Card> s = new HashSet<Card>();
		s.add(c0);
		s.add(c1);
		s.add(c2);
		System.out.println("Size: " + s.size()); // Size 2 because 2 cards are the same
	}

	public static void main(String[] args) {
		// simpleTest();
		hashSetTest();
	}
}
