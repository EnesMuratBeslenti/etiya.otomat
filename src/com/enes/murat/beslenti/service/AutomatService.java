package com.enes.murat.beslenti.service;

import com.enes.murat.beslenti.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutomatService {
    private List<Product> productList = new ArrayList<>();
    private Double balance = 0.0;
    private final Scanner scanner = new Scanner(System.in);

/*
*Kullanıcıya menüyü gösterip seçim yaptırıyor.
* Seçim de işleniyor.
 */
    public void Start() {
        int selection = showMenu();
        processSelection(selection);
    }
/*
*Menü gösteriliyor.
* Menü içerisinde kullanıcıdan bir input alınıyor.
* Alınan bu String değerin parseInt ile Integer değer olarak dönüştürüldüğüne bakılıyor.
* Bu kontrol edilirken bu değerin seçenek sayısından büyük olmaması gerekiyor.
* +2 eklenmesinin sebebi ise ürün sayısına ek olarak Bakiye Ekle ve Çıkış seçeneklerine eklenmesidir.
* Negatif değer girilmemesi için girilen değerin 0'dan büyük olduğu kontrol edilmektedir.
 */
    public int showMenu() {
        printMenu();

        String inputString = scanner.next();
        if (isNumeric(inputString) && Integer.parseInt(inputString) <= productList.size() + 2 && Integer.parseInt(inputString) >=0) {
            return Integer.parseInt(inputString);
        } else
            System.out.println("Lütfen geçerli bir giriş yapınız");

        return showMenu();
    }
/*
*Menü'de değerler double olarak alındığı için virgülden sonra 1 basamak şeklinde yazılıyor.
*Menü uzunluğu listenin Size'na bağlı olarak for ile arttılmaktadır.
 */
    private void printMenu() {
        System.out.println("\nİçecek Otomatına Hoşgeldiniz");

        for (int i = 0; i < productList.size(); i++) {
            System.out.format("(%d) %s %.1f TL\n", i + 1, productList.get(i).getName(), productList.get(i).getPrice());
        }

        System.out.format("(%d) Bakiye Ekle \n", productList.size() + 1);
        System.out.println("(0) Çıkış");
        System.out.println("\n\t\t\t\t\t Bakiye:" + balance + " TL");
    }

/*
* Bu metod da addBalance menüsü gösterilmektedir.İf içeresinde girilen değerlerin numeric ve int olarak alındığına bakılmaktadır.
* Aynı zaman da da 0'dan büyük olması da gerekmektedir.
 */
    public int showAddBalanceMenu() {
        printAddBalanceMenu();

        String inputString = scanner.next();
        if (isNumeric(inputString) && Integer.parseInt(inputString) <= 4 && Integer.parseInt(inputString) >=0) {
            return Integer.parseInt(inputString);
        } else
            System.out.println("Lütfen geçerli bir giriş yapınız");

        return showAddBalanceMenu();
    }

    private void printAddBalanceMenu() {
        System.out.println("\nLütfen Eklemek İstediğiniz Miktarı Seçiniz");
        System.out.println("(1) 50 Kuruş");
        System.out.println("(2) 1 TL");
        System.out.println("(3) 5 TL");
        System.out.println("(4) 10 TL");
        System.out.println("(0) Çıkış");
        System.out.println("\n\t\t\t\t\t Bakiye:" + balance + " TL");
    }
/*
*Menü'den kullanıcı 0 değeri girdiğinde para üstü verilerek çıkış yapılır.
*Else if ürün alımı gerçekleşiyor
* Else durumunda bakiye menüsüne yönlendirilip bakiye ekleme işlemlerine yönlendiriliyor.
 */
    public void processSelection(int selection) {
        int selectedAmount;

        if (selection == 0) {
            System.out.println("Para üstü verildi: " + balance + " TL");
        } else if (selection <= productList.size()) {
            buy(selection - 1);
        } else{
            selectedAmount = showAddBalanceMenu();
            addBalance(selectedAmount);
        }
    }
/*
*Bakiye eklenme durumu kontolleri burda sağlanır.
* Eklenmek isteyen bakiye switch case kontrolleri içerisinde eklenir.
*Menü gösterilip seçim yaptırılır.
 */
    public void addBalance(int selectedAmount) {
        double amount = 0;

        switch (selectedAmount) {
            case 1:
                amount = 0.5;
                break;
            case 2:
                amount = 1.0;
                break;
            case 3:
                amount = 5.0;
                break;
            case 4:
                amount = 10.0;
                break;
            default:
                break;
        }

        balance += amount;
        int selection = showMenu();
        processSelection(selection);
    }
/*
*Alım gerçekleştiğin de ürün fiyatı bakiyeden düşülmektedir.
* Eğer bakiye ürün fiyatından düşük ise alım gerçekleşmemektedir.
 */
    public void buy(int selectedIndex) {
        Product selectedProduct = productList.get(selectedIndex);

        if (balance >= selectedProduct.getPrice()) {
            balance -= selectedProduct.getPrice();
            System.out.println(selectedProduct.getName() + " Verildi");
        } else {
            System.out.println("Yetersiz Bakiye");
        }

        Start();
    }
/*
*Ürün eklenmesi yapılmaktadır.
 */

    public void addProducts() {
        Product cola = new Product("Kola", 15.0);
        Product fanta = new Product("Fanta", 20.0);
        Product gazoz = new Product("Gazoz", 30.0);


        productList.add(cola);
        productList.add(fanta);
        productList.add(gazoz);
    }
/*
*Girilen değerin Integer olarak kontrolleri sağlanmaktadır.
 */
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
