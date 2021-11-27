package com.portfolio.coinportfolio.repo;

import com.portfolio.coinportfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByUserName(String userName);
}
