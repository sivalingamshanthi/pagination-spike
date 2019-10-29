package com.spike.pagination.paginationspike.service;

import com.spike.pagination.paginationspike.model.Student;
import com.spike.pagination.paginationspike.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void addStudents(List<Student> students){
        studentRepository.saveAll(students);
    }

    public List<Student> getAllStudents(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);

        if(studentPage.isEmpty())
            return new ArrayList<>();

        return studentPage.getContent();
    }

    public Page<Student> getAllPagedStudents(Pageable pageable){
        Page<Student> studentPage = studentRepository.findAll(pageable);

        return studentRepository.findAll(pageable);
    }
}
