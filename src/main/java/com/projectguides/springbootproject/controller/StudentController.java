package com.projectguides.springbootproject.controller;

import com.projectguides.springbootproject.exception.ResourceNotFoundException;
import com.projectguides.springbootproject.model.Student;
import com.projectguides.springbootproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("studentapi")
public class StudentController {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    //create a RestAPI//

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    //Build GET Student ID by REST API//

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id:" + id));
        return ResponseEntity.ok(student);
    }

    //Build UPDATE Student Rest API//

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student studentDetails)
    {
        Student updateStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not Exist with id: " + id));
        updateStudent.setName(studentDetails.getName());
        updateStudent.setEmail(studentDetails.getEmail());
        updateStudent.setGender(studentDetails.getGender());
        updateStudent.setPhone(studentDetails.getPhone());
        studentRepository.save(updateStudent);
        return ResponseEntity.ok(updateStudent);
    }

    //DELETE Student Rest API//

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not Exist with id: " + id));

        studentRepository.delete(student);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //to get the address of details

    @GetMapping("getstudentaddress/{id}")
    public List<Map<String,Object>> getstudentaddressdetails(@PathVariable int id){
        List<Map<String,Object>> list=new LinkedList<>();
        list=jdbc.queryForList("SELECT s.name,s.email,s.phone,a.streetline1,a.streetline2,a.city,a.state,a.country,a.pincode FROM student s JOIN address a ON s.id = a.student_id WHERE s.id ="+id+"");
        return list;
    }

}
