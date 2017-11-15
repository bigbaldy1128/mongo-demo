package com.wjz;

import com.wjz.domain.Role;
import com.wjz.domain.RoleRepository;
import com.wjz.domain.User;
import com.wjz.domain.UserRepository;
import org.bson.types.ObjectId;
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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void performanceTest(){
        long start= System.currentTimeMillis();
        List<User> users=new ArrayList<>();
        for (long i = 1; i <= 100; i++) {
            User user = new User();
            user.setName("王五" + i);
            users.add(user);
        }
        userRepository.save(users);
        long end= System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Test
    public void test10() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("abc");
        Role role1 = new Role();
        role1.setName("角色1");
        role1.setLevel(1);
        Role role2 = new Role();
        role2.setName("角色2");
        role2.setLevel(2);
        List<Role> roles = Arrays.asList(role1, role2);
        roleRepository.insert(roles); //要先插入,因为内嵌的类型不会自动生成id
        user.setRoles(roles);
        users.add(user);
        userRepository.insert(users);
    }

    @Test
    public void test11() {
        List<User> users= userRepository.findAll();
    }

    @Test
    public void test20() {
        List<User> users = mongoTemplate.find(new Query(Criteria.where("name").is("name55")), User.class);
        users.forEach(System.out::println);
    }

    @Test
    public void test21() {
        List<User> users = userRepository.findByName("name55");
        users.forEach(System.out::println);
    }

}
