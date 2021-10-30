package com.sd.seerserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Move {

    private Long id;
    private Long petId;
    private String name;
    private String type;
    private Long power;
    private Long times;
    private Long grade;
    private String info;

}
