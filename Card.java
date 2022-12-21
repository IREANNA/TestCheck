public class Card {
    private String number;
    private int percent;

    public Card() {
        this.number = null;
        this.percent = 0;
    }
    public Card(String number, int percent) {
        this.number = number;
        this.percent = percent;
    }

    public String getNumber() {
        return number;
    }
    public int getPercent() {
        return percent;
    }

    public Card setNumber(String number) {
        this.number = number;
        return this;
    }
    public Card setPercent (int percent) {
        this.percent = percent;
        return this;
    }

    public String toString() {return number + " " + percent;}
}
