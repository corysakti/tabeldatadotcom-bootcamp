package com.maryanto.dimas.example.bootcampspring;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        List<Department> list = this.depRepo.list();
        list.forEach(data -> {
            System.out.println(data.getNama());
        });
    }

}
