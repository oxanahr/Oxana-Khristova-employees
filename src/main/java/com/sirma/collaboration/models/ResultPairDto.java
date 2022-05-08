package com.sirma.collaboration.models;


public class ResultPairDto implements Comparable<ResultPairDto> {

    private int firstEmployeeId;

    private  int secondEmployeeId;

    private int projectId;

    private long daysWorked;

    public ResultPairDto(int firstEmployeeId, int secondEmployeeId, int projectId, long daysWorked) {
        super();
        this.setFirstEmployeeId(firstEmployeeId);
        this.setSecondEmployeeId(secondEmployeeId);
        this.setProjectId(projectId);
        this.setDaysWorked(daysWorked);
    }

    public ResultPairDto() {
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

    public int getProjectId() {
        return projectId;
    }

    private void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public long getDaysWorked() {
        return daysWorked;
    }

    private void setDaysWorked(long daysWorked) {
        this.daysWorked = daysWorked;
    }

    @Override
    public int compareTo(ResultPairDto o) {
        if (this.getDaysWorked() - o.getDaysWorked() < 0) {
            return  1;
        } else if (this.getDaysWorked() - o.getDaysWorked() > 0) {
            return  -1;
        }
        return 0;
    }
}
