public class GameDesk {
    public static void createGameDesk(){
        Card[] deck = new Card[40];
        int j = 1;
        for(int i = 0;i<40;i++){
            if(i<10){
                deck[i] = new Card("green",j,"+");
                j++;
            }else if(i>9&&i<20){
                j = 1;
                deck[i] = new Card("yellow",j,"+");
                j++;
            }else if(i>19&&i<30){
                j = 1;
                deck[i] = new Card("red",j,"+");
                j++;
            }else if(i>29){
                j = 1;
                deck[i] = new Card("blue",j,"+");
                j++;
            }
        }
        
        
    }
}
