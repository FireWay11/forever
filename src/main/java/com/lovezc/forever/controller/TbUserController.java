package com.lovezc.forever.controller;

import com.lovezc.forever.entity.TbUser;
import com.lovezc.forever.service.TbUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**8
 * 用户表(TbUser)表控制层
 *
 * @author makejava
 * @since 2020-04-16 13:15:10
 */
@RestController
@RequestMapping("tbUser")
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbUser selectOne(String id) {
        return this.tbUserService.queryById(id);
    }

    @PostMapping("addUser")
    public TbUser addUser(TbUser tbUser){
        return tbUserService.insert(tbUser);
    }


}