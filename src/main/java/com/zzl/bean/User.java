package com.zzl.bean;

import lombok.Data;

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
@Data
public class User {
    private Long id;
    private String name;
    private int age;

}
