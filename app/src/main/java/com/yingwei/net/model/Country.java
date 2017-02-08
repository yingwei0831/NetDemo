package com.yingwei.net.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by jiahe008 on 2017/2/8.
 * 如果一个javabean继承了RealmObject，那么它就可以用来存储Realm，
 * 如果你想知道什么是javabean，来看看这里的定义：javabean是可序列化的，有一个默认的构造方法，成员变量都提供getter和setter方法。
 * 例如，下面的代码可以轻松的保存到Realm中，
 */
public class Country extends RealmObject{
    /**
     * 可以使用@PrimaryKey注解来表示这个一个成员变量是Realm的主键。例如下面的代码是将code字段作为主键，
     */
    @PrimaryKey
    private String code;
    @Required
    private String name;
    private int population;
//    @Ignore
//    private String area;

    public Country() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public String getArea() {
//        return area;
//    }
//
//    public void setArea(String area) {
//        this.area = area;
//    }
}
