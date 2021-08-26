package com.enes.murat.beslenti;

import com.enes.murat.beslenti.service.AutomatService;

public class Main {

    public static void main(String[] args) {
        /*
        * Otomata ürünler eklenip , otomat başlatılıyor.
        */
        AutomatService automatService = new AutomatService();
        automatService.addProducts();
        automatService.Start();
    }
}
