package net.engineeringdigest.journalApp.controller;
import net.engineeringdigest.journalApp.entity.TeacherDetailsEntry;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/details")
public class MyController {
    private Map<Long, TeacherDetailsEntry> teacherDetails = new HashMap<>();

    @GetMapping
    public List<TeacherDetailsEntry> getTeacherDeatils() {
        return new ArrayList<>(teacherDetails.values());
    }

    @PostMapping
    public boolean createEntryForTeacherDetails(@RequestBody TeacherDetailsEntry myEntry) {
        teacherDetails.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public TeacherDetailsEntry getTeacherDetailsById(@PathVariable long myId) {
        return teacherDetails.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteDetails(@PathVariable long myId) {
        teacherDetails.remove(myId);
        return true;
    }
    @PutMapping("id/{myId}")
    public boolean updateEntries(@PathVariable long myId,@RequestBody TeacherDetailsEntry myEntry){
        teacherDetails.put(myId,myEntry);
        return true;
    }
    @GetMapping("count")
    public int getCountOfRecords(){
        return teacherDetails.size();
    }
    //code by copilot
//    @GetMapping("/search")
//    public List<TeacherDetailsEntry> searchTeachers(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String designation) {
//
//        List<TeacherDetailsEntry> result = new ArrayList<>();
//
//        for (TeacherDetailsEntry entry : teacherDetails.values()) {
//            boolean matchesName = (name == null || entry.getName().toLowerCase().contains(name.toLowerCase()));
//            boolean matchesDesignation = (designation == null || entry.getDesignatiion().toLowerCase().contains(designation.toLowerCase()));
//
//            if (matchesName && matchesDesignation) {
//                result.add(entry);
//            }
//        }
//        return result;
//    }
//}
@GetMapping("/search")
public List<TeacherDetailsEntry> searchForTeacher(
        @RequestParam(required=false) String name,
        @RequestParam(required=false) String designation){
        List <TeacherDetailsEntry> result=new ArrayList<>();
        for(TeacherDetailsEntry entry:teacherDetails.values()){
          boolean matchName=  name==null || entry.getName().toLowerCase().contains(name.toLowerCase());
          boolean matchDesignation=designation==null || entry.getDesignation().toLowerCase().contains(designation.toLowerCase());
          if(matchDesignation && matchName){
              result.add(entry);
          }
        }
        return result;
    }
}
