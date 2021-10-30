package com.sd.seerserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetForm {
    String name;

    Integer idRangeLower;

    Integer idRangeUpper;

    String sex;

    String race;

    Integer raceValueLower;

    Integer raceValueUpper;

    String move;

    Integer limit;
}
