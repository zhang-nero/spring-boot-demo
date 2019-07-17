package com.nero.spring.boot.demo.mapper.db1;

import com.nero.spring.boot.demo.entities.Table1;

import java.util.List;

/**
 * @author Nero
 * @since 2019-07-17 16:35
 */
public interface Table1Mapper {
    int addTable1(Table1 table1);
    List<Table1> selectTable1(String queryString);
}
