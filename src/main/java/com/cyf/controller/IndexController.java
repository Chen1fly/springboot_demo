package com.cyf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
    @GetMapping(value = "/index")
    public String toIndex() {
        return "ems/login";

    }

    @GetMapping(value = "/change_mumber")
    public String change_mumber() {
        return "ems/change_mumber";

    }

    @GetMapping(value = "/toRegister")
    public String toRegister() {

        return "ems/regist";
    }

    @GetMapping(value = "toSave")
    public String toSave() {

        return "ems/add_mumber";
    }

}
