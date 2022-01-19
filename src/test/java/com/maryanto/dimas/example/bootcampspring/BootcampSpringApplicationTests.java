package com.maryanto.dimas.example.bootcampspring;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;
import java.util.stream.Collectors;

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
        List<String> listNama = list.stream()
                .map(Department::getNama).collect(Collectors.toList());

        try {
            Department dep = this.depRepo.findByIdDuplicate(6);
            System.out.println(dep.getNama());
        } catch (EmptyResultDataAccessException erda) {
            System.out.println("datanya kosong!");
        } catch (IncorrectResultSizeDataAccessException irsdae) {
            System.out.println("datanya lebih dari 1");
        }

//        Department dep = this.depRepo.findByIdNoThrowing(6);
//        System.out.println(dep.getNama());
    }

}
