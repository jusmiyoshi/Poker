public class Deck {
    private final Card[] deck;
    private int cardsDealt = 0;
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

    public Card dealCard(){
        return deck[cardsDealt++];
    }

    public Card[] getDeck(){
        return deck;
    }
}

