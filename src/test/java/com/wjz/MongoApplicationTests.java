package com.wjz;

import com.wjz.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<User> users = userRepository.findByName("王五5");
        users.forEach(System.out::println);
    }

    @Test
    public void test22(){
        List<User> users = userRepository.customFindByName("王五5");
        users.forEach(System.out::println);
    }

    @Test
    public void test23(){
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("pkTask").is(7)),
                Aggregation.group("rule_name").count().as("total"),
                Aggregation.project("total").and("rule_name").previousOperation());
        List<GroupVO> groupVOS= mongoTemplate.aggregate(agg, "bug1", GroupVO.class).getMappedResults();
    }

}
