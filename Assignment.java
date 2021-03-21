import java.util.*;

/**Represent the different ranks a card can be. 
 * Since it is enum, the card rank cannot be changed. 
 */
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

    //^ Getters:
    /** Returns ID of the current card object. 
     * @return cardID (string): card ID
     */
    public long getCardId() {
        return cardId;
    }

    /** Returns name of the current card object. 
     * @return cardName (string): card name
     */
    public String getCardName() {
        return cardName;
    }

    /** Returns rank of the current card object. 
     * @return cardRank (enum Rank): card rank
     */
    public Rank getCardRank() {
        return cardRank;
    }

    /** Returns price of the current card object. 
     * @return cardPrice (int): card price
     */
    public long getCardPrice() {
        return cardPrice;
    }

    //^ Setters:
    /** Assigns the argument passed to the card ID field. 
     * @param cardId (long): new card ID
     */
    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    /** Assigns the argument passed to the card name field. 
     * @param cardName (String): new card name
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /** Assigns the argument passed to the card rank field. 
     * @param cardRank (enum Rank): new card rank
     */
    public void setCardRank(Rank cardRank) {
        this.cardRank = cardRank;
    }

    /** Assigns the argument passed to the card field. 
     * @param cardPrice (long): new card price
     */
    public void setCardPrice(long cardPrice) {
        this.cardPrice = cardPrice;
    }

    //^ Overrides: 
    @Override
    /**Returns the string representation of the Card object. 
     * The card ID, name, rank and price in £ are returned in new lines for better readability. 
     * @return (string): string representation of card
     */
    public String toString() {
        return ("Card ID: " + this.cardId + "\nCard Name: " + this.cardName + "\nCard Rank: " + this.cardRank + "\nCard Price: £" + this.cardPrice);
    }

    @Override 
    /** Override default implementation to check whether current object is equal to object being passed for comparison.  
     * Method checks whether the object passed is the same type / instance of the current object, 
     * if the objects are not the same type than they cannot be equal. 
     * If they are the same type, than the passed object is casted to be the Card type so that card methods can be used. 
     * Card names, card IDs, and card ranks must be equal for the two cards to be equal. 
     * Comparison done with equal operator (==) and compareTo() method. 
     * @return (boolean): whether the cards are equal
     */
    public boolean equals(Object object) { 
        if (!(object instanceof Card)) { // If the object passed is not an instance of card then cannot be equal
            return false;
        }

        Card other = (Card)object; // Object passed is casted and assigned to a variable so that Card methods can be called

        if ((this.cardId == other.cardId) && (this.cardName == other.cardName) && (this.cardRank == other.cardRank)) { // Same ID, name and rank using comparison operator
            return true;
        } else if ((Long.valueOf(this.cardId).compareTo(Long.valueOf(other.cardId)) == 0) && (this.cardName.compareTo(other.cardName) == 0) && (this.cardRank.compareTo(other.cardRank) == 0)){ // Same ID, name, and rank using compareTo() method
            return true;
        } else {
            return false;
        }
    }

    @Override
    /** Overrides the default hash code method to determine hash code. 
     * Hash code determines in which bucket in a hash set an object is stored in.
     * Object with the same hash code are stored in the same bucket. 
     * Equals method must also be overwritten so that objects in the same bucket are compared appropriately. 
     * @return hash (int): overwritten hash code determined from ID, name and rank
     */
    public int hashCode() {
        int hash;
        hash = cardName.hashCode(); // Hash code of card name added to hash
        hash = hash + cardRank.hashCode(); // Hash code of card rank added to hash
        hash = hash + Long.valueOf(cardId).hashCode(); // Hash code of card ID added to hash
        return hash; // Final hash code from card name, rank and ID is returned
    }

    @Override
    /**Compare whether two cards are equal by using the Comparable interface of generic type. 
     * The card ID, name and rank are compared. 
     * Each will return 0 if the fields are equal. 
     * The if the sum of all the comparison us 0, then the cards are equal. 
     * @return (int): whether current card and other card are equal. 
     */
    public int compareTo(Card object) {
        Long thisID = Long.valueOf(cardId); // Assign primitive long to wrapper type Long
        
        int result = thisID.compareTo(object.cardId); // Compare ID from this object to the object passed
        result = result + this.cardName.compareTo(object.cardName); // Increment with card name comparison
        result = result + this.cardRank.compareTo(object.cardRank); // Increment with card name comparison

        return result;
        // Increment all compareTo since if they are equal than 0 will be returned
    }
}

public class Assignment {
    public static void main(String[] args) {
        CardTest.test1();
    }
}