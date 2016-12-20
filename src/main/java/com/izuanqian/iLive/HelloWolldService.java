package com.izuanqian.iLive;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by sanlion on 2016/12/20.
 */
@RequestScoped
public class HelloWolldService {

    public String sayHello() {
        return "hello world. weld is running..";
    }
}
