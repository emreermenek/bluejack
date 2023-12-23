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
        Card[] computerStartHandCards = new Card[4];
        System.out.println("Welcome to bluejack game!!!");
        String name = "";
        String computerBoard = "";
        String playerBoard = "";
        String playerHand = "";
        String computerHand ="X X X X";
        int playerWin = 0;
        int computerWin = 0;
        int playerTotal = 0;
        int computerTotal = 0;
        int cardCount = 5;
        Boolean isGameEnd = false;
        Boolean isPlayerPlay = false;
        Boolean isComputerPlay = true;
        Boolean isRoundFinished = false;
        Boolean isMoveTrue = false;
        Boolean isPlayerStand = false;
        Boolean isComputerStand = false;
        Boolean isBlueJack = false;
        String[] computerSpecialCards = new String[2];
        int gameCount = 1;
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
        int j = 0;
        for(int i = 39;i>34;i--){
            computerHandCards[j] = gameDesk[i];
            j++;
        }
         for(int i = 0;i<3;i++){
            computerHandCards[j] = extraCardsComputer[i];
            j++;
        }
          for(int i = 0; i<2;i++){
            computerHandCards[j] = GameDesk.specialCard();
            j++;
        }
        int[] list = {0,1,2,3,4,5,6,7,8,9};
        int o = 10;
        for(int i = 0; i<4; i++){
            int y = random.nextInt(o);
            playerStartHandCards[i] = playerHandCards[list[y]];
            o--;
            list = GameDesk.removeElement(list, y);
            if(playerStartHandCards[i].sign == "x2" || playerStartHandCards[i].sign == "+/-"){
                playerHand += playerStartHandCards[i].sign + " ";
            }else{
                playerHand += playerStartHandCards[i].color + " " + playerStartHandCards[i].sign + playerStartHandCards[i].value + " ";
            }
        }
        
         o = 10;
        int[] list2 = {0,1,2,3,4,5,6,7,8,9};
         for(int i = 0; i<4; i++){
            int y = random.nextInt(o);
            computerStartHandCards[i] = computerHandCards[list2[y]];
            o--;
            list2 = GameDesk.removeElement(list2, y);
        }
   
        do{
            System.out.print("Please enter your name(max 10 character): ");
            name = sc.nextLine();
        }while(name.length()>10);
        System.out.println("Welcome the game "+ name + "Game started...You draw your first card.");
        System.out.println("----------Scoreboard----------\n"+"     Computer: " + String.valueOf(computerWin) + ", " + name + ": " + String.valueOf(playerWin) + "\n----------Scoreboard----------\n" );

    
        //gameplay
       while (!isRoundFinished) {
        if(isPlayerStand!= true && playerTotal>20){
            System.out.println("computer winned"+ gameCount +". tour!");
                computerWin++;
                 gameCount++;
                 isRoundFinished=true;
                 break;
        }
        if(isComputerStand!=true&&computerTotal>20){
                System.out.println(name + " winned " + gameCount +". tour!!!");
                playerWin++;
                 gameCount++;
        }
        if(isComputerStand==true && isPlayerStand==true){
            if(computerTotal>playerTotal){
                 System.out.println("computer winned"+ gameCount +". tour!");
                computerWin++;
                 gameCount++;
            }else if(playerTotal>computerTotal){
                 System.out.println(name + " winned " + gameCount +". tour!!!");
                playerWin++;
                 gameCount++;
            }else{
                System.out.println(gameCount +". tour end tied!");
                gameCount++;
            }
            isRoundFinished = true;
            break;
        }
        do {
            while (!isComputerPlay) {
                computerBoard += gameDesk[cardCount].color + " "+ gameDesk[cardCount].sign + gameDesk[cardCount].value + " ";
            computerTotal += gameDesk[cardCount].value;
            String computerMove = Computer.computerMove(computerTotal, isPlayerStand, playerTotal, gameDesk[cardCount], computerStartHandCards);
            cardCount++;
            if(computerMove == "endTurn"){
                isComputerPlay = true;
            }else if(computerMove=="stand"){
                isComputerStand = true;
                isComputerPlay = true;
            }else{
                for(int i = 0;i<computerStartHandCards.length;i++){
                    if(Integer.toString(i)==computerMove){
                        if(computerStartHandCards[i].sign=="-"){
                           computerTotal -=  computerStartHandCards[i].value;
                            break;
                           }else if(computerStartHandCards[i].sign=="+"){
                           computerTotal +=  computerStartHandCards[i].value;
                            break;
                           }else if(computerStartHandCards[i].sign=="+/-"){
                           computerTotal -=  (gameDesk[cardCount-1].value)*2;
                            break;
                           }else if(computerStartHandCards[i].sign=="x2"){
                            computerTotal +=  gameDesk[cardCount-1].value;
                            break;
                           }
                    }
                }
                isComputerPlay = true;
            }
            }
        if(isPlayerStand!=true&&isComputerStand != true){
            playerTotal += gameDesk[cardCount].value;
        playerBoard += gameDesk[cardCount].color + " " + gameDesk[cardCount].sign + gameDesk[cardCount].value + " ";
        cardCount++;
        System.out.println("Computer Hand: " + computerHand + "\nComputer Board: " + computerBoard + "\n------------------------------------------------\n" + name + "'s Board: " + playerBoard + "\n" + name + "'s Hand: " + playerHand);
             if(isComputerStand==false){
             boolean done = false;
        while (!done) {
            System.out.print("1-End Turn\n2-Use Special Card\n3-Stand\nEnter your move: ");
            int playerMove = sc.nextInt();
            try {
                if(playerMove==1){
                    isComputerPlay = false;
                    isPlayerPlay = true;
                    done = true;
                }else if(playerMove==2){
                    boolean done2 = false;
                    while (!done2) {
                      for(int i = 0;i<playerStartHandCards.length;i++){
                        System.out.println((i+1)+"-"+playerStartHandCards[i].color + " " +playerStartHandCards[i].sign + playerStartHandCards[i].value + " " );
                    }
                    System.out.print("Choose a special card: ");
                    int playerSpecialMove = sc.nextInt();
                    try {
                        boolean done3 = false;
                        for(int i = 0;i<playerStartHandCards.length;i++){
                            while (!done3) {
                                if(i == playerSpecialMove-1){
                            if(playerStartHandCards[i].sign=="-"){
                             playerTotal -= playerStartHandCards[i].value;
                            playerBoard += playerStartHandCards[i].color + " " + "-" + playerStartHandCards[i].value + " ";
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            isComputerPlay = false;
                            isPlayerPlay=true;
                            done=true;
                            done2=true;
                            done3=true;
                            break;
                           }else if(playerStartHandCards[i].sign=="+"){
                            playerTotal += playerStartHandCards[i].value;
                            playerBoard += playerStartHandCards[i].color + " " + "+" + playerStartHandCards[i].value + " ";
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            isComputerPlay = false;
                            isPlayerPlay=true;
                            done=true;
                            done2=true;
                            done3=true;
                            break;
                           }else if(playerStartHandCards[i].sign=="+/-"){
                            playerTotal -= gameDesk[cardCount-1].value;
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            isComputerPlay = false;
                            isPlayerPlay=true;
                            done=true;
                            done2=true;
                            done3=true;
                            break;
                           }else if(playerStartHandCards[i].sign=="x2"){
                            playerTotal += gameDesk[cardCount-1].value;
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            isComputerPlay = false;
                            isPlayerPlay=true;
                            done=true;
                            done2=true;
                            done3=true;
                            break;
                           }
                                }  
                            }
                        }
                    } catch (Exception e) {
                       sc.nextInt();
                    }   
                    }

                }else if(playerMove==3){
                    isComputerPlay = false;
                    isPlayerStand=true;
                    isPlayerPlay = true;
                    done = true;
                }
            } catch (Exception e) {
                System.out.println("Enter number between 1-3");
            }
        }
        }else{
            boolean done = false;
        while (!done) {
            System.out.print("1-Draw Card\n2-Use Special Card\n3-Stand\nEnter your move: ");
            int playerMove = sc.nextInt();
            try {
                if(playerMove==1){
                    isPlayerPlay = true;
                    done = true;
                }else if(playerMove==2){
                    boolean done2 = false;
                    while (!done2) {
                      for(int i = 0;i<playerStartHandCards.length;i++){
                        System.out.println((i+1)+"-"+playerStartHandCards[i].color + " " +playerStartHandCards[i].sign + playerStartHandCards[i].value + " " );
                    }
                    System.out.print("Choose a special card: ");
                    int playerSpecialMove = sc.nextInt();
                    try {
                        boolean done3 = false;
                        for(int i = 0;i<playerStartHandCards.length;i++){
                            while (!done3) {
                                if(i == playerSpecialMove-1){
                            if(playerStartHandCards[i].sign=="-"){
                             playerTotal -= playerStartHandCards[i].value;
                            playerBoard += playerStartHandCards[i].color + " " + "-" + playerStartHandCards[i].value + " ";
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            isPlayerStand=true;
                            isPlayerPlay=true;
                            done=true;
                            done2=true;
                            done3=true;
                            break;
                           }else if(playerStartHandCards[i].sign=="+"){
                            playerTotal += playerStartHandCards[i].value;
                            playerBoard += playerStartHandCards[i].color + " " + "+" + playerStartHandCards[i].value + " ";
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            isPlayerStand=true;
                            isPlayerPlay=true;
                            done=true;
                            done2=true;
                            done3=true;
                            break;
                           }else if(playerStartHandCards[i].sign=="+/-"){
                            playerTotal -= gameDesk[cardCount-1].value;
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            isPlayerStand=true;
                            isPlayerPlay=true;
                            done=true;
                            done2=true;
                            done3=true;
                            break;
                           }else if(playerStartHandCards[i].sign=="x2"){
                            playerTotal += gameDesk[cardCount-1].value;
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            isPlayerStand=true;
                            isPlayerPlay=true;
                            done=true;
                            done2=true;
                            done3=true;
                            break;
                           }
                                }  
                            }
                        }
                    } catch (Exception e) {
                       sc.nextInt();
                    }   
                    }

                }else if(playerMove==3){
                    isPlayerStand=true;
                    isPlayerPlay = true;
                    done = true;
                }
            } catch (Exception e) {
                System.out.println("Enter number between 1-3");
            }
        }
        }
        
        }else{
             System.out.println("Computer Hand: " + computerHand + "\nComputer Board: " + computerBoard + "\n------------------------------------------------\n" + name + "'s Board: " + playerBoard + "\n" + name + "'s Hand: " + playerHand);
             break;
        }
       } while (!isPlayerPlay);
       }
       
    
        
      
       
        
    }
}
