package com.sd.seerserver.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("评论")
public class Comment {

  private Long id;

  @ApiModelProperty("评论编写者")
  private String user;

  @ApiModelProperty("对哪个精灵ID做出的评论")
  private Long pet;

  @ApiModelProperty("发表评论时的时间戳")
  private java.sql.Timestamp time;

  @ApiModelProperty("评论内容")
  private String content;

}
