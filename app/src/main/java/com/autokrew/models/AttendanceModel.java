package com.autokrew.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class AttendanceModel {
    @SerializedName("Table")
    @Expose
    private List<Table> table = null;
    @SerializedName("Table1")
    @Expose
    private List<Table1> table1 = null;
    @SerializedName("Table2")
    @Expose
    private List<Table2> table2 = null;
    @SerializedName("Table3")
    @Expose
    private List<Table3> table3 = null;
    @SerializedName("Table4")
    @Expose
    private List<Table4> table4 = null;

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public List<Table1> getTable1() {
        return table1;
    }

    public void setTable1(List<Table1> table1) {
        this.table1 = table1;
    }

    public List<Table2> getTable2() {
        return table2;
    }

    public void setTable2(List<Table2> table2) {
        this.table2 = table2;
    }

    public List<Table3> getTable3() {
        return table3;
    }

    public void setTable3(List<Table3> table3) {
        this.table3 = table3;
    }

    public List<Table4> getTable4() {
        return table4;
    }

    public void setTable4(List<Table4> table4) {
        this.table4 = table4;
    }



    //-----Table----------

    public class Table {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("EmployeeCode")
        @Expose
        private String employeeCode;
        @SerializedName("Company")
        @Expose
        private String company;
        @SerializedName("Location")
        @Expose
        private String location;
        @SerializedName("SubLocation")
        @Expose
        private String subLocation;
        @SerializedName("Department")
        @Expose
        private String department;
        @SerializedName("SubDepartment")
        @Expose
        private String subDepartment;
        @SerializedName("ReprtingPerson")
        @Expose
        private String reprtingPerson;
        @SerializedName("PunchCardNo")
        @Expose
        private String punchCardNo;
        @SerializedName("Timing")
        @Expose
        private String timing;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmployeeCode() {
            return employeeCode;
        }

        public void setEmployeeCode(String employeeCode) {
            this.employeeCode = employeeCode;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getSubLocation() {
            return subLocation;
        }

        public void setSubLocation(String subLocation) {
            this.subLocation = subLocation;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getSubDepartment() {
            return subDepartment;
        }

        public void setSubDepartment(String subDepartment) {
            this.subDepartment = subDepartment;
        }

        public String getReprtingPerson() {
            return reprtingPerson;
        }

        public void setReprtingPerson(String reprtingPerson) {
            this.reprtingPerson = reprtingPerson;
        }

        public String getPunchCardNo() {
            return punchCardNo;
        }

        public void setPunchCardNo(String punchCardNo) {
            this.punchCardNo = punchCardNo;
        }

        public String getTiming() {
            return timing;
        }

        public void setTiming(String timing) {
            this.timing = timing;
        }

    }

    //------Table1-----------

    public class Table1 {

        @SerializedName("WorkingDays")
        @Expose
        private Integer workingDays;
        @SerializedName("PresentDays")
        @Expose
        private Integer presentDays;
        @SerializedName("AbsentDays")
        @Expose
        private Integer absentDays;
        @SerializedName("WeekOff")
        @Expose
        private Integer weekOff;
        @SerializedName("HalfDays")
        @Expose
        private Integer halfDays;
        @SerializedName("Leave")
        @Expose
        private Integer leave;
        @SerializedName("Holiday")
        @Expose
        private Integer holiday;
        @SerializedName("PunchLate")
        @Expose
        private Integer punchLate;
        @SerializedName("PunchEarly")
        @Expose
        private Integer punchEarly;

        public Integer getWorkingDays() {
            return workingDays;
        }

        public void setWorkingDays(Integer workingDays) {
            this.workingDays = workingDays;
        }

        public Integer getPresentDays() {
            return presentDays;
        }

        public void setPresentDays(Integer presentDays) {
            this.presentDays = presentDays;
        }

        public Integer getAbsentDays() {
            return absentDays;
        }

        public void setAbsentDays(Integer absentDays) {
            this.absentDays = absentDays;
        }

        public Integer getWeekOff() {
            return weekOff;
        }

        public void setWeekOff(Integer weekOff) {
            this.weekOff = weekOff;
        }

        public Integer getHalfDays() {
            return halfDays;
        }

        public void setHalfDays(Integer halfDays) {
            this.halfDays = halfDays;
        }

        public Integer getLeave() {
            return leave;
        }

        public void setLeave(Integer leave) {
            this.leave = leave;
        }

        public Integer getHoliday() {
            return holiday;
        }

        public void setHoliday(Integer holiday) {
            this.holiday = holiday;
        }

        public Integer getPunchLate() {
            return punchLate;
        }

        public void setPunchLate(Integer punchLate) {
            this.punchLate = punchLate;
        }

        public Integer getPunchEarly() {
            return punchEarly;
        }

        public void setPunchEarly(Integer punchEarly) {
            this.punchEarly = punchEarly;
        }

    }


    //--------Table2--------

    public class Table2 {

        @SerializedName("AttendancePK")
        @Expose
        private Integer attendancePK;
        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("First In")
        @Expose
        private String firstIn;
        @SerializedName("Last Out")
        @Expose
        private String lastOut;
        @SerializedName("Working Hours")
        @Expose
        private String workingHours;
        @SerializedName("MachineStatus")
        @Expose
        private String machineStatus;
        @SerializedName("Status")
        @Expose
        private String status;
        @SerializedName("Deviation")
        @Expose
        private String deviation;

        @SerializedName("DeviationFK")
        @Expose
        private int deviationFK;


        @SerializedName("Employee Remarks")
        @Expose
        private String employeeRemarks;
        @SerializedName("Reporting Person Status")
        @Expose
        private String reportingPersonStatus;
        @SerializedName("Reporting Person Remarks")
        @Expose
        private String reportingPersonRemarks;
        @SerializedName("Shift")
        @Expose
        private String shift;
        @SerializedName("LockAttendance")
        @Expose
        private String lockAttendance;
        @SerializedName("LeaveDayType")
        @Expose
        private Integer leaveDayType;

        public int getDeviationFK() {
            return deviationFK;
        }

        public void setDeviationFK(int deviationFK) {
            this.deviationFK = deviationFK;
        }

        public Integer getAttendancePK() {
            return attendancePK;
        }

        public void setAttendancePK(Integer attendancePK) {
            this.attendancePK = attendancePK;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getFirstIn() {
            return firstIn;
        }

        public void setFirstIn(String firstIn) {
            this.firstIn = firstIn;
        }

        public String getLastOut() {
            return lastOut;
        }

        public void setLastOut(String lastOut) {
            this.lastOut = lastOut;
        }

        public String getWorkingHours() {
            return workingHours;
        }

        public void setWorkingHours(String workingHours) {
            this.workingHours = workingHours;
        }

        public String getMachineStatus() {
            return machineStatus;
        }

        public void setMachineStatus(String machineStatus) {
            this.machineStatus = machineStatus;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeviation() {
            return deviation;
        }

        public void setDeviation(String deviation) {
            this.deviation = deviation;
        }

        public String getEmployeeRemarks() {
            return employeeRemarks;
        }

        public void setEmployeeRemarks(String employeeRemarks) {
            this.employeeRemarks = employeeRemarks;
        }

        public String getReportingPersonStatus() {
            return reportingPersonStatus;
        }

        public void setReportingPersonStatus(String reportingPersonStatus) {
            this.reportingPersonStatus = reportingPersonStatus;
        }

        public String getReportingPersonRemarks() {
            return reportingPersonRemarks;
        }

        public void setReportingPersonRemarks(String reportingPersonRemarks) {
            this.reportingPersonRemarks = reportingPersonRemarks;
        }

        public String getShift() {
            return shift;
        }

        public void setShift(String shift) {
            this.shift = shift;
        }

        public String getLockAttendance() {
            return lockAttendance;
        }

        public void setLockAttendance(String lockAttendance) {
            this.lockAttendance = lockAttendance;
        }

        public Integer getLeaveDayType() {
            return leaveDayType;
        }

        public void setLeaveDayType(Integer leaveDayType) {
            this.leaveDayType = leaveDayType;
        }

    }


    //--------Table3----------

    public class Table3 {

        @SerializedName("Company")
        @Expose
        private String company;
        @SerializedName("Location")
        @Expose
        private String location;
        @SerializedName("SubLocation")
        @Expose
        private String subLocation;
        @SerializedName("MachineName")
        @Expose
        private String machineName;
        @SerializedName("MachineIP")
        @Expose
        private String machineIP;
        @SerializedName("PunchCardNo")
        @Expose
        private String punchCardNo;

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getSubLocation() {
            return subLocation;
        }

        public void setSubLocation(String subLocation) {
            this.subLocation = subLocation;
        }

        public String getMachineName() {
            return machineName;
        }

        public void setMachineName(String machineName) {
            this.machineName = machineName;
        }

        public String getMachineIP() {
            return machineIP;
        }

        public void setMachineIP(String machineIP) {
            this.machineIP = machineIP;
        }

        public String getPunchCardNo() {
            return punchCardNo;
        }

        public void setPunchCardNo(String punchCardNo) {
            this.punchCardNo = punchCardNo;
        }

    }

    //---------Table4-------

    public class Table4 {

        @SerializedName("Shift")
        @Expose
        private String shift;
        @SerializedName("Shift Type")
        @Expose
        private String shiftType;
        @SerializedName("Timing Type")
        @Expose
        private String timingType;
        @SerializedName("InTime")
        @Expose
        private String inTime;
        @SerializedName("OutTime")
        @Expose
        private String outTime;
        @SerializedName("GraceUptoTime")
        @Expose
        private String graceUptoTime;
        @SerializedName("EarlyGoingTime")
        @Expose
        private String earlyGoingTime;
        @SerializedName("Full Day Hours")
        @Expose
        private String fullDayHours;
        @SerializedName("Half Day Hours")
        @Expose
        private String halfDayHours;
        @SerializedName("Deviation Type")
        @Expose
        private String deviationType;
        @SerializedName("Late Coming Allow")
        @Expose
        private Integer lateComingAllow;
        @SerializedName("Early Going Allow")
        @Expose
        private Integer earlyGoingAllow;
        @SerializedName("Total Deviation Allowed")
        @Expose
        private Integer totalDeviationAllowed;
        @SerializedName("Hours Deviation")
        @Expose
        private String hoursDeviation;
        @SerializedName("Post Daviation Limit")
        @Expose
        private String postDaviationLimit;
        @SerializedName("Deviation Occurence")
        @Expose
        private Integer deviationOccurence;
        @SerializedName("Week Off")
        @Expose
        private String weekOff;

        public String getShift() {
            return shift;
        }

        public void setShift(String shift) {
            this.shift = shift;
        }

        public String getShiftType() {
            return shiftType;
        }

        public void setShiftType(String shiftType) {
            this.shiftType = shiftType;
        }

        public String getTimingType() {
            return timingType;
        }

        public void setTimingType(String timingType) {
            this.timingType = timingType;
        }

        public String getInTime() {
            return inTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public String getGraceUptoTime() {
            return graceUptoTime;
        }

        public void setGraceUptoTime(String graceUptoTime) {
            this.graceUptoTime = graceUptoTime;
        }

        public String getEarlyGoingTime() {
            return earlyGoingTime;
        }

        public void setEarlyGoingTime(String earlyGoingTime) {
            this.earlyGoingTime = earlyGoingTime;
        }

        public String getFullDayHours() {
            return fullDayHours;
        }

        public void setFullDayHours(String fullDayHours) {
            this.fullDayHours = fullDayHours;
        }

        public String getHalfDayHours() {
            return halfDayHours;
        }

        public void setHalfDayHours(String halfDayHours) {
            this.halfDayHours = halfDayHours;
        }

        public String getDeviationType() {
            return deviationType;
        }

        public void setDeviationType(String deviationType) {
            this.deviationType = deviationType;
        }

        public Integer getLateComingAllow() {
            return lateComingAllow;
        }

        public void setLateComingAllow(Integer lateComingAllow) {
            this.lateComingAllow = lateComingAllow;
        }

        public Integer getEarlyGoingAllow() {
            return earlyGoingAllow;
        }

        public void setEarlyGoingAllow(Integer earlyGoingAllow) {
            this.earlyGoingAllow = earlyGoingAllow;
        }

        public Integer getTotalDeviationAllowed() {
            return totalDeviationAllowed;
        }

        public void setTotalDeviationAllowed(Integer totalDeviationAllowed) {
            this.totalDeviationAllowed = totalDeviationAllowed;
        }

        public String getHoursDeviation() {
            return hoursDeviation;
        }

        public void setHoursDeviation(String hoursDeviation) {
            this.hoursDeviation = hoursDeviation;
        }

        public String getPostDaviationLimit() {
            return postDaviationLimit;
        }

        public void setPostDaviationLimit(String postDaviationLimit) {
            this.postDaviationLimit = postDaviationLimit;
        }

        public Integer getDeviationOccurence() {
            return deviationOccurence;
        }

        public void setDeviationOccurence(Integer deviationOccurence) {
            this.deviationOccurence = deviationOccurence;
        }

        public String getWeekOff() {
            return weekOff;
        }

        public void setWeekOff(String weekOff) {
            this.weekOff = weekOff;
        }

    }
}
