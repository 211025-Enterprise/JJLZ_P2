package com.revature.JJLZ.repository;

import com.revature.JJLZ.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByUsername(String username);
	Boolean existsByUsername(String username);
}
