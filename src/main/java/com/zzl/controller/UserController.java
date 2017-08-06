package com.zzl.controller;

import com.zzl.bean.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
 * Created by zhangzilong on 17/8/3.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<User> getUserList(){
        List<User> list = new ArrayList<>(users.values());
        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute User user){
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }

}
