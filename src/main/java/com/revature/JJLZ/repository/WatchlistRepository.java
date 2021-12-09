package com.revature.JJLZ.repository;

import com.revature.JJLZ.model.StockWatchlist;
import com.revature.JJLZ.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<StockWatchlist, String> {



    StockWatchlist findStockWatchlistByStockName(String name);

    List<StockWatchlist> findAllByWatcher_UserId(Integer userId);


}
