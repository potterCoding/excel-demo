package com.reminis.exceldemo.web;

import com.alibaba.fastjson.JSON;
import com.reminis.exceldemo.entity.Emp;
import com.reminis.exceldemo.util.ExcelUtils;
import com.reminis.exceldemo.util.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/test")
public class ExcelController {

    private static final Logger log = LogManager.getLogger(ExcelController.class);

    /**
     * Excel导出
     * @param response
     */
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request,HttpServletResponse response){
        //使用假数据代替从数据库查出来的需要导出的数据
        List<Emp> empList = handleRepositoryData();
        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(request,response, empList, Emp.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
    }

    /**
     * Excel导入
     * @param file
     */
    @PostMapping("/readExcel")
    public Result<String> readExcel(@RequestBody MultipartFile file){
        long t1 = System.currentTimeMillis();
        log.info("上传的文件："+file);
        List<Emp> list = ExcelUtils.readExcel("", Emp.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
        list.forEach(
                b -> System.out.println(JSON.toJSONString(b))
        );
        return new Result<>();
    }

    public List<Emp> handleRepositoryData() {
        List<Emp> empList = new ArrayList<>();
        Emp emp;
        for (int i = 1; i<= 10; i++) {
            emp = new Emp();
            emp.setId(i);
            emp.setEmpName("员工" + i);
            emp.setEmpNo((1000 + i) + "");
            emp.setJob("JY" + i);
            emp.setSalary(new BigDecimal(i * 1000 + ""));
            emp.setEntryTime(LocalDateTime.now().minusHours(Long.valueOf(i)));
            empList.add(emp);
        }
        return empList;
    }

    /**
     * 前台页面的数据列表
     * @return
     */
    @GetMapping("/getList")
    public Result getList(){
        Result<List<Emp>> result = new Result<>();
        List<Emp> empList = handleRepositoryData();
        result.setData(empList);
        return result;
    }
}
