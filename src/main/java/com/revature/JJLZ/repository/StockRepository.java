package com.revature.JJLZ.repository;
import com.revature.JJLZ.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stocks, String> {



    //Object findByName(String name);
}
