package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface BoardMsgService {



    List<Map<String, Object>> getAllMsgForPerson(int floorNumber, int peopleId);

    Map<Integer, Integer> getAllStudentDorNumber(int floorNumber);

    void saveBoardMsg(int id, String title, String message);

    void delete(int id);

    Map<Integer, String> getAllPersonId(int floorNumber);
}
