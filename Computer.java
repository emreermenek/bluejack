public class Computer {
    public static String computerMove(int computerTotal,boolean isPlayerStand, int playerTotal,Card lastPlayedCard,Card[] computerStartHandCards){
       if(isPlayerStand==true){
        if(computerTotal==playerTotal){
            for(int i =0;i<computerStartHandCards.length;i++){
                if(computerStartHandCards[i].sign=="+"){
                    if(computerTotal+computerStartHandCards[i].value<=20){
                        return Integer.toString(i);
                    }
                   break;
                  }else if(computerStartHandCards[i].sign=="x2"){
                    if(computerTotal+lastPlayedCard.value<=20){
                       return Integer.toString(i);
                    }
                   break;
                  }
            }
            return "stand";
        }else if(computerTotal>playerTotal){
            if(computerTotal<=20){
                return "stand";
            }else{
                for(int i =0;i<computerStartHandCards.length;i++){
                if(computerStartHandCards[i].sign=="-"){
                    if(computerTotal-computerStartHandCards[i].value<=20){
                        return Integer.toString(i);
                    }
                   break;
                  }
            }
            }
        }else{
            if(computerTotal<15){
                 for(int i =0;i<computerStartHandCards.length;i++){
                if(computerStartHandCards[i].sign=="x2"){
                    if(computerTotal-computerStartHandCards[i].value>playerTotal){
                        return Integer.toString(i);
                    }
                   break;
                  } 
            }
            return "endTurn";
            }else{
                for(int i =0;i<computerStartHandCards.length;i++){
                if(computerStartHandCards[i].sign=="+"){
                    if(computerTotal+computerStartHandCards[i].value<=20){
                        return Integer.toString(i);
                    }
                   break;
                  }else if(computerStartHandCards[i].sign=="x2"){
                    if(computerTotal+lastPlayedCard.value<=20){
                       return Integer.toString(i);
                    }
                   break;
                  }
            }
            return "endTurn";
            }
        }
       }else{
        if(computerTotal<=17){
            return "endTurn";
        }else{
            return "stand";
        }
       }
    return null;
    }
}
