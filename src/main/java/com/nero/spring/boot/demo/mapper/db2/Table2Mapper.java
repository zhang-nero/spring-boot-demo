package com.nero.spring.boot.demo.mapper.db2;

import com.nero.spring.boot.demo.entities.Table1;
import com.nero.spring.boot.demo.entities.Table2;

import java.util.List;

/**
 * @author Nero
 * @since 2019-07-17 16:35
 */
public interface Table2Mapper {
    int addTable2(Table2 table2);
    List<Table1> selectTable2(String queryString);
}
