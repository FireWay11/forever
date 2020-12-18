package com.lovezc.forever.entity;

import lombok.Data;

import java.io.Serializable;

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

}