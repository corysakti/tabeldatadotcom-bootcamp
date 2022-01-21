package com.maryanto.dimas.example.bootcampspring;

import com.maryanto.dimas.example.bootcampspring.entity.Category;
import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.CategoryRepository;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
class BootcampSpringApplicationTests {

    @Autowired
    private DepartmentRepository depRepo;

    @Autowired
    private CategoryRepository catRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void testInsertData() {
        Department department = new Department(null, "IT", "IT");
        department = this.depRepo.insert(department);
        System.out.println(department.getId());
    }

    @Test
    void testInsertDataCategory() {
        Category category = new Category(null,1, "Jalan", "Bagus");
        category = this.catRepo.insert(category);
        System.out.println(category.getId());
    }

    // @Test
    // void testUpdateData() {
    //     Category catAwal = this.catRepo.findById(1);
    // //    Category category = new category(1,null, UUID.randomUUID().toString(), null);
    //     Category category = new Category(1, null, UUID.randomUUID().toString(), null);
    //     this.catRepo.updateById(category);

    //     Category newCategory = this.catRepo.findById(category.getId());
    //     Assertions.assertEquals(category.getNama(), newCategory.getNama());
    //     Assertions.assertNotEquals(catAwal.getNama(), newCategory.getNama());
    // }
    // @Test
    // void testDeleteData(){
    //     Category category = new Category(30, null, null, null);
    //     this.catRepo.deleteById(category);
    // }

    @Test
    void testDataCategory() {
        List<Category> list = this.catRepo.list();
        list.forEach(data -> {
            System.out.println(data.getNama());
        });
        List<String> listNama = list.stream()
                .map(Category::getNama).collect(Collectors.toList());

        try {
            Category cat = this.catRepo.findByIdDuplicate(6);
            System.out.println(cat.getNama());
        } catch (EmptyResultDataAccessException erda) {
            System.out.println("datanya kosong!");
        } catch (IncorrectResultSizeDataAccessException irsdae) {
            System.out.println("datanya lebih dari 1");
        }

//        Department dep = this.depRepo.findByIdNoThrowing(6);
//        System.out.println(dep.getNama());
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
