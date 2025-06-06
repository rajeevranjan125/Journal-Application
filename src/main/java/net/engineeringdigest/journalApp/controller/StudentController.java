package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.StudentDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studentDetails")
public class StudentController {
    Map<Long, StudentDetails> studentDetails = new HashMap<>();
    //important
    @GetMapping
    public ArrayList<StudentDetails> getStudentDetails() {
        return new ArrayList<>(studentDetails.values());
    }
    //important
    @PostMapping
    public boolean setStudentDetails(@RequestBody StudentDetails newStudentDetails) {
            studentDetails.put(newStudentDetails.getStudentId(), newStudentDetails);
            return  true;
    }
    //Important
    @GetMapping("/studentId{myStudentId}")
    public StudentDetails getStudentDetailsById(@PathVariable long myStudentId){
        return studentDetails.get(myStudentId);
    }
    @PutMapping("/updateByStudentId{myStudentId}")
    public boolean updateStudentdetails(@RequestBody StudentDetails newStudentDetails,@PathVariable long myStudentId){
        studentDetails.put(newStudentDetails.getStudentId(),newStudentDetails);
        return  true;
    }
    @DeleteMapping("/deleteById{myStudentId}")
    public boolean deleteStudentDetails(@PathVariable long myStudentId){
        studentDetails.remove(myStudentId);
        return  true;
    }
    //Important
    @GetMapping("/countRecords")
    public long countRecords(){
        return studentDetails.size();
    }
    //Important
    @GetMapping("/searchRecords")
    public List <StudentDetails> searchRecords(@RequestParam(required = false) String name){
        List <StudentDetails> result=new ArrayList<>();
      for(StudentDetails entry:studentDetails.values()){
          if(name==null || entry.getStudentName().toLowerCase().contains(name.toLowerCase())){
              result.add(entry);
          }
      }
      return result;
    }
}

