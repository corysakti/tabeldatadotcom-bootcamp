package com.maryanto.dimas.example.bootcampspring;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@SpringBootTest
class BootcampSpringApplicationTests {

    @Autowired
    private DepartmentRepository depRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void testDataDepartment() {
        try {
            Department dep = this.depRepo.findById(6);
            System.out.println(dep.getNama());
        } catch (EmptyResultDataAccessException erda) {
            System.out.println("datanya kosong!");
        }


//        Department dep = this.depRepo.findByIdNoThrowing(6);
//        System.out.println(dep.getNama());


    }

}
