package com.wjz.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by wangjinzhao on 2017/11/14.
 */
public interface RoleRepository extends MongoRepository<Role,ObjectId> {

}
