package com.wjz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by wangjinzhao on 2017/11/7.
 */
@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private Long id;
    private String name;
}
