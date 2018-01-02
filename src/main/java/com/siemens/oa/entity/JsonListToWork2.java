package com.siemens.oa.entity;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: Json所对应实体（后端传至前端的json数据格式）
 * \* User: xujin
 * \* Date: 2017/12/7
 * \* Time: 21:01
 * \
 */
public class JsonListToWork2 {

    /**
     * weekId : 1511712000000
     * work : [{"projectName":"oa","projectId":1,"tasks":[{"days":[{"hour":1,"m_STATUS":1,"stamp":"1511712000000"},{"hour":2,"m_STATUS":1,"stamp":"1511798400000"},{"hour":2,"m_STATUS":1,"stamp":"1511798400000"},{"hour":3,"m_STATUS":1,"stamp":"1511884800000"}],"taskName":"modifiy-bug","taskId":1},{"days":[{"hour":2,"m_STATUS":1,"stamp":"1511712000000"},{"hour":3,"m_STATUS":1,"stamp":"1511798400000"},{"hour":4,"m_STATUS":1,"stamp":"1511971200000"}],"taskName":"learn","taskId":3}]}]
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
         * projectName : oa
         * projectId : 1
         * tasks : [{"days":[{"hour":1,"m_STATUS":1,"stamp":"1511712000000"},{"hour":2,"m_STATUS":1,"stamp":"1511798400000"},{"hour":2,"m_STATUS":1,"stamp":"1511798400000"},{"hour":3,"m_STATUS":1,"stamp":"1511884800000"}],"taskName":"modifiy-bug","taskId":1},{"days":[{"hour":2,"m_STATUS":1,"stamp":"1511712000000"},{"hour":3,"m_STATUS":1,"stamp":"1511798400000"},{"hour":4,"m_STATUS":1,"stamp":"1511971200000"}],"taskName":"learn","taskId":3}]
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
             * days : [{"hour":1,"m_STATUS":1,"stamp":"1511712000000"},{"hour":2,"m_STATUS":1,"stamp":"1511798400000"},{"hour":2,"m_STATUS":1,"stamp":"1511798400000"},{"hour":3,"m_STATUS":1,"stamp":"1511884800000"}]
             * taskName : modifiy-bug
             * taskId : 1
             */
            private List<DaysEntity> days;
            private String taskName;
            private int taskId;

            public void setDays(List<DaysEntity> days) {
                this.days = days;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }

            public List<DaysEntity> getDays() {
                return days;
            }

            public String getTaskName() {
                return taskName;
            }

            public int getTaskId() {
                return taskId;
            }

            public class DaysEntity {
                /**
                 * hour : 1
                 * m_STATUS : 1
                 * stamp : 1511712000000
                 */
                private int hour;
                private int m_STATUS;
                private String stamp;

                public void setHour(int hour) {
                    this.hour = hour;
                }

                public void setM_STATUS(int m_STATUS) {
                    this.m_STATUS = m_STATUS;
                }

                public void setStamp(String stamp) {
                    this.stamp = stamp;
                }

                public int getHour() {
                    return hour;
                }

                public int getM_STATUS() {
                    return m_STATUS;
                }

                public String getStamp() {
                    return stamp;
                }
            }
        }
    }
}