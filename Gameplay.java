import java.util.Scanner;

public class Gameplay {
    public static void startGame(){
        Card[] gameDesk = GameDesk.shuffleGameDesk();
        Card[] extraCardsPlayer = GameDesk.createExtraCards();
        Card[] extraCardsBot = GameDesk.createExtraCards();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to bluejack game!!!");
        String name = "";
        do{
            System.out.print("Please enter your name(max 10 character): ");
            name = sc.nextLine();
        }while(name.length()>10);
        System.out.println("Welcome the game "+ name + "\nGame is starting. You draw your first card...\n");
        System.out.println(String.format("Computer Hand: X X X X\nComputer Board: \n--------------------------------------------------\n%s's Board: \n%s's Hand: %s %s%d, %s %s%d, %s %s%d",
            name,name,
            extraCardsPlayer[0].color,extraCardsPlayer[0].sign,extraCardsPlayer[0].value,
            extraCardsPlayer[1].color,extraCardsPlayer[1].sign,extraCardsPlayer[1].value,
            extraCardsPlayer[2].color,extraCardsPlayer[2].sign,extraCardsPlayer[2].value)
            );
       
        
    }
}
