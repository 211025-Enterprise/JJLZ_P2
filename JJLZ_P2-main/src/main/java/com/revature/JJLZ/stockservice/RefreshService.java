package com.revature.JJLZ.stockservice;


import com.revature.JJLZ.stockmodel.StockWrapper;
import org.springframework.stereotype.Service;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class RefreshService {


    public RefreshService(){
        stocksToRefresh = new HashMap<>();
        setRefreshEveryMinute();
    }

    private final Map<StockWrapper, Boolean> stocksToRefresh;

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final Duration refreshPeriod = Duration.ofSeconds(60);


    public boolean shouldRefresh(final StockWrapper stock){
        if(!stocksToRefresh.containsKey(stock)){
            stocksToRefresh.put(stock,false);
            return true;
        }
        return stocksToRefresh.get(stock);
    }

    public void setRefreshEveryMinute(){
        scheduler.scheduleAtFixedRate(() ->
                stocksToRefresh.forEach((stock,value) ->{
                    //checks to see if data is stale so we can refresh it
            if(stock.getLastAccessed().isBefore(LocalDateTime.now().minus(refreshPeriod))){
                System.out.println("refreshed price every minute "+stock.getStock().getSymbol());
                stocksToRefresh.remove(stock);
                stocksToRefresh.put(stock.withLastAccessed(LocalDateTime.now()),true);
            }
        }),0,60,SECONDS);

    }

}
