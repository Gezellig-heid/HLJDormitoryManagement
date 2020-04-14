package com.example.demo.dao;

import com.example.demo.entity.BoardMsg;
import com.example.demo.mapper.BoardMsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class BoardMsgDao {

    @Autowired
    BoardMsgMapper boardMsgMapper;

    public List<Map<String, Object>> getAllMsgForPerson(int floorNumber,Date before30days) {
        List<Map<String, Object>> allMsgForPerson = boardMsgMapper.getAllMsgForPerson(floorNumber,before30days);
        return allMsgForPerson;
    }

    public void saveBoardMsg(BoardMsg boardMsg) {
        boardMsgMapper.saveBoardMsg(boardMsg);
    }

    public void delete(int id) {
        boardMsgMapper.delete(id);
    }
}
