package com.ssm.rest.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable{
    private Long id;

    private String role;

    private String description;

    private List<Long> resourceIds;

    private Boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getResourceIds() {
    	if(resourceIds == null) {
            resourceIds = new ArrayList<Long>();
        }
        return resourceIds;
    }

    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}