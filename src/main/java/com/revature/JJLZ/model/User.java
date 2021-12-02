package com.revature.JJLZ.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yahoofinance.Stock;


import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    public String firstName;

    public String lastName;

    @Column(unique = true)
    public String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private double balance;

    @OneToMany(mappedBy = "holder")
    @JsonManagedReference
    public List<Stocks> holding;

    @OneToMany(mappedBy = "watcher",
                                    cascade = CascadeType.ALL)
    @JsonManagedReference
    public List<StockWatchlist> userWatchList;

}