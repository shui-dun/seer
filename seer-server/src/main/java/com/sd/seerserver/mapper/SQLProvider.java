package com.sd.seerserver.mapper;

import com.sd.seerserver.entity.PetForm;
import org.apache.ibatis.jdbc.SQL;

public class SQLProvider {
    public String search(PetForm petForm) {
        return new SQL() {
            {
                SELECT("distinct p.*");
                FROM("pet p");
                if (petForm.getMove() != null) {
                    JOIN("move m on p.id = m.pet_id");
                    WHERE("m.name like '%' #{move} '%'");
                }
                if (petForm.getName() != null) {
                    WHERE("(p.name1 like '%' #{name} '%' or p.name2 like '%' #{name} '%' or p.name3 like '%' #{name} '%')");
                }
                if (petForm.getIdRangeLower() != null) {
                    WHERE("p.id >= #{idRangeLower}");
                }
                if (petForm.getIdRangeUpper() != null) {
                    WHERE("p.id <= #{idRangeUpper}");
                }
                if (petForm.getSex() != null && !petForm.getSex().equals("任意")) {
                    WHERE("p.sex = #{sex}");
                }
                if (petForm.getRace() != null && !petForm.getRace().equals("任意")) {
                    WHERE("p.race like '%' #{race} '%'");
                }
                if (petForm.getRaceValueLower() != null) {
                    WHERE("p.sum >= #{raceValueLower}");
                }
                if (petForm.getRaceValueUpper() != null) {
                    WHERE("p.sum <= #{raceValueUpper}");
                }
                if (petForm.getLimit() != null) {
                    LIMIT("#{limit}");
                }
            }
        }.toString();
    }
}
