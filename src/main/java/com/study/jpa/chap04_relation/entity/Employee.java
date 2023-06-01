package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter
//Jpa 연관관계 매핑에서는 연관관계 데이터는 ToString에서 제외해야 한다.
@ToString(exclude = "department") //제외할 필드명. ','로 구분하여 나열 가능
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) //다대일
    //default값 eager. 항상 조인을 수행. lazy는 필요한 경우에만 조인을 수행
    @JoinColumn(name = "dept_id")
    private Department department;
}
