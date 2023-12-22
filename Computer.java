public class Computer {
    public static String computerMove(int computerTotal,int playerTotal,boolean isStand,Card[] extraCards,Card lastPlayedCard,String[] specialCards){
    if(specialCards.length == 1){
        if(isStand == true){
            if(computerTotal>playerTotal){
                return "stand";
            }else if(computerTotal==playerTotal){
                if(computerTotal<=10){
                        if(computerTotal==10||computerTotal==9){
                            //use x2 and win
                            if(specialCards[0] == "x2"){
                                return "x2";
                            }else{
                                return "draw";
                            }
                        }else{
                            return "draw";
                        }
                }else if(computerTotal<18){
                    for(int i=0;i<extraCards.length;i++){
                        if(extraCards[i].sign == "+"){
                             if(computerTotal + extraCards[i].value <= 20){
                                return String.valueOf(i);
                             }   
                        }else{
                            return "draw";
                        }
                    }
                }else{
                    return "stand";
                }
            }else{
                if(computerTotal<=10){
                    if(specialCards.length == 2){
                        if(computerTotal==10||computerTotal==9){
                            //use x2 and win
                        }else{
                            //draw card
                        }
                    }else{
                        if(computerTotal<18){
                            //draw card
                        }else{
                            //look special card and stand
                        }
                    }
                }
            } 
        }else{

        }
    }else{  //2 extra cards

    }
    }
}
