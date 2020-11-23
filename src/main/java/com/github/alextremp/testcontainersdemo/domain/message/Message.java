package com.github.alextremp.testcontainersdemo.domain.message;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Value;

@Value
public class Message {
  UUID id;
  String text;
  LocalDateTime created;
}
