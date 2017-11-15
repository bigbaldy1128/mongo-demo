package com.wjz.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by wangjinzhao on 2017/11/7.
 */
public interface UserRepository extends MongoRepository<User,ObjectId> {
    List<User> findByName(String name);
}
