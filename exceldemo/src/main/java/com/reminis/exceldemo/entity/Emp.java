package com.reminis.exceldemo.entity;

import com.reminis.exceldemo.annotation.ExcelColumn;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Emp {

    @ExcelColumn(value = "员工主键id", col = 1)
    private Integer id;

    @ExcelColumn(value = "员工编号",col = 2)
    private String empNo;

    @ExcelColumn(value = "员工名称",col = 3)
    private String empName;

    @ExcelColumn(value = "员工薪资",col = 4)
    private BigDecimal salary;

    @ExcelColumn(value = "员工职称",col = 5)
    private String job;

    @ExcelColumn(value = "入职时间",col = 6)
    private LocalDateTime entryTime;
}
