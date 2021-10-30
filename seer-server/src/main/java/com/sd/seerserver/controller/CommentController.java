package com.sd.seerserver.controller;

import com.sd.seerserver.entity.Response;
import com.sd.seerserver.entity.User;
import com.sd.seerserver.enumeration.StatusCodeEnum;
import com.sd.seerserver.service.CommentService;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequiresPermissions("comment:list")
    @GetMapping("/list")
    public Response<?> list() {
        return new Response<>(StatusCodeEnum.SUCCESS, commentService.listAll());
    }

    @RequiresPermissions("comment:delete")
    @GetMapping("/delete/{id}")
    public Response<?> delete(@PathVariable long id) {
        if (commentService.deleteById(id) == 1) {
            return new Response<>(StatusCodeEnum.SUCCESS);
        } else {
            return new Response<>(StatusCodeEnum.COMMENT_NOT_EXIST);
        }
    }

    @RequiresAuthentication
    @PostMapping("/mine")
    public Response<?> mine(int limit, @ApiParam("返回结果中最小的可能id") int minId) {
        String name = ((User) SecurityUtils.getSubject().getPrincipal()).getName();
        return new Response<>(StatusCodeEnum.SUCCESS, commentService.listMine(name, limit, minId));
    }

    @GetMapping("/pet")
    public Response<?> pet(int petId, int limit, int minId) {
        return new Response<>(StatusCodeEnum.SUCCESS, commentService.listByPet(petId, limit, minId));
    }

    @RequiresAuthentication
    @PostMapping("/addMine")
    public Response<?> addMine(int petId, String text) {
        String name = ((User) SecurityUtils.getSubject().getPrincipal()).getName();
        if (text.length() > 100) {
            return new Response<>(StatusCodeEnum.COMMENT_TOO_LONG);
        }
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            commentService.addMine(name, petId, text, timestamp);
            return new Response<>(StatusCodeEnum.SUCCESS);
        } catch (DataIntegrityViolationException e) {
            return new Response<>(StatusCodeEnum.PET_NOT_EXIST);
        }
    }

    @RequiresAuthentication
    @PostMapping("/deleteMine")
    public Response<?> deleteMine(long id) {
        String name = ((User) SecurityUtils.getSubject().getPrincipal()).getName();
        try {
            if (commentService.deleteMineById(name, id) == 1) {
                return new Response<>(StatusCodeEnum.SUCCESS);
            } else {
                return new Response<>(StatusCodeEnum.COMMENT_NOT_EXIST);
            }
        } catch (UnauthorizedException e) {
            return new Response<>(StatusCodeEnum.UNAUTHORIZED);
        }
    }
}
