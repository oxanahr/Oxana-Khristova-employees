package com.sirma.collaboration.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.sirma.collaboration.utils.DateConverter;

import java.util.Date;
import java.util.Objects;

public class EmployeeDto {

    @CsvBindByName(column = "EmpID")
    private int empId;

    @CsvBindByName(column = "ProjectID")
    private int projectId;

    @CsvCustomBindByName(column = "DateFrom", converter= DateConverter.class)
    private Date from;

    @CsvCustomBindByName(column = "DateTo", converter= DateConverter.class)
    private Date to;

    public EmployeeDto() {
    }

    public EmployeeDto(int empId, int projectId, Date from, Date to) {
        this.setId(empId);
        this.setProjectId(projectId);
        this.setFrom(from);
        this.setTo(to);
    }

    public int getId() {
        return empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    private void setId(int empId) {
        this.empId = empId;
    }

    private void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    private void setFrom(Date from) {
        this.from = from;
    }

    private void setTo(Date to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        EmployeeDto other = (EmployeeDto)o;
        return Objects.equals(empId, other.empId) &&
                Objects.equals(projectId, other.projectId) &&
                Objects.equals(from, other.from) &&
                Objects.equals(to, other.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, projectId, from, to);
    }
}
