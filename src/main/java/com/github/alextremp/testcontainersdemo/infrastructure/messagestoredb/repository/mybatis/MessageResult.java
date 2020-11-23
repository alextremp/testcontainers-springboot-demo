package com.github.alextremp.testcontainersdemo.infrastructure.messagestoredb.repository.mybatis;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MessageResult {
  String id;
  String text;
  LocalDateTime created;
}
