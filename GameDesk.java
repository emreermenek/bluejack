import java.util.Random;

public class GameDesk {
    public static Card[] createGameDesk(){
        Card[] deck = new Card[40];
        int j = 1;
        int x = 1;
        int y = 1;
        int z = 1;
        for(int i = 0;i<40;i++){
            if(i<10){
                deck[i] = new Card("green",j,"+");
                j++;
            }else if(i>9&&i<20){
                deck[i] = new Card("yellow",x,"+");
                x++;
            }else if(i>19&&i<30){
               
                deck[i] = new Card("red",y,"+");
                y++;
            }else if(i>29){
                
                deck[i] = new Card("blue",z,"+");
                z++;
            }
        }
        return deck;
    }
       public static Card[] shuffleGameDesk(){
            Card[] deck = GameDesk.createGameDesk();  
            for (int i = 0; i < deck.length; i++) {
                int index = (int)(Math.random() * deck.length);
                Card temp = deck[i];
                deck[i] = deck[index];
                deck[index] = temp;
            }
            return deck;
    }

    public static Card[] createExtraCards(){
        String[] colors = {"red","blue","yellow","green"};
        String[] signs = {"-","+"};
        int[] values = {1,2,3,4,5,6};
        Card[] extraCards = new Card[3];
        Random random = new Random();
        for(int i = 0; i<3;i++){
            extraCards[i] = new Card(colors[random.nextInt(4)],values[random.nextInt(6)],signs[random.nextInt(2)]);
        }
        return extraCards;
    }
       public static Card createSecondExtraCard(){
        String[] colors = {"red","blue","yellow","green"};
        String[] signs = {"-","+"};
        int[] values = {1,2,3,4,5,6};
        Random random = new Random();
        Card extraCard = new Card(colors[random.nextInt(4)],values[random.nextInt(6)],signs[random.nextInt(2)]);
        return extraCard;
    }
    public static Card specialCard(){
        Random random = new Random();
        double probability = random.nextDouble();
        if(probability<0.20){
            if(probability<0.10){
                 return new Card("",0,"x2");
            }else{
                 return new Card("",0,"+/-");
            }
        }else{
            return createSecondExtraCard();
        }
    }
     public static int[] removeElement(int[] array, int index) {
        int[] newArray = new int[array.length-1];
        for(int i=0; i<array.length;i++){
             if(i<index){
                newArray[i] = array[i];
            }
            else if(i>index){
                newArray[i-1] = array[i];
             }
    }
        return newArray;
    }
      public static Card[] removeCardElement(Card[] array, int index) {
        Card[] newArray = new Card[array.length-1];
        for(int i=0; i<array.length;i++){
             if(i<index){
                newArray[i] = array[i];
            }
            else if(i>index){
                newArray[i-1] = array[i];
             }
    }
        return newArray;
    }
    public static String changeSignBoard(String board){
        int index = board.lastIndexOf("+");
        char[] charArray = board.toCharArray();
        charArray[index] = '-';
        board = new String(charArray);
        return board;
    }
    public static String replaceCharAtIndex(String original, int index, char replacement) {
        char[] chars = original.toCharArray();
        chars[index] = replacement;
        return new String(chars);
    }
    public static String doubleLastNumber(String inputString) {
        // Split the string into words
        String[] words = inputString.split("\\s+");

        // Find the last word that represents a number
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i];
            if (word.matches("\\d+")) {
                // Convert the number to an integer and double it
                int originalNumber = Integer.parseInt(word);
                int doubledNumber = originalNumber * 2;

                // Replace the original number with its doubled version in the string
                words[i] = Integer.toString(doubledNumber);
                break;  // Stop after replacing the last number
            }
        }

        // Join the words back into a string
        return String.join(" ", words);
    }
}
