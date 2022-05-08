package com.sirma.collaboration.models;


import java.util.Objects;

public class EmployeesPairDto {

    private int firstEmployeeId;

    private  int secondEmployeeId;

    public EmployeesPairDto(int firstEmployeeId, int secondEmployeeId) {
        this.setFirstEmployeeId(firstEmployeeId);
        this.setSecondEmployeeId(secondEmployeeId);
    }

    public EmployeesPairDto() {
    }

    public int getFirstEmployeeId() {
        return firstEmployeeId;
    }

    private void setFirstEmployeeId(int firstEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
    }

    public int getSecondEmployeeId() {
        return secondEmployeeId;
    }

    private void setSecondEmployeeId(int secondEmployeeId) {
        this.secondEmployeeId = secondEmployeeId;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        EmployeesPairDto other = (EmployeesPairDto)o;
        return Objects.equals(firstEmployeeId, other.firstEmployeeId) &&
                Objects.equals(secondEmployeeId, other.secondEmployeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstEmployeeId, secondEmployeeId);
    }
}
