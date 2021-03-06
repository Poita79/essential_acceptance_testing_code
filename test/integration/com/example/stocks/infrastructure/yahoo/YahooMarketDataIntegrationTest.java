package com.example.stocks.infrastructure.yahoo;

import com.example.stocks.core.FrozenClock;
import com.example.stocks.core.Money;
import com.example.stocks.core.Symbol;
import com.example.stocks.infrastructure.Clock;
import com.example.stocks.util.Date;
import org.junit.Test;

import static com.example.stocks.core.ExampleStocks.Apple;
import static com.example.stocks.infrastructure.http.HttpClientFactory.defaultHttpClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class YahooMarketDataIntegrationTest {

    /*
     * If this test is failing, first check http://www.datatables.org/healthchecker/?q=yahoo to see if the feed is ok.
     */

    @Test
    public void requestsStockPriceFromYahoo() throws Exception {
        Symbol symbol = Apple;
        Clock clock = new FrozenClock(new Date(2013, 3, 1));
        Money expectedPrice = new Money("430.47");

        Yahoo yahoo = new YqlWebService(defaultHttpClient());
        YahooMarketData marketData = new YahooMarketData(yahoo, clock);

        assertThat(marketData.getPrice(symbol), is(expectedPrice));
    }

}
