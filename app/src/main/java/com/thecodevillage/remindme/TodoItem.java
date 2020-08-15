package com.thecodevillage.remindme;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "todo_items")
public class TodoItem {


    @Id(autoincrement = true)
    private Long id;


    private String name;

    private boolean status;

    @Generated(hash = 1019285688)
    public TodoItem(Long id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Generated(hash = 1307818545)
    public TodoItem() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
