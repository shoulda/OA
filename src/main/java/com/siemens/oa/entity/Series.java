package com.siemens.oa.entity;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: 饼状图实体类
 * \* User: xujin
 * \* Date: 2017/12/12
 * \* Time: 14:38
 * \
 */
public class Series {

    /**
     * data : [{"name":"Firefox","y":45},{"name":"IE","y":26.8},{"sliced":true,"name":"Chrome","y":12.8},{"name":"Chrome","y":12.8},{"name":"Safari","y":8.5},{"name":"Opera","y":6.2},{"name":"其他","y":0.7}]
     * name : 工作时间占比
     * type : pie
     */
    private List<DataEntity> data;
    private String name;
    private String type;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public class DataEntity {
        /**
         * name : Firefox
         * y : 45.0
         */
        private String name;
        private double y;

        public void setName(String name) {
            this.name = name;
        }

        public void setY(double y) {
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public double getY() {
            return y;
        }
    }
}