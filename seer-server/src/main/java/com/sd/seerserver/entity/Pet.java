package com.sd.seerserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    private Long id;
    private Long nType;
    private String name1;
    private String name2;
    private String name3;
    private String sex;
    private String race;
    private Long tiLi;
    private Long gongJi;
    private Long fangYu;
    private Long teGong;
    private Long teFang;
    private Long suDu;
    private Long sum;
    private String info1;
    private String info2;
    private String info3;

    private List<Move> moves;
}
