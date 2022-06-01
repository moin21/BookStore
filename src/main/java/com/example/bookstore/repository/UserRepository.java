package com.example.bookstore.repository;

import com.example.bookstore.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
    @Query(value = "select * from user where login_Id= :loginID", nativeQuery = true)
    Optional<UserData> findByLoginId(String loginID);

}

