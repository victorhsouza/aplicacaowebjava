package com.senai.projetowebsenai.service;

import com.senai.projetowebsenai.model.UserModel;
import com.senai.projetowebsenai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public UserModel saveUser(UserModel userModel){
        return userRepository.save(userModel);
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }

    public Optional<UserModel> getUserById(Integer id){
        return userRepository.findById(id);
    }
}
