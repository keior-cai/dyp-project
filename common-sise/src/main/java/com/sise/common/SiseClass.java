package com.sise.common;

import lombok.Data;

import java.util.List;

@Data
public class SiseClass {
    /**
     * 必修课程
     **/
    private List<RowClass> compulsory;

    /**
     * 选修课程
     **/
    private List<RowClass> elective;

    /**
     * 必修总学分
     **/
    private Double compulsoryTotal;

    /**
     * 已修必修学分
     **/
    private Double compulsoryActual;

    /**
     * 已获必修学分
     **/
    private Double compulsoryReceive;


    @Data
    public class RowClass {
        private String className;

        private String classCode;

        private String credit;

        private String type;

        private String preClass;

        private String Semester;

        private String studySemester;

        private String source;

        private String actualSource;
    }
}
