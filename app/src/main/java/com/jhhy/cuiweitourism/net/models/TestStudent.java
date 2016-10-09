package com.jhhy.cuiweitourism.net.models;

import com.jhhy.cuiweitourism.net.models.FetchModel.BasicFetchModel;

/**
 * Created by birney1 on 16/9/28.
 */
public class TestStudent extends BasicFetchModel {
    public  String  name;
    public  String address;
    public  int age;

    public TestStudent(){

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public  TestStudent(String name, String address, int age){
        super();
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {

        this.name = name;
    }



}
