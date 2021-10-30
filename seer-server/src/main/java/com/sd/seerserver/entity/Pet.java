package com.sd.seerserver.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("精灵")
public class Pet {

    private Long id;

    @ApiModelProperty("形态数目")
    private Long nType;

    @ApiModelProperty("初级形态名称")
    private String name1;

    @ApiModelProperty("中级形态名称")
    private String name2;

    @ApiModelProperty("高级形态名称")
    private String name3;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("种族，例如草系")
    private String race;

    @ApiModelProperty("体力值")
    private Long tiLi;

    @ApiModelProperty("攻击值")
    private Long gongJi;

    @ApiModelProperty("防御值")
    private Long fangYu;

    @ApiModelProperty("特攻值")
    private Long teGong;

    @ApiModelProperty("特防值")
    private Long teFang;

    @ApiModelProperty("速度值")
    private Long suDu;

    @ApiModelProperty("种族值总和")
    private Long sum;

    @ApiModelProperty("初级形态介绍")
    private String info1;

    @ApiModelProperty("中级形态介绍")
    private String info2;

    @ApiModelProperty("高级形态介绍")
    private String info3;

    @ApiModelProperty("技能列表")
    private List<Move> moves;
}
