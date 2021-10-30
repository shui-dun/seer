package com.sd.seerserver.controller;

import com.sd.seerserver.entity.Pet;
import com.sd.seerserver.entity.PetForm;
import com.sd.seerserver.entity.Response;
import com.sd.seerserver.entity.RolePerm;
import com.sd.seerserver.enumeration.StatusCodeEnum;
import com.sd.seerserver.service.PetService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @ApiOperation("根据精灵ID查询精灵的详细信息")
    @GetMapping("/info")
    public Response<Pet> info(int id) {
        Pet pet = petService.infoById(id);
        if (pet == null) {
            return new Response<>(StatusCodeEnum.PET_NOT_EXIST);
        }
        return new Response<>(StatusCodeEnum.SUCCESS, pet);
    }

    @ApiOperation("随机返回一个精灵ID")
    @GetMapping("/randomId")
    public Response<Integer> randomId() {
        return new Response<>(StatusCodeEnum.SUCCESS, petService.randomId());
    }

    @ApiOperation("根据提供的精灵ID列表返回这些精灵的基本信息")
    @GetMapping("/baseInfos")
    public Response<List<Pet>> baseInfos(Integer[] ids) {
        return new Response<>(StatusCodeEnum.SUCCESS, petService.baseInfos(ids));
    }

    @ApiOperation("根据搜索条件搜索精灵的基本信息")
    @GetMapping("/search")
    public Response<List<Pet>> search(PetForm petForm) {
        return new Response<>(StatusCodeEnum.SUCCESS, petService.search(petForm));
    }
}
