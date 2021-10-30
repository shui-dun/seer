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

    @ApiOperation("精灵的详细信息")
    @GetMapping("/info")
    public Response<?> info(int id) {
        Pet pet = petService.infoById(id);
        if (pet == null) {
            return new Response<>(StatusCodeEnum.PET_NOT_EXIST);
        }
        return new Response<>(StatusCodeEnum.SUCCESS, pet);
    }

    @GetMapping("/randomId")
    public Response<?> randomId() {
        return new Response<>(StatusCodeEnum.SUCCESS, petService.randomId());
    }

    @GetMapping("/baseInfos")
    public Response<?> baseInfos(Integer[] ids) {
        for (int i : ids)
            System.out.println(i);
        return new Response<>(StatusCodeEnum.SUCCESS, petService.baseInfos(ids));
    }

    @GetMapping("/search")
    public Response<?> search(PetForm petForm) {
        return new Response<>(StatusCodeEnum.SUCCESS, petService.search(petForm));
    }
}
