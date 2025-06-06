package net.engineeringdigest.journalApp.controller;
import net.engineeringdigest.journalApp.entity.StudentCollegeDetails;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/collegeDetails")
public class StudentCollegeDetailsController {
    Map<Long, StudentCollegeDetails> collegeDetailsStorage=new HashMap<>();
    @PostMapping
    public boolean addCollegeDetails(@RequestBody StudentCollegeDetails newStudentDetails){
        collegeDetailsStorage.put(newStudentDetails.getCollegeId(),newStudentDetails);
        return true;
    }
    @GetMapping
    public List<StudentCollegeDetails> getStudentCollegeDetails(){
        return new ArrayList<>(collegeDetailsStorage.values());
    }
    @GetMapping("id{myId}")
    public StudentCollegeDetails getCollegeDetailsById(@PathVariable long myId){
        return collegeDetailsStorage.get(myId);
    }
    @GetMapping("/count")
    public long countCollegeRecords(){
        return collegeDetailsStorage.size();
    }
    @GetMapping("/search")
    public List<StudentCollegeDetails> searchCollegeDetails(
            @RequestParam(required=false) String firstName,
            @RequestParam(required=false) String lastName,
            @RequestParam(required=false) String collegeName,
            @RequestParam(required=false) long collegeId,
            @RequestParam(required=false) long studentId,
            @RequestParam(required=false) String collegeEmail,
            @RequestParam(required=false) String collegePhone,
            @RequestParam(required=false) String collegeAddress,
            @RequestParam(required=false) String collegeCity,
            @RequestParam(required = false) String collegeState,
            @RequestParam(required = false) String collegeZip){
        List <StudentCollegeDetails> result=new ArrayList<>();
        for(StudentCollegeDetails myEntry:collegeDetailsStorage.values()){
            boolean firstNameCheck=firstName==null || myEntry.getFirstName().toLowerCase().contains(firstName.toLowerCase());
            boolean lastNameCheck=lastName==null || myEntry.getLastName().toLowerCase().contains(lastName.toLowerCase());
            boolean collegeNameCheck=collegeName==null || myEntry.getCollegeName().toLowerCase().contains(collegeName.toLowerCase());
            boolean collegeIdCheck=collegeId==0 || myEntry.getCollegeId()==collegeId;
            boolean studentIdCheck=studentId==0 || myEntry.getStudentId()==studentId;
            boolean collegeEmailCheck=collegeEmail==null || myEntry.getCollegeEmail().toLowerCase().contains(collegeEmail.toLowerCase());
            boolean collegePhoneCheck=collegePhone==null  || myEntry.getCollegePhone().toLowerCase().contains(collegePhone.toLowerCase());
            boolean collegeAddressCheck =collegeAddress==null || myEntry.getCollegeAddress().toLowerCase().contains(collegeAddress.toLowerCase());
            boolean collegeCityCheck=collegeCity==null || myEntry.getCollegeCity().toLowerCase().contains(collegeCity.toLowerCase());
            boolean collegeStateCheck =collegeState==null || myEntry.getCollegeState().toLowerCase().contains(collegeState.toLowerCase());
            boolean collegeZipCheck =collegeZip==null;
            if(firstNameCheck && lastNameCheck && collegeNameCheck && studentIdCheck && collegeEmailCheck && collegePhoneCheck && collegeAddressCheck && collegeCityCheck &&  collegeStateCheck && collegeZipCheck){
                result.add(myEntry);
            }
        }
        return result;
    }
    @PutMapping("id{myId}")
    public boolean updateCollegeDetails(@RequestBody StudentCollegeDetails newCollegeDetails,@PathVariable long myId){
        collegeDetailsStorage.put(newCollegeDetails.getCollegeId(),newCollegeDetails);
        return  true;
    }
    @DeleteMapping("delete{myId}")
    public boolean deleteCollegeDetails(@PathVariable long myId){
        collegeDetailsStorage.remove(myId);
        return  true;
    }
}
