package com.sd.seerserver.mapper;


import com.sd.seerserver.entity.Pet;
import com.sd.seerserver.entity.PetForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface PetMapper {
    @Select("select * from pet")
    List<Pet> getAll();

    @Select("select * from pet where id=#{id}")
    Pet findById(int id);

    @Select("SELECT t1.id " +
            "FROM pet AS t1 " +
            "         JOIN (SELECT ROUND(RAND() * ((SELECT MAX(id) FROM pet) - (SELECT MIN(id) FROM pet)) + " +
            "                            (SELECT MIN(id) FROM pet)) AS id) AS t2 " +
            "WHERE t1.id >= t2.id " +
            "ORDER BY t1.id LIMIT 1; ")
    int randomId();

    @Select("<script>" +
            "select * from pet where id in" +
            "<foreach item='ids' collection='array' open='(' separator=',' close=')'>" +
            "#{ids}" +
            "</foreach>" +
            "order by field " +
            "<foreach item='ids' collection='array' open='(id,' separator=',' close=')'>" +
            "#{ids}" +
            "</foreach>" +
            "</script>")
    List<Pet> findByIds(Integer[] ids);

    @SelectProvider(method = "search", type = SQLProvider.class)
    List<Pet> search(PetForm petForm);
}
