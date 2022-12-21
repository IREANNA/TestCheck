import java.util.Date;

public class Cash {
    static String cashTitle = "             CASH RECEIPT\n";
    static String tableTitle = "\nDESCRIPTION     PRICE   QTY  TOTAL    PROMO  ";
    static String separator1 = "\n---------------------------------------------";
    static String separator2 = "\n=============================================";
    private String cashier;
    private Date date;
    //private Product[] basket;
    private double total;
    private double promo;
    private Card card;
    private double discount;
    private double pay;

    public Cash(String cashier, double total, double promo, Card card, double discount, double pay) {
        setDate();
        setCard(card);
        this.cashier = cashier;
        this.date = getDate();
        this.total = total;
        this.promo = promo;
        this.card = getCard();
        this.discount = discount;
        this.pay = pay;
    }

    public String toString1() {
        return cashTitle + "Cashier: " + cashier + "\ndate: " + date + separator2 +tableTitle + separator1;
    }
    public String toString2() {
        return separator2 + "\nTotal: " + total + "\nCard " + card + "\nDiscount " + discount + "\nPay: " +pay;
    }

    public String getCashier() {
        return cashier;
    }
    public Date getDate() {
        return date;
    }
    //public Product getBasket() { return basket;};
    public double getTotal() {
        return total;
    }
    public double getPromo() {
        return promo;
    }
    public Card getCard() {
        return card;
    }
    public int getCardPercent() {
        return card.getPercent();
    }
    public double getDiscount() {
        return discount;
    }
    public double getPay() {
        return pay;
    }

    public Cash setCashier(String cashier) {
        this.cashier = cashier;
        return this;
    }
    public Cash setDate() {
        Date date = new Date();
        this.date = date;
        return this;
    }
    //public Product[] setBasket(Product[] basket) {
    //    this.basket = new Product[basket.length];
    //    for (int i=0; i<basket.length; i++) {
    //this.basket[i] = new Product.ProductBuilder(basket[j].getId(),
    //                                            basket[j].getName(),
    //                                            basket[j].getPromoB(),
    //                                            basket[j].getPrice())
    //                                            .setQuantity(quantity)
    //                                            .setTotal()
    //                                            .setPromoD().build();
    //    return this;
    //}
    public Cash setTotal(double total) {
        this.total = total;
        return this;
    }
    public Cash setPromo(double promo) {
        this.promo = promo;
        return this;
    }
    public Cash setCard(Card card) {
        this.card = new Card(card.getNumber(), card.getPercent());
        return this;
    }
    public Cash setDiscount(double discount) {
        this.discount = discount;
        return this;
    }
    public Cash setPay(double pay) {
        this.pay = pay;
        return this;
    }

}
