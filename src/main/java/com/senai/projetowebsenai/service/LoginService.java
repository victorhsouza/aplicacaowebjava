package com.senai.projetowebsenai.service;

import com.senai.projetowebsenai.model.UserModel;
import com.senai.projetowebsenai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public UserModel logar(UserModel userModel){
        return userRepository.login(userModel.getEmail(), userModel.getSenha());
    }
}
