package com.wjz.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by wangjinzhao on 2017/11/7.
 */
public interface UserRepository extends MongoRepository<User,Long> {
    List<User> findByName(String name);
}
