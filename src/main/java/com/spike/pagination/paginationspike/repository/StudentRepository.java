package com.spike.pagination.paginationspike.repository;

import com.spike.pagination.paginationspike.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {
    Page<Student> findAll(Pageable pageable);
}
