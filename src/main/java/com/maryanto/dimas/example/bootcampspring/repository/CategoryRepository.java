package com.maryanto.dimas.example.bootcampspring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.maryanto.dimas.example.bootcampspring.entity.Category;
import com.maryanto.dimas.example.bootcampspring.entity.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class CategoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String sqlSelectAll = "Select * from Category";

    private String sqlSelectAllJoinTable = "SELECT category_id,category.name,category.description,department.department_id,department.name,department.description FROM category INNER JOIN department ON department.department_id = category.department_id";

    private String sqlSelectIdJoinTable = "SELECT category_id,category.name,category.description,department.department_id,department.name,department.description FROM category INNER JOIN department ON department.department_id = category.department_id where category_id = :id";

    private String sqlSelectById = "Select * from Category where category_id = :id";
    private String sqlInsert = "insert into category(category_id, department_id, name, description)\n" +
                "values (nextval('department_department_id_seq'), :department_id, :nama, :desc)";

    private String sqlUpdateById = "update category set name = :name where category_id = :id";

    private String sqlDeleteById = "delete from category where category_id = :id";


    //SELECT category_id,category.name,category.description, department.department_id, department.name, 
    //department.description FROM category INNER JOIN department ON department.department_id = category.department_id

    public List<Category> listJoin(){
        return this.namedParameterJdbcTemplate.query(sqlSelectAllJoinTable, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                Department dep = new Department(rs.getInt(4), rs.getString(5), rs.getString(6));
                return new Category
                (
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    dep
                );
            }
        });
    }

    public List<Category> list(){
        return this.namedParameterJdbcTemplate.query(sqlSelectAll, new RowMapper<Category>() {

            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                return new Category
                (
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4)
                );
            }
            
        });
    }

    public Category findById(Integer id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(sqlSelectIdJoinTable, map,
        new RowMapper<Category>() {

            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                Department dep = new Department(rs.getInt(4), rs.getString(5), rs.getString(6));
                return new Category
                (
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    dep
                );
            }
            
        });
        
    }

    public Category findByIdDuplicate(Integer id) throws IncorrectResultSizeDataAccessException, EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(sqlSelectById, map,
        new RowMapper<Category>() {

            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                return new Category
                (
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4)
                );
            }
            
        });
        
    }

    public Category findByIdNoThrowing(Integer id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(sqlSelectById, map,
        new RowMapper<Category>() {

            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                // TODO Auto-generated method stub
                return new Category
                (
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4)
                );
            }
            
        });
        
    }

    @Transactional
    public Category insert(Category value) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("department_id", value.getDepartment_id());
        map.addValue("nama", value.getNama());
        map.addValue("desc", value.getDescription());
        this.namedParameterJdbcTemplate.update(sqlInsert, map, keyHolder, new String[]{"category_id"});

        Number key = keyHolder.getKey();
        value.setId(key.intValue());
        return value;
    }
   // DELETE FROM films USING producers
   // WHERE producer_id = producers.id AND producers.name = 'foo';

   // UPDATE table_name SET column1 = value1, column2 = value2 WHERE condition;
   @Transactional
    public Category updateById(Category value) {
        
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("department_id", value.getDepartment_id());
        map.addValue("name", value.getNama());
        map.addValue("id", value.getId());
        this.namedParameterJdbcTemplate.update(sqlUpdateById, map);
        return value;
        
    }

    // DELETE FROM table_name WHERE condition;
    @Transactional
    public void deleteById(Long id) {
        
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        this.namedParameterJdbcTemplate.update(sqlDeleteById, map);
        
    }


    
}
