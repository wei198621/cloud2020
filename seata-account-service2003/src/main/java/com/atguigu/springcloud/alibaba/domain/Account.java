package com.atguigu.springcloud.alibaba.domain;

/**
 * @author leowei
 * @date 2020/11/11  - 1:11
 */
public class Account {


    private Long id;
    private Long userId;
    private Integer total;
    private Integer used;
    private Integer residue;

    public Account() {
    }

    public Account(Long id, Long userId, Integer total, Integer used, Integer residue) {
        this.id = id;
        this.userId = userId;
        this.total = total;
        this.used = used;
        this.residue = residue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }
}
