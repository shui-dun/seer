package com.sd.seerserver.controller;

import com.sd.seerserver.entity.Comment;
import com.sd.seerserver.entity.Response;
import com.sd.seerserver.entity.User;
import com.sd.seerserver.enumeration.StatusCodeEnum;
import com.sd.seerserver.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation("列出所有的评论")
    @RequiresPermissions("comment:list")
    @GetMapping("/list")
    public Response<Set<Comment>> list() {
        return new Response<>(StatusCodeEnum.SUCCESS, commentService.listAll());
    }

    @ApiOperation("通过ID删除评论")
    @RequiresPermissions("comment:delete")
    @GetMapping("/delete/{id}")
    public Response<?> delete(@PathVariable long id) {
        if (commentService.deleteById(id) == 1) {
            return new Response<>(StatusCodeEnum.SUCCESS);
        } else {
            return new Response<>(StatusCodeEnum.COMMENT_NOT_EXIST);
        }
    }

    @ApiOperation("列出我的评论")
    @RequiresAuthentication
    @GetMapping("/mine")
    public Response<List<Comment>> mine(int limit, int offset) {
        String name = ((User) SecurityUtils.getSubject().getPrincipal()).getName();
        return new Response<>(StatusCodeEnum.SUCCESS, commentService.listMine(name, limit, offset));
    }

    @ApiOperation("列出关于某精灵的评论")
    @GetMapping("/pet")
    public Response<List<Comment>> pet(int petId, int limit, int offset) {
        return new Response<>(StatusCodeEnum.SUCCESS, commentService.listByPet(petId, limit, offset));
    }

    @ApiOperation("添加一条评论")
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

    @ApiOperation("根据ID删除我的某条评论")
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
