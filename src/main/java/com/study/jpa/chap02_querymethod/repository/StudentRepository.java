package com.study.jpa.chap02_querymethod.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByName(String name); // findBy + 컬럼명이 아닌 필드명으로 해야 함
    List<Student> findByCityAndMajor(String city, String major); // findBy + 컬럼명이 아닌 필드명으로 해야 함
    List<Student> findByMajorContaining(String major); // like query

    //native query 사용
    @Query(value = "select * from tbl_student where stu_name = :nm", nativeQuery = true)
    Student findNameWithSQL(@Param("nm") String name);

    // JPQL : 클래스를 대상으로 하는 query
    // select 별칭 from entity class 명 as 별칭
    // where 별칭.field명 = ?

    //native-sql : select * from tbl_student where stu_name = ?
    // jpql : select st from Student as st where st.name = ?

//    @Query(value = "select * from tbl_student where city = ?1", nativeQuery = true)
    @Query("select s from Student s where s.city = ?1")
    Student getByCityWithJPQL(String city);

    @Query("select s from Student s where s.name like %?1%")
    List<Student> searchByNamesWithJPQL(String name);

    //JPQL로 수정 삭제 쿼리 쓰기
    @Modifying //조회가 아닐 경우 무조건 붙여야 함
    @Query("delete from Student s where s.name = ?1")
    void deleteByNameWithJPQL(String name);

}
