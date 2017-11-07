package com.wjz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by wangjinzhao on 2017/11/7.
 */
@Data
@AllArgsConstructor
public class User {
    @Id
    private Long id;

    private String name;
}
