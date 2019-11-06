package com.petrochina.e7.monitor.pojo;

import java.util.List;
/**
 * @ProjectName com.petrochina.e7.monitor.pojo
 * @ClassName: SysUser
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/10/23 0023$ 15:39$
 * @Version: 1.0
 */

//用户表（sys_user表）
public class SysUser {

        //主键id
        private Integer id;
        //用户名
        private String username;
        //登录密码
        private String password;

        private List<SysRole> roles;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<SysRole> getRoles() {
            return roles;
        }

        public void setRoles(List<SysRole> roles) {
            this.roles = roles;
        }
    }

