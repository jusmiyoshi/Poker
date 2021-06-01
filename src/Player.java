public class Player {
    //Set to size "2" Array for POKER
    private Card[] hand = new Card[50];
    private int cardCount = 0;
    private int chipStack;
    private int playerScore = 0;

    //Constructor
    public Player(int chipStack){
        this.chipStack = chipStack;
    }

    //Update/Change Methods
    public void updateChips(int chips){
        chipStack += chips;
    }

    public void addToHand(Card c){
        hand[cardCount] = c;
        playerScore += c.getNumber();
        cardCount++;
    }

    public void clearHand(){
        hand = new Card[50];
        cardCount = 0;
        playerScore = 0;
    }

    //Get Methods
    public int getChips(){
        return chipStack;
    }
    public Card[] getHand(){
        return hand;
    }
    public int getPlayerScore(){
        return playerScore;
    }
    public int getCardCount(){
        return cardCount;
    }

}