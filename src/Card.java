 class Card {
    // A = 1 (+13 when checking values)
    // K = 13
    // Q = 12
    // J = 11
    // H = 0 D = 1 S = 2 C = 3
    private int number;
    private int suit;

    //Constructor
    Card(){}
    public Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
    }

    //Get Methods
    public int getNumber(){
        //print AKQJ instead of numbers
        return number;
    }
    public char getSuit(){
        char tmp = ' ';
        switch (suit) {
            case 0 -> tmp = 'H';
            case 1 -> tmp = 'D';
            case 2 -> tmp = 'S';
            case 3 -> tmp = 'C';
        }
        return tmp;
    }
}