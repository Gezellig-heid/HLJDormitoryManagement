package com.example.demo.component;

import com.example.demo.config.SpringContextHolder;
import com.example.demo.dao.CommentsDao;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * 事件监听器，消息提示
 */

@Component
public class MyApplicationListener {

    @EventListener
    public void listener1(MyApplicationEvent event)
    {
        CommentsDao commentsDao = SpringContextHolder.getBean(CommentsDao.class);
        int type = event.getType();
        int peopleId = event.getPeopleId();
        if(type==3&&peopleId==0){
            //是水费通知
            commentsDao.saveFeeNotification(type);
        }else {
            commentsDao.saveNotification(peopleId, type);
        }
    }

}
