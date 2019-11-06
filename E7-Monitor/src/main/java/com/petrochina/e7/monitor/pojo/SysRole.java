package com.petrochina.e7.monitor.pojo;

/**
 * @ProjectName com.petrochina.e7.monitor.pojo
 * @ClassName: SysRole
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/10/23 0023$ 15:40$
 * @Version: 1.0
 */

//角色表（e7_mnt_role表）
public class SysRole {
    //主键id
    private Integer id;
    //角色名称
    private String name;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
