import java.util.Random;

public class GameDesk {
    public static Card[] createGameDesk(){
        Card[] deck = new Card[40];
        for(int i = 0;i<40;i++){
            if(i<10){
                deck[i] = new Card("green",i+1,"+");
            }else if(i>9&&i<20){
                deck[i] = new Card("yellow",i-9,"+");
            }else if(i>19&&i<30){
                deck[i] = new Card("red",i-19,"+");
            }else if(i>29){
                deck[i] = new Card("blue",i-29,"+");
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
        if (index < 0 || index >= array.length) {
            return array;
        }
        Card[] newArray = new Card[array.length - 1];
        
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, newArray.length - index);
    
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

    public static String removeHandCard(Card[] playerStartHandCards,String hand,int i){
        String target =  "";
        if("+".equals(playerStartHandCards[i].sign) || "-".equals(playerStartHandCards[i].sign)){
            target = playerStartHandCards[i].color + " " + playerStartHandCards[i].sign + playerStartHandCards[i].value;
        }else{
                target = playerStartHandCards[i].sign;
               target = playerStartHandCards[i].sign;
        }
        hand = hand.replace(target, "");
        return hand;
    }
    public static boolean containsOnlyBlue(String input) {
        String result = input.replaceAll("blue", "");
        return result.isEmpty();
    }
    public static Card[] createHandCards(Card[] gameDesk){
        Card[] handCards = new Card[10];
        Card[] extraCards = GameDesk.createExtraCards();
        for(int i = 0;i<5;i++){
            handCards[i] = gameDesk[i];
        }
        for(int i = 0;i<3;i++){
            handCards[i+5] = extraCards[i];
        }
        for(int i = 0; i<2;i++){
            handCards[i+8] = GameDesk.specialCard();
        }
        return handCards;
    }
    public static String doubledValueOfNumber(String hand) {
        String[] parts = hand.split(" ");
        int lastNumber = Integer.parseInt(parts[parts.length - 1]);
        int doubledNumber = lastNumber * 2;

        parts[parts.length - 1] = String.valueOf(doubledNumber);
    
        String output = String.join(" ", parts);
        
        return output;
    }
}
