package com.revature.JJLZ.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    public String firstName;
    public String lastName;
    public String username;
    public String password;

    private double balance;

    @OneToMany(mappedBy = "holder")
    @JsonManagedReference
    public List<Stocks> holding;

    @OneToMany(mappedBy = "watcher",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    public List<StockWatchlist> userWatchList;
}