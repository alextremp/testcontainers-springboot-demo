package com.github.alextremp.testcontainersdemo.infrastructure.messagestoredb.repository.mybatis;

import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper {

  @Insert("insert into message values (#{id}, #{text}, #{created})")
  void insert(@Param("id") String id, @Param("text") String text, @Param("created") LocalDateTime created);

  @Select("select * from message where text=#{text}")
  Optional<MessageResult> selectOneByText(@Param("text") String text);
}
