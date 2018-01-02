package com.iapps.adapters;

/**
 * Created by marcelsantoso.
 * <p/>
 * 6/22/16
 */
public abstract class BaseBean implements Cloneable {
    int id;

    public int getId() {
        return id;
    }

    public BaseBean setId(int id) {
        this.id = id;
        return this;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
