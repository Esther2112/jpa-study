package com.study.jpa.chap03_pagination.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPageRepository extends JpaRepository<Student, String> {

    //학생 조건없이 전체 조회 페이징(기본기능. 안 만들어도 됨;)
    Page<Student> findAll(Pageable pageable);

    //학생 이름에 특정 단어가 포함된걸 조회 + 페이징 (마지막 파라미터에 Pageable 넣기)
    Page<Student> findByNameContaining(String name, Pageable pageable); //containing : %word%, startWith : word%
}
