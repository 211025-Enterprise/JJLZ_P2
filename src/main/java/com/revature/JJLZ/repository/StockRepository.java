package com.revature.JJLZ.repository;
import com.revature.JJLZ.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stocks, String> {
    Stocks findStockByName(String name);

    List<Stocks> findAllByHolder_UserId(Integer userId);

    //Object findByName(String name);
}
