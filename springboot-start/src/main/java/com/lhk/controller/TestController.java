package com.lhk.controller;


import com.lhk.pojo.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public String hello() {
        return "hello springBoot";
    }

    @RequestMapping("/getResource")
    public Resource getResource() {
        Resource resultResource = new Resource();
        BeanUtils.copyProperties(resource,resultResource);
        return resultResource;
    }
}
