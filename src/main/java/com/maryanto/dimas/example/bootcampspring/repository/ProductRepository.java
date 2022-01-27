package com.maryanto.dimas.example.bootcampspring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.maryanto.dimas.example.bootcampspring.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ProductRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Product> list() {
        return this.namedJdbcTemplate.query(
                "select * from product",
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(4));
                    }
                });
    }

    public Product findById(Integer id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedJdbcTemplate.queryForObject("select * from product where product_id = :id",
                map, new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(4));
                    }
                });
    }

    // 

    // @Transactional
    // public Product updateById(Product value) {
    //     String query = "update product set name = :name, description = :description where department_id = :id";
    //     MapSqlParameterSource map = new MapSqlParameterSource();
    //     map.addValue("name", value.getNama());
    //     map.addValue("id", value.getId());
    //     map.addValue("description", value.getDescription());
    //     this.namedJdbcTemplate.update(query, map);
    //     return value;
    // }


}
