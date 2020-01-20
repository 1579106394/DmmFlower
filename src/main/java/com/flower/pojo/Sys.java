package com.flower.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `sys` (
 *   `sql_id` varchar(36) NOT NULL,
 *   `sql_name` varchar(50) NOT NULL COMMENT '数据库文件名',
 *   `sql_created_time` varchar(50) NOT NULL COMMENT '创建时间',
 *   `sql_staff` varchar(36) NOT NULL COMMENT '备份人',
 *   PRIMARY KEY (`sql_id`),
 *   KEY `sql_staff` (`sql_staff`),
 *   CONSTRAINT `sql_staff` FOREIGN KEY (`sql_staff`) REFERENCES `staff` (`staff_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Sys implements Serializable {

    private String sqlId;
    private String sqlName;
    private String sqlCreatedTime;
    private Staff staff;

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getSqlCreatedTime() {
        return sqlCreatedTime;
    }

    public void setSqlCreatedTime(String sqlCreatedTime) {
        this.sqlCreatedTime = sqlCreatedTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
