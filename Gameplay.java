import java.util.Scanner;
import java.util.Random;

public class Gameplay {
    public static void startGame(){
        Card[] gameDesk = GameDesk.shuffleGameDesk();
        Card[] extraCardsPlayer = GameDesk.createExtraCards();
        Card[] extraCardsComputer = GameDesk.createExtraCards();
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        Card[] playerHandCards = new Card[10];
        Card[] computerHandCards = new Card[10];
         Card[] playerStartHandCards = new Card[4];
        Card[] computerStartCards = new Card[4];
        System.out.println("Welcome to bluejack game!!!");
        String name = "";
        String computerBoard = "";
        String playerBoard = "";
        String playerHand = "";
        String computerHand ="";
        int playerWin = 0;
        int computerWin = 0;
        int playerTotal = 0;
        int computerTotal = 0;
        int playerPlayCount = 0;
        int computerPlayCount = gameDesk.length-1;
        Boolean isGameEnd = false;
        Boolean isMoveHappen = false;
        Boolean isRoundFinished = false;
        Boolean isStand = false;
        String[] computerSpecialCards = new String[2];
        playerHand =  String.valueOf(extraCardsPlayer[0].color) + " " +String.valueOf(extraCardsPlayer[0].sign) + String.valueOf(extraCardsPlayer[0].value) + ", "+ String.valueOf(extraCardsPlayer[1].color) + " " +String.valueOf(extraCardsPlayer[1].sign) + String.valueOf(extraCardsPlayer[1].value) + ", "+ String.valueOf(extraCardsPlayer[2].color) + " " +String.valueOf(extraCardsPlayer[2].sign) + String.valueOf(extraCardsPlayer[2].value);
        for(int i = 0;i<5;i++){
            playerHandCards[i] = gameDesk[i];
        }
        int a = 5;
        for(int i = 0;i<3;i++){
            playerHandCards[a] = extraCardsPlayer[i];
            a++;
        }
        for(int i = 0; i<2;i++){
            playerHandCards[a] = GameDesk.specialCard();
            a++;
        }
        int b = 5;
        int j = 0;
        for(int i = 39;i<34;i--){
            playerHandCards[j] = gameDesk[i];
            j++;
        }
         for(int i = 0;i<3;i++){
            computerHandCards[b] = extraCardsComputer[i];
            b++;
        }
          for(int i = 0; i<2;i++){
            computerHandCards[b] = GameDesk.specialCard();
            b++;
        }
        int[] list = {0,1,2,3,4,5,6,7,8,9};
        int o = 10;
        for(int i = 0; i<4; i++){
            int y = random.nextInt(o);
            playerStartHandCards[i] = playerHandCards[list[y]];
            o--;
            list = GameDesk.removeElement(list, y);
            System.out.println(playerStartHandCards[i].sign);
        }
        


        
        do{
            System.out.print("Please enter your name(max 10 character): ");
            name = sc.nextLine();
        }while(name.length()>10);
        System.out.println("Welcome the game "+ name + "Game started...You draw your first card.");
        playerBoard = String.valueOf(gameDesk[0].color) + " " +String.valueOf(gameDesk[0].sign) + String.valueOf(gameDesk[0].value);
        playerTotal += gameDesk[playerPlayCount].value;
        playerPlayCount += 1;
        
        do {
                System.out.println("----------Scoreboard----------\n"+"     Computer: " + String.valueOf(computerWin) + ", " + name + ": " + String.valueOf(playerWin) + "\n----------Scoreboard----------\n" );
                System.out.println("Computer Hand: " + computerHand + "\nComputer Board: " + computerBoard + "\n------------------------------------------------\n" + name + "'s Board: " + playerBoard + "\n" + name + "'s Hand: " + playerHand);
             do {
                 do {
                    Boolean isMoveTrue = false;
                    do {
                          try {
                            System.out.print("Choose your next move: 1-Pass  2-Play-Special-Card 3-Stand\nChoose by number: ");
                            int move = sc.nextInt();
                            if(move ==1){
                                computerTotal += gameDesk[computerPlayCount].value;
                                computerBoard = String.valueOf(gameDesk[computerPlayCount].color) + " " +String.valueOf(gameDesk[computerPlayCount].sign) + String.valueOf(gameDesk[computerPlayCount].value);
                                computerPlayCount -= 1;
                                isMoveTrue = true;
                                isMoveHappen = true;
                            }else if(move == 2){
                                isMoveTrue = true;
                            }else if(move == 3){
                                isMoveTrue = true;
                                Computer.computerMove(computerTotal,playerTotal,true,extraCardsComputer,gameDesk[computerPlayCount],computerSpecialCards);
                            }else{
                                System.out.println("Enter a number between 1-3");
                            }
                    } catch (Exception e) {
                        // TODO: move choose 
                        sc.nextLine();
                        System.out.println("Enter a number between 1-3");
                    }
                        } while (isMoveTrue == false);
                    } while(isMoveHappen == false);
                } while (isRoundFinished == false);
            } while (isGameEnd == false);
       
       
        
    }
}
