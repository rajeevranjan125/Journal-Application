package net.engineeringdigest.journalApp.entity;

public class StudentCollegeDetails {
    private long collegeId;
    private String firstName;
    private String lastName;
    private String collegeName;
    private String collegeEmail;
    private String collegePhone;
    private String collegeWebsite;
    private String collegeAddress;
    private String collegeCity;
    private String collegeState;
    private String collegeZip;
    private long studentId;

    public long getStudentId(){
        return studentId;
    }
    public void setStudentId(long studentId){
        this.studentId=studentId;
    }
    public long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(long collegeId) {
        this.collegeId = collegeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCollegeState() {
        return collegeState;
    }

    public void setCollegeState(String collegeState) {
        this.collegeState = collegeState;
    }

    public String getCollegeZip() {
        return collegeZip;
    }

    public void setCollegeZip(String collegeZip) {
        this.collegeZip = collegeZip;
    }

    public String getCollegeCity() {
        return collegeCity;
    }

    public void setCollegeCity(String collegeCity) {
        this.collegeCity = collegeCity;
    }

    public String getCollegeAddress() {
        return collegeAddress;
    }

    public void setCollegeAddress(String collegeAddress) {
        this.collegeAddress = collegeAddress;
    }

    public String getCollegeWebsite() {
        return collegeWebsite;
    }

    public void setCollegeWebsite(String collegeWebsite) {
        this.collegeWebsite = collegeWebsite;
    }

    public String getCollegePhone() {
        return collegePhone;
    }

    public void setCollegePhone(String collegePhone) {
        this.collegePhone = collegePhone;
    }

    public String getCollegeEmail() {
        return collegeEmail;
    }

    public void setCollegeEmail(String collegeEmail) {
        this.collegeEmail = collegeEmail;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
