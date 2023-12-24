import java.util.Scanner;
import java.util.Random;

public class Gameplay {
    public static void startGame(){
        Card[] gameDesk = GameDesk.shuffleGameDesk();
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        Card[] playerHandCards = GameDesk.createHandCards(gameDesk);
        Card[] computerHandCards = GameDesk.createHandCards(gameDesk);
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
        Boolean isPlayerStand = false;
        Boolean isComputerStand = false;
        Boolean isComputerBluejack = false;
        Boolean isPlayerBluejack = false;
        String computerColors ="";
        String playerColors = "";
        int gameCount = 1;
        Boolean doYouWantToPlay = false;
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
        int playOrNot = 1;
        while (!doYouWantToPlay) {
           boolean checkError = false;
           while (!checkError) {
             try {
                 System.out.print("Do you want to play bluejack?\n1-Yes\n2-No\nEnter your choice: ");
                playOrNot = sc.nextInt();
                checkError = true;
            } catch (Exception e) {
                sc.nextLine();
            }
           }
           sc.nextLine();
           if(playOrNot==1){
             do{
            try {
                 System.out.print("Please enter your name(max 10 character): ");
                 name = sc.nextLine();
            } catch (Exception e) {
                sc.nextLine();
            }   
        }while(name.length()>10);
        System.out.println("Welcome the game "+ name + "\nGame started...You draw your first card.");
        while (!isGameEnd) {
            System.out.println("----------Scoreboard----------\n"+"     Computer: " + String.valueOf(computerWin) + ", " + name + ": " + String.valueOf(playerWin) + "\n----------Scoreboard----------\n" );
            if(isComputerBluejack==true){
                System.out.println("COMPUTER BLUEJACK AND WIN!!!");
                break;
            }
             if(isPlayerBluejack==true){
                System.out.println("YOU BLUEJACK AND WIN!!!");
                break;
            }
            if(playerWin==3){
                System.out.println("YOU WIN!!!");
                break;
            }
            if(computerWin==3){
                System.out.println("COMPUTER WIN!!!");
                break;
            }
            computerBoard = "";
            playerBoard = "";
            playerTotal = 0;
            computerTotal = 0;
             isPlayerPlay = false;
            isComputerPlay = true;
            isRoundFinished = false;
            isPlayerStand = false;
            isComputerStand = false;
        while (!isRoundFinished) {
        if(computerTotal==20 && GameDesk.containsOnlyBlue(computerColors)){
            isComputerBluejack = true;
            computerWin=3;
            break;
        }   
        if(playerTotal==20 && GameDesk.containsOnlyBlue(playerColors)){
            isPlayerBluejack = true;
            playerWin=3;
            break;
        }     
        if(isPlayerStand!= true && playerTotal>20){
            System.out.println("computer winned "+ gameCount +". tour!");
                computerWin++;
                 gameCount++;
                 isRoundFinished=true;
                 break;
        }else{
            isPlayerPlay =false;
        }
        if(isComputerStand!=true&&computerTotal>20){
                System.out.println(name + " winned " + gameCount +". tour!!!");
                playerWin++;
                 gameCount++;
                 break;
        }
        if(isComputerStand==true && isPlayerStand==true){
            if(computerTotal>playerTotal){
                 System.out.println("computer winned "+ gameCount +". tour!");
                computerWin++;
                 gameCount++;
                 break;
            }else if(playerTotal>computerTotal){
                 System.out.println(name + " winned " + gameCount +". tour!!!");
                playerWin++;
                 gameCount++;
                 break;
            }else{
                System.out.println(gameCount +". tour end tied!");
                gameCount++;
                break;
            }
        }
        do {
            while (!isComputerPlay) {
                if(playerTotal>20){
                    break;
                }
            computerBoard += gameDesk[cardCount].color + " "+ gameDesk[cardCount].sign + gameDesk[cardCount].value + " ";
            computerTotal += gameDesk[cardCount].value;
            computerColors += gameDesk[cardCount].color;
            String computerMove = Computer.computerMove(computerTotal, isPlayerStand, playerTotal, gameDesk[cardCount], computerStartHandCards);
            cardCount++;
            if("endTurn".equals(computerMove)){
                isComputerPlay = true;
                break;
            }else if("stand".equals(computerMove)){
                isComputerStand = true;
                isComputerPlay = true;
                break;
            }else{
                for(int i = 0;i<computerStartHandCards.length;i++){
                    if(Integer.toString(i)==computerMove){
                        if(computerStartHandCards[i].sign=="-"){
                            computerColors += computerStartHandCards[i].color;
                           computerTotal -=  computerStartHandCards[i].value;
                            computerHand=GameDesk.replaceCharAtIndex(computerHand, i*2, '0');
                          computerStartHandCards=GameDesk.removeCardElement(computerStartHandCards, i);
                            break;
                           }else if(computerStartHandCards[i].sign=="+"){
                             computerColors += computerStartHandCards[i].color;
                           computerTotal +=  computerStartHandCards[i].value;
                           computerHand=GameDesk.replaceCharAtIndex(computerHand, i*2, '0');
                           computerStartHandCards=GameDesk.removeCardElement(computerStartHandCards, i);
                            break;
                           }else if(computerStartHandCards[i].sign=="+/-"){
                           computerTotal -=  (gameDesk[cardCount-1].value)*2;
                            computerHand=GameDesk.replaceCharAtIndex(computerHand, i*2, '0');
                           computerStartHandCards=GameDesk.removeCardElement(computerStartHandCards, i);
                            computerBoard = GameDesk.changeSignBoard(computerBoard);
                            break;
                           }else if(computerStartHandCards[i].sign=="x2"){
                            computerTotal +=  gameDesk[cardCount-1].value;
                             computerHand=GameDesk.replaceCharAtIndex(computerHand, i*2, '0');
                            computerStartHandCards=GameDesk.removeCardElement(computerStartHandCards, i);
                            break;
                           }
                    }
                }
                isComputerPlay = true;
                break;
            }
            }
        if(isPlayerStand!=true){
            playerTotal += gameDesk[cardCount].value;
        playerBoard += gameDesk[cardCount].color + " " + gameDesk[cardCount].sign + gameDesk[cardCount].value + " ";
        computerColors += gameDesk[cardCount].color;
        cardCount++;
        System.out.println("Computer Hand: " + computerHand + "\nComputer Board: " + computerBoard + "\n------------------------------------------------\n" + name + "'s Board: " + playerBoard + "\n" + name + "'s Hand: " + playerHand);
        if(playerTotal>20){
                    isComputerPlay = true;
                    isPlayerPlay = true;
                    break;
                }
             if(isComputerStand==false){
             boolean done = false;
        while (!done) {
            try {
                  System.out.print("1-End Turn\n2-Use Special Card\n3-Stand\nEnter your move: ");
                    int playerMove = sc.nextInt();
                if(playerMove==1){
                     if(playerTotal>20){
                    isComputerPlay = true;
                    isPlayerPlay = false;
                    break;
                }else{
                    isComputerPlay = false;
                    isPlayerPlay = true;
                }
                    done = true;
                }else if(playerMove==2){
                    if(playerStartHandCards.length == 0){
                        break;
                    }
                    boolean done2 = false;
                    while (!done2) {
                      for(int i = 0;i<playerStartHandCards.length;i++){
                        if(playerStartHandCards[i].sign=="-"||playerStartHandCards[i].sign=="+"){
                            System.out.println((i+1)+"-"+playerStartHandCards[i].color + " " +playerStartHandCards[i].sign + playerStartHandCards[i].value );
                        }else{
                            System.out.println((i+1)+"-" +playerStartHandCards[i].sign  );
                        }  
                    }
                    try {
                        System.out.print("Choose a special card: ");
                        int playerSpecialMove = sc.nextInt();
                        boolean done3 = false;
                        for(int i = 0;i<playerStartHandCards.length;i++){
                            while (!done3) {
                                if(i == playerSpecialMove-1){
                                    playerHand = GameDesk.removeHandCard(playerStartHandCards, playerHand, i);
                            if(playerStartHandCards[i].sign=="-"){
                                playerColors += playerStartHandCards[i].color;
                             playerTotal -= playerStartHandCards[i].value;
                            playerBoard += playerStartHandCards[i].color + " " + "-" + playerStartHandCards[i].value + " ";
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            playerStartHandCards= GameDesk.removeCardElement(playerStartHandCards, i);
                            isComputerPlay = false;
                            isPlayerPlay=true;
                              done = true;
                            done2 = true; 
                            done3=true;
                   
                           }else if(playerStartHandCards[i].sign=="+"){
                            playerColors += playerStartHandCards[i].color;
                            playerTotal += playerStartHandCards[i].value;
                            playerBoard += playerStartHandCards[i].color + " " + "+" + playerStartHandCards[i].value + " ";
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            playerStartHandCards= GameDesk.removeCardElement(playerStartHandCards, i);
                            isComputerPlay = false;
                            isPlayerPlay=true;
                              done = true;
                            done2 = true; 
                            done3=true;
                          
                           }else if(playerStartHandCards[i].sign=="+/-"){
                            playerTotal -= gameDesk[cardCount-1].value;
                            playerBoard = GameDesk.changeSignBoard(playerBoard);
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            playerStartHandCards= GameDesk.removeCardElement(playerStartHandCards, i);
                            isComputerPlay = false;
                            isPlayerPlay=true;
                              done = true;
                            done2 = true; 
                            done3=true;
                           
                           }else if(playerStartHandCards[i].sign=="x2"){
                            playerTotal += gameDesk[cardCount-1].value;
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            playerStartHandCards= GameDesk.removeCardElement(playerStartHandCards, i);
                            isComputerPlay = false;
                            isPlayerPlay=true;
                              done = true;
                            done2 = true; 
                            done3=true;
                            
                           }
                             }  
                           break;
                            }
                                                 
                        }
                    } catch (Exception e) {
                        sc.nextLine();
                    }   
                    }

                }else if(playerMove==3){
                    isComputerPlay = false;
                    isPlayerStand=true;
                    isPlayerPlay = true;
                    done = true;
                    break;
                }
                break;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Enter number between 1-3");
            }
           
        }
        }else{
             boolean done = false;
        while (!done) {
            try {
                System.out.print("1-End Turn\n2-Use Special Card\n3-Stand\nEnter your move: ");
                int playerMove = sc.nextInt();
                if(playerMove==1){
                     if(playerTotal>20){
                    isComputerPlay = true;
                    isPlayerPlay = false;
                    break;
                }else{
                    isComputerPlay = false;
                    isPlayerPlay = true;
                }
                    done = true;
                }else if(playerMove==2){
                    if(playerStartHandCards.length == 0){
                        break;
                    }
                    boolean done2 = false;
                    while (!done2) {
                      for(int i = 0;i<playerStartHandCards.length;i++){
                        if(playerStartHandCards[i].sign=="-"||playerStartHandCards[i].sign=="+"){
                            System.out.println((i+1)+"-"+playerStartHandCards[i].color + " " +playerStartHandCards[i].sign + playerStartHandCards[i].value );
                        }else{
                            System.out.println((i+1)+"-" +playerStartHandCards[i].sign  );
                        }
                    }
                    System.out.print("Choose a special card: ");
                    int playerSpecialMove = sc.nextInt();
                    try {
                        boolean done3 = false;
                        for(int i = 0;i<playerStartHandCards.length;i++){
                            while (!done3) {
                                if(i == playerSpecialMove-1){
                                    playerHand = GameDesk.removeHandCard(playerStartHandCards, playerHand, i);
                            if(playerStartHandCards[i].sign=="-"){
                                playerColors += playerStartHandCards[i].color;
                             playerTotal -= playerStartHandCards[i].value;
                            playerBoard += playerStartHandCards[i].color + " " + "-" + playerStartHandCards[i].value + " ";
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            playerStartHandCards= GameDesk.removeCardElement(playerStartHandCards, i);
                            isPlayerStand=true;
                            isPlayerPlay=true;
                            done3=true;
                            break;
                           }else if(playerStartHandCards[i].sign=="+"){
                            playerColors += playerStartHandCards[i].color;
                            playerTotal += playerStartHandCards[i].value;
                            playerBoard += playerStartHandCards[i].color + " " + "+" + playerStartHandCards[i].value + " ";
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            playerStartHandCards= GameDesk.removeCardElement(playerStartHandCards, i);
                           isPlayerStand=true;
                            isPlayerPlay=true;
                            done3=true;
                             break;
                           }else if(playerStartHandCards[i].sign=="+/-"){
                            playerTotal -= gameDesk[cardCount-1].value;
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            playerStartHandCards= GameDesk.removeCardElement(playerStartHandCards, i);
                               playerBoard = GameDesk.changeSignBoard(playerBoard);
                            isPlayerStand=true;
                            isPlayerPlay=true;
                            done3=true;
                             break;
                           }else if(playerStartHandCards[i].sign=="x2"){
                            playerTotal += gameDesk[cardCount-1].value;
                            playerHandCards[i].color="";
                            playerHandCards[i].value=0;
                            playerHandCards[i].sign="";
                            playerStartHandCards= GameDesk.removeCardElement(playerStartHandCards, i);
                            isPlayerStand=true;
                            isPlayerPlay=true;
                            done3=true;
                             break;
                           }
                             }  
                           break;
                            }
                            isComputerPlay = true;
                            isPlayerPlay=true;
                            done = true;
                            done2 = true;    
                                                 
                        }
                          break;
                    } catch (Exception e) {
                       sc.nextLine();
                    }   
                    }

                }else if(playerMove==3){
                    isComputerPlay = false;
                    isPlayerStand=true;
                    isPlayerPlay = true;
                    done = true;
                    break;
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Enter number between 1-3");
            }
        }
        }
        
        }else{
             System.out.println("Computer Hand: " + computerHand + "\nComputer Board: " + computerBoard + "\n------------------------------------------------\n" + name + "'s Board: " + playerBoard + "\n" + name + "'s Hand: " + playerHand);
             isComputerPlay = false;
             isPlayerPlay = true;
             isPlayerPlay = true;
        }
       } while (!isPlayerPlay);
       } 
        }
           }else if(playOrNot == 2){
                break;
           }else{
            continue;
           }
           
        }
        
        sc.close();
    }
}
