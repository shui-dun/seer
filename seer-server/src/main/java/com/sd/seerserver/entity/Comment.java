package com.sd.seerserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  private Long id;
  private String user;
  private Long pet;
  private java.sql.Timestamp time;
  private String content;

}
