package com.senai.projetowebsenai.repository;

import com.senai.projetowebsenai.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    @Query(value = "select * from users where email = :email and senha = :senha",nativeQuery = true)
    public UserModel login(String email, String senha);
}
