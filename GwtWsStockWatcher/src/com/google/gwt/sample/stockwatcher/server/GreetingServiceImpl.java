package com.google.gwt.sample.stockwatcher.server;

import java.util.ArrayList;
import java.util.Random;

import com.google.gwt.sample.stockwatcher.client.GreetingService;
import com.google.gwt.sample.stockwatcher.client.StockPrice;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

	@Override
	public StockPrice[] refreshWatchList(ArrayList<String> stocks) {
		 final double MAX_PRICE = 100.0; // $100.00
		 final double MAX_PRICE_CHANGE = 0.02; // +/- 2%

		StockPrice[] prices = new StockPrice[stocks.size()];
		 for (int i = 0; i < stocks.size(); i++) {
		 double price = new Random().nextDouble() * MAX_PRICE;
		 double change = price * MAX_PRICE_CHANGE
		 * (new Random().nextDouble() * 2.0 - 1.0);
		
		 prices[i] = new StockPrice(stocks.get(i), price, change);
		 }
		return prices;
	}

}
