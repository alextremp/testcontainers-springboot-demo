package com.github.alextremp.testcontainersdemo.infrastructure;

import com.github.alextremp.testcontainersdemo.infrastructure.messagestoredb.repository.mybatis.MessageMapper;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InventBreaker {

  private final MessageMapper messageMapper;

  public InventBreaker(MessageMapper messageMapper) {
    this.messageMapper = messageMapper;
  }

  @PostConstruct
  void tryToBreakTheTestContext() {
    messageMapper.selectOneByText("applicationContext lanzará excepción si no hay conectividad a la BBDD");
  }
}
