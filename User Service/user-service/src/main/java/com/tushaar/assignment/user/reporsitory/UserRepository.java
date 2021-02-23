package com.tushaar.assignment.user.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tushaar.assignment.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
