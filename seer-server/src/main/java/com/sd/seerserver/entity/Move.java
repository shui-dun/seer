package com.sd.seerserver.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@ApiModel("精灵技能")
public class Move {

    private Long id;

    @ApiModelProperty("该技能属于哪个精灵（事实上，由于一个技能可以属于多个精灵，按照3NF，应该拆分为两张表，但出于性能考虑没有拆分）")
    private Long petId;

    @ApiModelProperty("技能名称")
    private String name;

    @ApiModelProperty("技能类型：物理/属性/...")
    private String type;

    @ApiModelProperty("威力")
    private Long power;

    @ApiModelProperty("最大使用次数")
    private Long times;

    @ApiModelProperty("获得该技能时的等级")
    private Long grade;

    @ApiModelProperty("该技能的介绍信息")
    private String info;

}
