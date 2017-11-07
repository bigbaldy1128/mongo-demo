package com.wjz.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by wangjinzhao on 2017/11/7.
 */
public interface UserRepository extends MongoRepository<User,Long> {
}
