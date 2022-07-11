package com.usermanagement;

import com.usermanagement.model.user;
import com.usermanagement.repository.userRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class userRepositoryTest {
    @Autowired private userRepository repo;

    @Test
    public void testAddNew(){
        user user = new user();
        user.setEmail("egesaylan99@gmail.com");
        user.setPassword("12345");
        user.setFirstName("Ege");
        user.setLastName("Şaylan2");

        user savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();

        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<user> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(user user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer Id = 1;
        Optional<user> optionalUser = repo.findById(Id);
        user user = optionalUser.get();
        user.setPassword("hello123");
        repo.save(user);

        user updatedUser = repo.findById(Id).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello123");
    }

    @Test
    public void testGet(){
        Integer ID = 1;
        Optional<user> optionalUser = repo.findById(ID);
        Assertions.assertThat(optionalUser).isPresent();
    }

    @Test
    public void deleteById(){
        Integer ID = 5;
        repo.deleteById(ID);

        Optional<user> optionalUser = repo.findById(ID);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
