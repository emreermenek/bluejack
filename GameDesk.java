public class GameDesk {
    public static int[] createGameDesk(){
        int[] deck = new int[40];
        String[] colors = {"mavi","kirmizi","sari","yesil"};
        int j = 1;
        //desk initialized
        for (int i = 0; i < 40; i++) {
            if(j<11){
                deck[i] = j;
                j++;
            }else{
                j=1;
            }
        }
        return deck;
    }
}
