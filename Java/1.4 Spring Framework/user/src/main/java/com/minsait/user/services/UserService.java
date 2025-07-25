package com.minsait.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.minsait.user.dtos.UserDTO;
import com.minsait.user.models.UserModel;
import com.minsait.user.repositories.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<UserModel> save(UserDTO userDTO){
        if(userRepository.existsByEmail(userDTO.getEmail())){
            return Optional.empty(); //verificar
        }

        UserModel user = new UserModel(userDTO);
        return Optional.of(userRepository.save(user));
    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(Long id){
        return userRepository.findById(id);
    }

    public UserModel update(UserDTO userDTO, Long id){
        UserModel newUserData = new UserModel(userDTO);
        newUserData.setId(id);
        return userRepository.save(newUserData);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
    
}
