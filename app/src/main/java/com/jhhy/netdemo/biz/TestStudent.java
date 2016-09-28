package com.jhhy.netdemo.biz;

import com.google.gson.Gson;

/**
 * Created by birney1 on 16/9/28.
 */
public class TestStudent {
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


    public String toJsonString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
