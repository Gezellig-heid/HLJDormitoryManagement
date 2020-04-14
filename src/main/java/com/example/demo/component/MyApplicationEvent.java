package com.example.demo.component;

import com.example.demo.dao.CommentsDao;
import org.springframework.context.ApplicationEvent;

import javax.annotation.Resource;


/**
 * 消息提示事件
 */
public class MyApplicationEvent extends ApplicationEvent {

    private int peopleId;   //用户ID
    private int type;       //消息类型


    public MyApplicationEvent(Object source, int peopleId, int type) {
        super(source);
        this.peopleId = peopleId;
        this.type = type;
    }

    public MyApplicationEvent(Object source) {
        super(source);
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

}
