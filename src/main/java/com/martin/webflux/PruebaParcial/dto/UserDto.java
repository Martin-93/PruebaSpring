package com.martin.webflux.PruebaParcial.dto;

import com.martin.webflux.PruebaParcial.documents.User;

public class UserDto {
    private Long id;

    private String name, type, user;

    private Boolean active;

    private int[] data;

    public UserDto() {
    }

    public UserDto(Long id, String name, String type, String user, Boolean active, int[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.user = user;
        this.active = active;
        this.data = data;
    }

    public UserDto(User user){
        this(user.getId(), user.getName(), user.getUser(), user.getType(), user.getActive(), user.getData());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
