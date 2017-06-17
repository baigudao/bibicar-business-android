package com.bibicar.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jackie on 2017/6/15 16:25.
 * QQ : 971060378
 * Used as : xxx
 */
@Entity
public class User {

    private String name;

    @Generated(hash = 422536127)
    public User(String name) {
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
