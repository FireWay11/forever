package com.lovezc.forever.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表(TbUser)实体类
 *
 * @author makejava
 * @since 2020-04-16 13:14:57
 */
@Data
public class TbUser implements Serializable {
    private static final long serialVersionUID = 313541013928430683L;
    
    private String id;
    
    private String name;
    
    private String username;
    
    private String password;

    public TbUser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TbUser() {
    }

    public static void main(String[] args) {
        TbUser bean1 = new TbUser("1", "2");
        TbUser bean2 = new TbUser("3", "4");
        List<TbUser> list = new ArrayList<>();
        list.add(bean1);
        list.add(bean2);
        System.out.println("list1 : " + list);
        list.stream().filter(bean -> {
            if ("1" == bean.getId()) {
                bean.setId("2");
            }
            return true;
        }).collect(Collectors.toList());
        System.out.println("list2 : " + list);
    }

}