public class Game {
    // 0 = Tournament Game
    // 1 = Cash Game
    // 2 = BlackJack (Testing Only)
    private int format;
    public Game(String format) {
        switch (format) {
            case "Cash", "cash" -> this.format = 1;
            case "BlackJack", "Blackjack", "blackjack" -> this.format = 2;
            //tournament is default
            default -> this.format = 0;
        }
    }

    public static void main(String[] args) {
        Deck deck1 = new Deck();
        for(int i = 0; i < 52; i++)
        {
            System.out.println(deck1.deck[i].getSuit() + "" + deck1.deck[i].getNumber());
        }
    }

    public static class Deck extends Card {
        private final Card[] deck;
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

    }

    static class Card {
        // A = 1 (+13 when checking values)
        // K = 13
        // Q = 12
        // J = 11
        // H = 0 D = 1 S = 2 C = 3
        private int number;
        private int suit;
        Card(){
        }
        public Card(int number, int suit)
        {
            this.number = number;
            this.suit = suit;
        }

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


}
