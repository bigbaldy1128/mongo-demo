package com.wjz.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by wangjinzhao on 2017/11/14.
 */
@Data
@Document(collection = "roles")
public class Role {
    @Id
    private ObjectId id;

    private String name;
    private int level;
}
