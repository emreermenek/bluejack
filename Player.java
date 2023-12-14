public class Player {
    String name;
    Card[] hand;
    SpecialCard[] specialCard;

    Player(String name, Card[] hand, SpecialCard[] specialCards){
        this.name = name;
        this.hand = hand;
        this.specialCard = specialCards;
    }

}
