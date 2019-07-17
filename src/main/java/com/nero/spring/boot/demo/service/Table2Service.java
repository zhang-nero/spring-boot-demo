package com.nero.spring.boot.demo.service;

import com.github.pagehelper.PageHelper;
import com.nero.spring.boot.demo.entities.Table1;
import com.nero.spring.boot.demo.entities.Table2;
import com.nero.spring.boot.demo.mapper.db2.Table2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nero
 * @since 2019-07-17 16:44
 */
@Service
public class Table2Service {

    @Autowired
    private Table2Mapper table2Mapper;

    @Transactional(rollbackFor = Exception.class, transactionManager = "db2TransactionManager")
    public int addTable1(String name){
        Table2 table2 = new Table2();
        table2.setName2(name);
        int i = table2Mapper.addTable2(table2);
        long time = System.currentTimeMillis();
        if (time%2 == 1){
            throw new RuntimeException("奇数，不能插入");
        }
        return i;
    }

    @Transactional(rollbackFor = Exception.class, transactionManager = "db2TransactionManager")
    public List<Table1> queryTable1(String queryString, Integer pageNumber, Integer pageSize){
        if (pageNumber == null || pageNumber < 1){
            pageNumber = 1;
        }
        if (pageSize == null || pageSize < 1){
            pageSize = 10;
        }
        PageHelper.startPage(pageNumber, pageSize);

        return table2Mapper.selectTable2(queryString);
    }
}
