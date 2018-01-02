package com.siemens.oa.entity;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: Json所对应实体（前端传至后端的json数据格式）
 * \* User: xujin
 * \* Date: 2017/12/7
 * \* Time: 21:01
 * \
 */
public class JsonListToWork {

    /**
     * weekId : 1511712000000
     * work : [{"projectName":"这是项目一","projectId":1,"tasks":[{"hour":1,"stamp":1511764230000,"taskName":"这是项目一的任务1","taskId":1},{"hour":2,"stamp":1511836200000,"taskName":"这是项目一的任务2","taskId":2}]},{"projectName":"这是项目二","projectId":2,"tasks":[{"hour":5,"stamp":1511940600000,"taskName":"这是项目二的任务，任务Id是3","taskId":3},{"hour":1,"stamp":1512023400000,"taskName":"这是项目二的任务，任务Id是4","taskId":4}]},{"projectName":"这是项目三","projectId":3,"tasks":[{"hour":8,"stamp":1511764230000,"taskName":"这是属于项目三的任务，任务id是5","taskId":5},{"hour":4,"stamp":1511836200000,"taskName":"这是属于项目三的任务，任务id是6","taskId":6}]},{"projectName":"这是项目四","projectId":4,"tasks":[{"hour":7,"stamp":1511940600000,"taskName":"这是属于项目四的任务，任务Id是7","taskId":7},{"hour":3,"stamp":1512023400000,"taskName":"这是属于项目四的任务，任务Id是8","taskId":8}]}]
     */
    private String weekId;
    private List<WorkEntity> work;

    public void setWeekId(String weekId) {
        this.weekId = weekId;
    }

    public void setWork(List<WorkEntity> work) {
        this.work = work;
    }

    public String getWeekId() {
        return weekId;
    }

    public List<WorkEntity> getWork() {
        return work;
    }

    public class WorkEntity {
        /**
         * projectName : 这是项目一
         * projectId : 1
         * tasks : [{"hour":1,"stamp":1511764230000,"taskName":"这是项目一的任务1","taskId":1},{"hour":2,"stamp":1511836200000,"taskName":"这是项目一的任务2","taskId":2}]
         */
        private String projectName;
        private int projectId;
        private List<TasksEntity> tasks;

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public void setTasks(List<TasksEntity> tasks) {
            this.tasks = tasks;
        }

        public String getProjectName() {
            return projectName;
        }

        public int getProjectId() {
            return projectId;
        }

        public List<TasksEntity> getTasks() {
            return tasks;
        }


        public class TasksEntity {
            /**
             * hour : 1
             * stamp : 1511764230000
             * taskName : 这是项目一的任务1
             * taskId : 1
             */
            private int hour;
            private String stamp;
            private String taskName;
            private int taskId;

            public void setHour(int hour) {
                this.hour = hour;
            }

            public void setStamp(String stamp) {
                this.stamp = stamp;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }

            public int getHour() {
                return hour;
            }

            public String getStamp() {
                return stamp;
            }

            public String getTaskName() {
                return taskName;
            }

            public int getTaskId() {
                return taskId;
            }
        }
    }

    @Override
    public String toString() {
        return "JsonListToWork{" +
                "weekId=" + weekId +
                ", work=" + work +
                '}';
    }
}