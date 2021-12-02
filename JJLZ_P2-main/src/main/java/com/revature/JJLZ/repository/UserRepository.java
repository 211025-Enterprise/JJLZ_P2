package com.revature.JJLZ.repository;

import com.revature.JJLZ.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
