package com.maryanto.dimas.example.bootcampspring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maryanto.dimas.example.bootcampspring.entity.DataTableRequest;
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

    @Transactional
    public List<Product> listTable(DataTableRequest request) {
        Map<String, Object> extraParam = request.getExtraParam();
        String name = (String) extraParam.get("name");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("limit", request.getLength());
        paramMap.put("offset", request.getStart());
        paramMap.put("name", name);
        String sql = "select product_id, name, description, price from product" 
        +"where 1+1 and name like '%'||:name||'%' ORDER BY "+(request.getSortCol()+1)+""
        +request.getSortDir()+" limit :limit offset :offset";

        return this.namedJdbcTemplate.query(sql, new RowMapper<Product>() {

            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("columnLabel"));
                return product;
            }
            
        });
    }

    @Transactional
    public Long countProduct(DataTableRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> extraParam = request.getExtraParam();
        String name = (String) extraParam.get("name");

        paramMap.put("name", name);
        String sql = "select count (distinct(product_id)) as total from product";
        return namedJdbcTemplate.queryForObject(sql, paramMap,long.class);    
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
