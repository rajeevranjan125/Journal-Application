package net.engineeringdigest.journalApp.entity;

public class StudentDetails {
    private long id;
    private  String name;
    public void setStudentId(long id){
        this.id=id;
    }
    public long getStudentId(){
        return id;
    }
    public void setStudentName(String name){
        this.name=name;
    }
    public String getStudentName(){
        return name;
    }
}
