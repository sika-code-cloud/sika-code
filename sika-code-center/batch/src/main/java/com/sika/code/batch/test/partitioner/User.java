package com.sika.code.batch.test.partitioner;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {
    @Id
    int id;
    String username;
    String password;
    int age;
}
