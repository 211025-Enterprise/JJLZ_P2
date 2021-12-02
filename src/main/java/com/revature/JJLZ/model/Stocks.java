package com.revature.JJLZ.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "stocks")
public class Stocks {
    @Id
    public String name;

//    public int quantity;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    public User holder;
}
