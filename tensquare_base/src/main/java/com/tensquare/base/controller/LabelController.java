package com.tensquare.base.controller;


import com.tensquare.base.entity.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 类功能说明:标签控制层
 * 类修改者	创建日期2020/2/9
 * 修改说明
 *  @RefreshScope 配置文件在更新重新加载时只会加载系统原生的属性
 *   不会加载自定义配置文件 该注解可以刷新使其生效
 * @author wzy
 * @version V1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/label")
@RefreshScope
public class LabelController {
    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpServletRequest request;

    @Value("${ip}")
    private String ip;

    /*** 查询全部列表 * @return */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        System.out.println("IP地址"+ ip);
        String authorization = request.getHeader("Authorization");
        System.out.println(authorization);
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /*** 根据ID查询标签 * @param id * @return */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成 功", labelService.findById(id));
    }

    /*** 增加标签
     北京市昌平区建材城西路金燕龙办公楼一层 电话：400-618-9090
     5.2.3 功能测试 （1）测试查询全部数据 使用浏览器测试GET方法 http://localhost:9001/label （2）测试根据ID查询标签 * @param label * @return */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /*** 修改标签 * @param label * @return */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /*** 删除标签 * @param id * @return */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /*** 根据条件查询
     * * @param searchMap
     * * @return
     * */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findSearch(label));
    }

    /*** 条件+分页查询 * @param searchMap * @param page * @param size * @return */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageList = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageList.getTotalElements(), pageList.getContent()));

    }
}