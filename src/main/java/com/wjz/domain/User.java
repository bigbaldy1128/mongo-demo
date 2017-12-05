package com.wjz.domain;

import com.mysema.query.annotations.QueryEntity;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by wangjinzhao on 2017/11/7.
 */
@QueryEntity
@Data
@Document(collection = "users")
public class  User{
    @Id
    private ObjectId id;
    private String name;

    @DBRef(lazy = true)
    private List<Role> roles;
}
