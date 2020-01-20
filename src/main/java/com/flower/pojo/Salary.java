package com.flower.pojo;

import java.io.Serializable;

/**
 * CREATE TABLE `salary` (
 *   `salary_id` varchar(36) NOT NULL COMMENT '工资id',
 *   `salary_staff` varchar(36) NOT NULL COMMENT '员工id',
 *   `salary_time` varchar(36) NOT NULL COMMENT '工资创建时间（发工资会按照创建时间来）',
 *   `salary_price` double(6,0) NOT NULL DEFAULT '2000' COMMENT '工资',
 *   `salary_flag` int(2) NOT NULL DEFAULT '1' COMMENT '1正常2删除',
 *   PRIMARY KEY (`salary_id`),
 *   KEY `salary_staff` (`salary_staff`),
 *   CONSTRAINT `salary_staff` FOREIGN KEY (`salary_staff`) REFERENCES `staff` (`staff_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
public class Salary implements Serializable {

    private String salaryId;
    private Staff staff;
    private String salaryTime;
    private Double salaryPrice;
    private Integer salaryFlag;

    public String getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getSalaryTime() {
        return salaryTime;
    }

    public void setSalaryTime(String salaryTime) {
        this.salaryTime = salaryTime;
    }

    public Double getSalaryPrice() {
        return salaryPrice;
    }

    public void setSalaryPrice(Double salaryPrice) {
        this.salaryPrice = salaryPrice;
    }

    public Integer getSalaryFlag() {
        return salaryFlag;
    }

    public void setSalaryFlag(Integer salaryFlag) {
        this.salaryFlag = salaryFlag;
    }
}
