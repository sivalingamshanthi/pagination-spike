package com.spike.pagination.paginationspike.controller;

import com.spike.pagination.paginationspike.model.Student;
import com.spike.pagination.paginationspike.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudents(
            @RequestParam(name = "page", defaultValue = "0")int page,
            @RequestParam(name = "size", defaultValue = "20")int size){
        List<Student> students = studentService.getAllStudents(page, size);

        if(students.isEmpty())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(studentService.getAllStudents(page, size), HttpStatus.OK);
    }

    @GetMapping(value = "/page-students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPagedStudents(Pageable pageable,
                                              PagedResourcesAssembler assembler) {
        Page<Student> studentPage = studentService.getAllPagedStudents(pageable);

        if(studentPage.isEmpty())
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(assembler.toResource(studentPage), HttpStatus.OK);
    }

    @PostMapping
    public void addStudents(){
        List<Student> students = new ArrayList<>();

        students.add(new Student(1, "siva"));
        students.add(new Student(2, "anu"));
        students.add(new Student(3, "praveen"));
        students.add(new Student(4, "harshith"));
        students.add(new Student(5, "sakthi"));
        students.add(new Student(6, "vicky"));
        students.add(new Student(7, "gomathi"));
        students.add(new Student(8, "sam"));
        students.add(new Student(9, "andre"));
        students.add(new Student(10, "eric"));

        studentService.addStudents(students);
    }
}
