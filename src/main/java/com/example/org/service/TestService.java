package com.example.org.service;

import com.example.org.bean.Exam;
import com.example.org.bean.Test;
import com.example.org.dao.TestDAO;
import com.example.org.util.DateUtil;

import java.util.Date;
import java.util.List;

public class TestService {
    TestDAO dao = new TestDAO();
    DateUtil dateUtil = new DateUtil();
    public boolean addTest(Test test) {
        return dao.addTest(test);
    }
    public List<Test> getAllTests() {
        return dao.getAllTests();
    }
    public Test getTestById(int id) {
        return dao.getTestById(id);
    }
    public boolean updateTest(Test test){return dao.updateTest(test);}
    public boolean deleteTest(Test test){return dao.deleteTest(test);}

    public String uploadTest(int exam_id,
                             String answer_key,
                             String negative_marking,
                             String name,
                             String date){

        Test test = Test.factory();
        test.setNegative_marking(Float.parseFloat(negative_marking));
        test.setAnswerKey(answer_key);
        Date test_date = dateUtil.parse(date);
        if(test_date == null) {
            return "Error Creating Test!";
        }
        test.setDate(date);
        test.setName(name);
        ExamService examService = new ExamService();
        Exam exam = examService.getExamId(exam_id);
        test.setExam(exam);

        boolean result = addTest(test);
        String n = new String("New Test Created!");
        if(!result)
            n = new String("Error Creating Test!");
        return n;
    }

    public List<Test> getTests(){
        List<Test> tests = getAllTests();
        return tests;
    }

    public String deleteTest(int id){
        Test test = getTestById(id);
        boolean result = deleteTest(test);
        String n = new String("Test deleted!");
        if(!result)
            n = new String("Error deleting test!");
        return n;
    }
}
