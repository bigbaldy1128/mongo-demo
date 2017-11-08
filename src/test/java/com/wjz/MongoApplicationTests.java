package com.wjz;

import com.wjz.domain.User;
import com.wjz.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        long start = System.currentTimeMillis();
        List<User> users=new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            users.add(new User((long) i, "name" + i));
        }
        userRepository.insert(users);
        long end = System.currentTimeMillis();
        System.out.println("elapse time:" + (end - start));
    }

    @Test
    public void test1(){
        List<User> users= mongoTemplate.find(new Query(Criteria.where("name").is("name55")),User.class);
        users.forEach(System.out::println);
    }

    @Test
    public void test2(){
        List<User> users= userRepository.findByName("name55");
        users.forEach(System.out::println);
    }

}
