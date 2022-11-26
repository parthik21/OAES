package com.example.org.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exam_id;

    @Column(unique = true)
    @NotNull
    private String exam_name;

    @Column
    @NotNull
    private String exam_start_date;

    @Column
    @NotNull
    private String exam_end_date;

    private Exam(int exam_id, String exam_name, String exam_start_date, String exam_end_date) {
        this.exam_id = exam_id;
        this.exam_name = exam_name;
        this.exam_start_date = exam_start_date;
        this.exam_end_date = exam_end_date;
    }

    private Exam() {

    }

    public static Exam factory(int exam_id, String exam_name, String exam_start_date, String exam_end_date) {
        return new Exam(exam_id, exam_name, exam_start_date, exam_end_date);
    }

    public static Exam factory() {
        return new Exam();
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getExam_start_date() {
        return exam_start_date;
    }

    public void setExam_start_date(String exam_start_date) {
        this.exam_start_date = exam_start_date;
    }

    public String getExam_end_date() {
        return exam_end_date;
    }

    public void setExam_end_date(String  exam_end_date) {
        this.exam_end_date = exam_end_date;
    }
}
