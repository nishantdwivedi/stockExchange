package com.bacq.stockmarket.start;

import com.bacq.stockmarket.helper.StockMarketHelper;
import com.bacq.stockmarket.service.OrderManagement;
import com.bacq.stockmarket.service.impl.OrderManagementImpl;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

public class StartApp {

    public static void main(String[] args) throws Exception {
        // assuming args1 is having the path
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        // e.g. in resource folder -> args[0] -> ./inputs
        URL url = classLoader.getResource(args[0]);
        if (url == null){
            throw new RuntimeException("Incorrect path provided");
        }
        File file = new File(url.getFile());
        Scanner scanner = new Scanner(new FileReader(file));

        StockMarketHelper stockMarketHelper = new StockMarketHelper();
        OrderManagement orderManagement = new OrderManagementImpl();
        while (true){
            String[] input = scanner.nextLine().split(" ");
            if(input.length == 1){
                break;
            }
            stockMarketHelper.takeAndAddStockToOrderBook(input, orderManagement);
        }
        stockMarketHelper.displayOutput();
    }
}
