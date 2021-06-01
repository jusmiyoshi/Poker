public class Deck {

    private final Card[] deck;
    private int cardsDealt = 0;

    //Constructor
    public Deck(){
        deck = new Card[52];
        int i = 0;
        for(int j = 0; j < 4; j++) {
            for (int k = 1; k <= 13; k++) {
                deck[i] = new Card(k, j);
                i++;
            }
        }
    }

    //Update Method
    public Card dealCard(){
        return deck[cardsDealt++];
    }

    //Get Method
    public Card[] getDeck(){
        return deck;
    }
}

