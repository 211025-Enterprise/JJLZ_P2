package com.revature.JJLZ.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yahoofinance.Stock;


import javax.persistence.*;
import java.util.List;
import java.time.LocalDate;

public class StockHistory {
	private LocalDate date;
	private double open;
	private double high;
	private double low;
	private double close;
	private double adjClose;
	private long volume;

}
