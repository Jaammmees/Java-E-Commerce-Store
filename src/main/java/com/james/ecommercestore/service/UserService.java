package com.james.ecommercestore.service;

import com.james.ecommercestore.model.User;
import com.james.ecommercestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public User save(User userDetails){
        return userRepository.save(userDetails);
    }

    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }

}
