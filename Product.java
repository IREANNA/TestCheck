public class Product {
    private int id;
    private String name;
    private boolean promoB;
    private double price;
    private double quantity;
    private double total;
    private double promoD;


    public String toString() {
        return id + " " + name + "       " + price + "   " + quantity + "   " + total + "   " + promoD;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean getPromoB() {
        return promoB;
    }
    public double getPrice() {
        return price;
    }
    public double getQuantity() {
        return quantity;
    }
    public double getTotal() {
        return total;
    }
    public double getPromoD() { return promoD; }

    public Product setId(int id) {
        this.id = id;
        return this;
    }
    public Product setPrice(double price) {
        this.price = price;
        return this;
    }
    public Product setName(String name) {
        this.name = name;
        return this;
    }
    public Product setPromoB(boolean promoB) {
        this.promoB = promoB;
        return this;
    }
    public Product setQuantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product(ProductBuilder productBuilder) {
        id = productBuilder.getId();
        name = productBuilder.getName();
        promoB = productBuilder.getPromoB();
        price = productBuilder.getPrice();
        quantity = productBuilder.getQuantity();
        total = productBuilder.getTotal();
        promoD = productBuilder.getPromoD();
    }


    public static class ProductBuilder {
        private int id;
        private double price;
        private boolean promoB;
        private String name;
        private double quantity;
        private double total;
        private double promoD;

        public ProductBuilder(int id, String name, boolean promoB, double price) {
            this.id = id;
            this.name = name;
            this.promoB = promoB;
            this.price = price;
        }

        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public boolean getPromoB() {
            return promoB;
        }
        public double getPrice() {
            return price;
        }
        public double getQuantity() {
            return quantity;
        }
        public double getTotal() {
            return total;
        }
        public double getPromoD() {
            return promoD;
        }


        public ProductBuilder setQuantity(double quantity) {
            this.quantity = quantity;
            return this;
        }
        public ProductBuilder setTotal() {
            this.total = Math.round(price * quantity * 100)/100.0 ;
            return this;
        }
        public ProductBuilder setPromoD() {
            double costD = 0;
            if (promoB && (quantity >= 5)) {
                costD = Math.round(price * quantity * 10)/100.0;
            }
            this.promoD = costD;
            return this;
        }


        public Product build() {
            return new Product(this);
        }

    }
}

