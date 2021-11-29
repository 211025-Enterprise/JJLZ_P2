package com.revature.JJLZ.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    private int userId;

    public String firstName;

    public String lastName;
    @Column(unique = true)
    public String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private double balance;


}
