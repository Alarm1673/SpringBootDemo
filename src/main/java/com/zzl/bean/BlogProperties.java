package com.zzl.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

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
 * Created by zhangzilong on 17/8/1.
 */

@Data
public class BlogProperties {

    @Value("${com.zzl.blog.name}")
    private String name;
    @Value("${com.zzl.blog.title}")
    private String title;

}
