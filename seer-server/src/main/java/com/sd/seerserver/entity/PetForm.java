package com.sd.seerserver.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("精灵搜索表单")
public class PetForm {
    @ApiModelProperty("精灵名称")
    String name;

    @ApiModelProperty("ID下界")
    Integer idRangeLower;

    @ApiModelProperty("ID上界")
    Integer idRangeUpper;

    @ApiModelProperty("性别")
    String sex;

    @ApiModelProperty("种族")
    String race;

    @ApiModelProperty("种族值下界")
    Integer raceValueLower;

    @ApiModelProperty("种族值上界")
    Integer raceValueUpper;

    @ApiModelProperty("拥有的技能")
    String move;

    @ApiModelProperty("返回搜索结果数目")
    Integer limit;
}
