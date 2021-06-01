import java.util.Random;
import java.util.Scanner;

public class Game {
    // 0 = Tournament Game
    // 1 = Cash Game
    // 2 = BlackJack (Testing Only)
    private final int format;
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
        shuffle(deck1.getDeck());

        // CARD SHUFFLE + CHECK
        /*
        for(int i = 0; i < 52; i++)
        {
            System.out.println(deck1.getDeck()[i].getSuit() + "" + deck1.getDeck()[i].getNumber());
        }
        */
        // BLACKJACK CHECK
        Player player1 = new Player(1000);
        boolean cont;
        do {
            cont = blackJack(player1, deck1);
            player1.clearHand();
            shuffle(deck1.getDeck());
        }while (cont && (player1.getChips() > 0));

    }

    static void shuffle(Card[] card) {
        Random r = new Random();
        for (int i = 52 - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            Card tmp = card[i];
            card[i] = card[j];
            card[j] = tmp;
        }
    }

    static boolean blackJack(Player player, Deck deck){
        Scanner scan = new Scanner(System.in);
        System.out.println("Current Chip Count:" + player.getChips() + "\n" + "Input Bet: ");
        int bet;
        do{
            bet = scan.nextInt();
            if(bet > player.getChips()){
                System.out.print("Not Enough Chips! Current Chips:" + player.getChips() + "\nInput Bet Amount: ");
            }
        }while(bet > player.getChips());
        player.updateChips(-1*bet);

        Card[] dealer = new Card[50];
        int dealerDealt = 0;
        int dealerScore = 0;
        do {
            player.addToHand(deck.dealCard());
            if(player.getCardCount() - 1 == 0) {
                System.out.println("Player: " + player.getHand()[player.getCardCount() - 1].getSuit() + "" + player.getHand()[player.getCardCount() - 1].getNumber() + " ");
            }
            dealer[dealerDealt] = deck.dealCard();
            dealerScore += dealer[dealerDealt].getNumber();
            if(dealerDealt == 0){
                System.out.println("Dealer: **\n");
            }
            dealerDealt++;
        }while(player.getCardCount() < 2 && dealerDealt < 2);

        Card[] playerC = player.getHand();
        printLine(player, dealer, playerC);

        //Player Choice

        //play game with move input
        boolean done = false;
        do {
            char move;
            do {
                System.out.println("[H]it, [S]tand, [D]ouble?");
                move = scan.next().charAt(0);
            }while(move != 'h' && move != 'H' && move != 'S' && move != 's' && move != 'd' && move != 'D');

            switch(move) {
                case 'H':
                case 'h':
                    player.addToHand(deck.dealCard());
                    break;
                case 'd':
                case 'D':
                    if(bet > player.getChips()){
                        System.out.println("Not Enough Chips!");
                        break;
                    }
                    player.updateChips(-1 * bet);
                    bet *= 2;
                    player.addToHand(deck.dealCard());
                case 's':
                case 'S':
                    done = true;
                    break;
                }
                if(move != 's' && move != 'S' && player.getPlayerScore() <= 21) {
                    playerC = player.getHand();
                    printLine(player, dealer, playerC);
                }
        }while(!done && player.getPlayerScore() <= 21);

        while(dealerScore < 17)
        {
            dealer[dealerDealt] = deck.dealCard();
            dealerScore += dealer[dealerDealt].getNumber();
            dealerDealt++;
        }

        playerC = player.getHand();
        printOut(player, playerC);
        for(int j = 0; j < dealerDealt; j++) {
            System.out.print(dealer[j].getSuit() + "" + dealer[j].getNumber() + " ");
       }
        System.out.println("\nDealer Score: " + dealerScore + "\n");

        int check = winCheck(player,dealerScore);
        switch (check) {
            case 1 -> {
                System.out.println("Draw!");
                player.updateChips(bet);
            }
            case 2 -> {
                System.out.println("Player Wins!");
                player.updateChips(bet * 2);
            }
            default -> System.out.println("Player Loses!");
        }

        System.out.println("Current Chip Count:" + player.getChips() + "\n" + "Keep Playing? (Y/N): ");
        char cont = scan.next().charAt(0);
        return cont != 'N' && cont != 'n';

    }

    private static void printOut(Player player, Card[] playerC) {
        System.out.println("----------------------------------------------------");
        System.out.print("Player Cards:");
        for(int i = 0; i < player.getCardCount(); i++)
        {
            System.out.print(playerC[i].getSuit() + "" + playerC[i].getNumber() + " ");
        }
        System.out.println("\nPlayer Score: " + player.getPlayerScore());
        System.out.print("\nDealer Cards:");
    }

    private static void printLine(Player player, Card[] dealer, Card[] playerC) {
        printOut(player, playerC);
        System.out.print("** ");
        System.out.println(dealer[1].getSuit() + "" + dealer[1].getNumber() + "\n");
    }

    public static int winCheck(Player player, int dealerScore){
        int outp;
        if(player.getPlayerScore() == 21 && dealerScore != 21) {
            outp = 2;
        }
        else if(player.getPlayerScore() > dealerScore && player.getPlayerScore() < 22) {
            outp = 2;
        }
        else if(dealerScore > 21 && player.getPlayerScore() < 22) {
            outp = 2;
        }
        else if(player.getPlayerScore() == dealerScore && dealerScore < 22) {
            outp = 1;
        }
        else {
            outp = 0;
        }
        return outp;
    }

}
