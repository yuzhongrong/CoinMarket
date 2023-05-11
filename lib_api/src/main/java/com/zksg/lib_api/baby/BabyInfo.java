package com.zksg.lib_api.baby;

public class BabyInfo {
    private String id;
    private String name;
    private String gender;
    private String birthday;
    private String weight;
    private String height;
    private String avatar;

    public BabyInfo(String id, String name, String gender, String birthday, String weight, String height,String avatar) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.avatar=avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
