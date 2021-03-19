import java.util.*;

enum Rank {
    UNIQUE, RARE, UNCOMMON, COMMON;
}

/** Card class from which card objects can be initiated for trading. 
 * Card ID represents unique card. 
 * Card name represent the name of the card. 
 * Card rank represent the rank of the card which is constant. 
 * Each card has a price for trade. 
 */
class Card implements Comparable<Card>{
    private long cardId;
    private String cardName;
    private Rank cardRank;
    private long cardPrice;

    /** Constructs card object. 
     *  
     * @param id (long) - represents each unique card
     * @param name (String) - represents name of card
     * @param rank (Rank) - represent the rank of card as constant enum
     */
    public Card(long id, String name, Rank rank) {
        this.cardId = id;
        this.cardName = name;
        this.cardRank = rank;
    }

    //^ Getter:
    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    //^ Overrides: 
    @Override
    public String toString() {
        return (this.cardId + " " + this.cardName + " " + this.cardRank + " " + this.cardPrice);
    }

    @Override // Override ensures that methods is not overloaded instead of overwritten
    public boolean equals(Object object) { 
        if (!(object instanceof Card)) {
            return true;
        }

        Card other = (Card)object;
        return (this.cardId == other.cardId);
    }

    @Override
    /** Overrides the default hash code method to determine hash code. 
     * Hash code determines in which bucket in a hash set an object is stored in.
     * Object with the same hash code are stored in the same bucket. 
     * Equals method must also be overwritten so that objects in the same bucket are compared appropriately. 
     */
    public int hashCode() {
        int hash;
        hash = cardName.hashCode(); // Hash code of card name added to hash
        hash = hash + cardRank.hashCode(); // Hash code of card rank added to hash
        hash = hash + Long.valueOf(cardId).hashCode(); // Hash code of card ID added to hash
        return hash; // Final hash code from card name, rank and ID is returned
    }

    @Override
    public int compareTo(Card o) {
        return 0;
    }
}

public class Assignment {
    public static void test1() {
        Card c0 = new Card(1, "card1", Rank.COMMON);
        Card c1 = new Card(2, "card2", Rank.COMMON);
        Card c2 = new Card(1, "card1", Rank.COMMON);

        System.out.println(c1);
    }

    public static void main(String[] args) {
        test1();
    }
}