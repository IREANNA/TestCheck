import java.io.*;
import java.util.*;

public class CheckRunner {


    public static int numberInt (String string) {                       // метод форматирования строки в число
        int stringToInt = Integer.parseInt(string);
        return stringToInt;
    }

    public static double numberDouble (String string) {                // метод форматирования строки в число с точкой
        String[] elementsNumber = string.split(",");
        double stringToDouble = Integer.parseInt(elementsNumber[0]) + Integer.parseInt(elementsNumber[1])/100.0;
        return stringToDouble;
    }
    public static void InfoOfShop() {
        System.out.println("********************************************");
        System.out.println("             SUPERMARKET 111 ");
        System.out.println("            Gomel, Victory, 5");
        System.out.println("          tel: +375 23 123-45-67");
        System.out.println("********************************************");
    }



    public static void main(String[] args) {
        //String[] args1 = {"C:\\Users\\msi\\list_of_products.txt", "23", "2-2,0", "7-0,75", "5-5,0", "card-4567"}; // для проверки
        int n=0, i=0, j=0;
        String file = args[0];
        File l_o_p = new File(file);                                            // определяем файл для чтения товара
        try (Scanner listP = new Scanner(l_o_p)) {                              // читаем инфу о товаре из файла
            while (listP.hasNextLine()) {
                if (listP.nextLine() != null) {n++;}                            // определение количества информативных строк
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception");
        }
        catch ( NoSuchElementException e) {
            System.out.println(" No Such Element Exception (an empty line?)");
        }
        //System.out.println(n);                                                // для проверки чтения файла
        Product[] infoListProduct = new Product[n];                             // определяем массив из объктов товара

        try (Scanner listP = new Scanner(l_o_p);){                              // обработка информации о товаре
            while (listP.hasNextLine()) {
                String[] element_of_list = listP.nextLine().split("/");   // разделили строку на элементы
                int id = numberInt(element_of_list[0]);                         // форматирование строки в целое число
                int s = 1/element_of_list[1].length();                          // генерация ошибки отсутствия наименования товара
                String name = element_of_list[1];
                boolean promo = element_of_list[1].startsWith("*");
                double price = numberDouble(element_of_list[2]);                // форматирование строки в число с точкой
                Product product = new Product.ProductBuilder(id, name, promo, price).build();    // сохранение данных в массив Товара
                infoListProduct[i] = product;
                i++;
                //System.out.println(product.toString());                       //  для проверки считывания данных товара
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File of Product Not Found Exception");
        }
        catch (NoSuchElementException e) {
            System.out.println("No Such Element Exception");
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid Data Of Product (missing data  product number or an empty line or invalid data format?) ");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Data Of Product (missing separator or data of price not found?)");
        }
        catch (ArithmeticException e) {
            System.out.println("Missing data product name");
        }


        File l_o_c = new File("C:\\Users\\msi\\list_of_cards.txt");
        n=0;
        try (Scanner listC = new Scanner(l_o_c)) {                                 // читаем инфу о картах из файла
            while (listC.hasNextLine()) {
                if (listC.nextLine() != null) {n++;}                               // определение количества информативных строк
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception");
        }
        catch ( NoSuchElementException e) {
            System.out.println(" No Such Element Exception");
        }
        //System.out.println(n);                                                  // для проверки чтения файла
        Card[] infoListCard = new Card[n];

        i=0;
        try (Scanner listC = new Scanner(l_o_c)) {                               // обработка информации о картах
            while (listC.hasNextLine()) {
                String[] element_of_list = listC.nextLine().split("/");
                String numberCard = element_of_list[0];
                int percentCard = numberInt(element_of_list[1]);
                Card card = new Card(numberCard, percentCard);                  // сохранение данных в массив Карта
                infoListCard[i] = card;
                i++;
                //System.out.println(card.toString());                          // для проверки считывания данных карты
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File of Card Not Found Exception");
        }
        catch (NoSuchElementException e) {
            System.out.println("No Such Element Exception");
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid Data Of Card (data of number not found or an empty line or invalid data format?) ");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Data Of Card (missing separator or data of percent not found?)");
        }


        try {
            Product[] basket = new Product[args.length-3];
            int productCode;
            double quantity, total=0, promo=0;
            Card card = new Card();

            for (int a=2; a< args.length; a++) {                                           // обработка информации о покупке
                String[] element_of_purchase = args[a].split("-");
                if (element_of_purchase[0].equalsIgnoreCase("card")) {           // если первая часть параметра - Сard
                    for (int b=0; b<infoListCard.length; b++) {                             // тогда до конца списка карт
                        if (element_of_purchase[1].equalsIgnoreCase(infoListCard[b].getNumber())) {   //сравнивается номер
                            card = infoListCard[b];                                         // запоминается карта
                        }
                    }
                } else {                                                                    // определяем покупки
                    productCode = numberInt(element_of_purchase[0]);
                    quantity = numberDouble(element_of_purchase[1]);
                    for (j=0; j<infoListProduct.length; j++) {
                        if (productCode == infoListProduct[j].getId()) {                    // сравнивается id товара
                            Product product = new Product.ProductBuilder(infoListProduct[j].getId(),
                                                                         infoListProduct[j].getName(),
                                                                         infoListProduct[j].getPromoB(),
                                                                         infoListProduct[j].getPrice())
                                                                         .setQuantity(quantity)
                                                                         .setTotal()
                                                                         .setPromoD().build();
                            basket[a-2] = product;                                          // складываем в корзину
                            total += product.getTotal();
                            promo += product.getPromoD();
                            //System.out.println(product.toString());                       // для проверки формирования корзины
                        }
                    }
                }
            }
            //System.out.println(card.toString());                                          // для проверки применения карты
            //System.out.println(total + " " + promo);                                      // для проверки расчета сумм
            double discount = Math.round(total*card.getPercent())/100.0;
            double pay = total-discount;
            Cash cash = new Cash(args[1], total, promo, card, discount, pay);
            InfoOfShop();
            System.out.println(cash.toString1());
            for (int c=0; c<basket.length; c++) {
                System.out.println(basket[c].toString());
            }
            System.out.println(cash.toString2());
        }
        catch (NegativeArraySizeException e) {
            System.out.println("missing enter arguments?");
        }
    }
}
