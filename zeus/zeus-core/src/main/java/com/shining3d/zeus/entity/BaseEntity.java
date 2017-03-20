package com.shining3d.zeus.entity;

import java.util.Date;

public class BaseEntity {

    private Long Id;

    private Date gmtCreate;

    private Date gmtModified;

    private String isDeleted = "n";

    private String creator;

    private String modifier;

    public Long getId() {
        return Id;
    }

    boolean idChange = false;

    public void setId(Long id) {
        idChange = true;
        Id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    boolean gmtCreateChange = false;

    public void setGmtCreate(Date gmtCreate) {
        gmtCreateChange = true;
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    boolean gmtModifiedChange = false;

    public void setGmtModified(Date gmtModified) {
        gmtModifiedChange = true;
        this.gmtModified = gmtModified;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    boolean isDeletedChange = false;

    public void setIsDeleted(String isDeleted) {
        isDeletedChange = true;
        this.isDeleted = isDeleted;
    }

    public String getCreator() {
        return creator;
    }

    boolean creatorChange = false;

    public void setCreator(String creator) {
        creatorChange = true;
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    boolean modifierChange = false;

    public void setModifier(String modifier) {
        modifierChange = true;
        this.modifier = modifier;
    }
}
