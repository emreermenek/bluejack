public class Player {
    String name;
    int winRate;
    Card playerDeck;
    Card[] extraCards = new Card[3];
    String specialCard;

    Player(String name, int winRate, Card playerDeck,Card[] extraCards, String specialCard){
        this.name = name;
        this.winRate = winRate;
        this.playerDeck = playerDeck;
        this.extraCards = extraCards;
        this.specialCard = specialCard;
    }

}
