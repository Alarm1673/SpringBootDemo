package com.zzl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class <code>Object</code> is the root of the class hierarchy.
 * Every class has <code>Object</code> as a superclass. All objects,
 * including arrays, implement the methods of this class.
 *
 * @author zhangzilong
 * @version 1.0
 * @see
 * @since JDK1.7
 * <p>
 * History
 * Created by zhangzilong on 17/7/30.
 */
@RestController
public class HelloController {


    @RequestMapping("/")
    public String index(){
        return "Hello world";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}
