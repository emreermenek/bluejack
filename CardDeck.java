public class CardDeck {
        public static void main(String[] args) {
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
            for(int i =0;i<40;i++){
                System.out.print("Color: " + deck[i].color + " value: "+ deck[i].sign +deck[i].value + "\n");
            }   
         
        }
      }

