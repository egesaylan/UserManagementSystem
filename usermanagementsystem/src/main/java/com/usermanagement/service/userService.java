package com.usermanagement.service;

import com.usermanagement.model.user;
import com.usermanagement.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userService {

    @Autowired private userRepository repo;

    public List<user> listAll(){
        return (List<user>) repo.findAll();
    }

    public void save(user user) {
        repo.save(user);
    }

    public user getUser(Integer id) throws userNotFoundException {
        Optional<user> optionalUser = repo.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new userNotFoundException("could not find any user with ID:" + id);
    }

    public void deleteUser(Integer id) throws userNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count ==0){
            throw new userNotFoundException("Could not find any users with ID" + id);
        }
        repo.deleteById(id);
    }
}
