package com.cos.Security1.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@Controller
class IndexController {
    @GetMapping(path=["","/"])
    fun index():String{
        return "index"
    }
    @GetMapping("/user")
    @ResponseBody
    fun user():String{
        return "user"
    }
    @GetMapping("/admin")
    @ResponseBody
    fun admin():String{
        return "admin"
    }
    @GetMapping("/manager")
    @ResponseBody
    fun manager():String{
        return "manager"
    }
    @GetMapping("/login")
    @ResponseBody
    fun login():String{
        return "login"
    }
    @GetMapping("/join")
    @ResponseBody
    fun join():String{
        return "join"
    }
    @GetMapping("/joinProc")
    @ResponseBody
    fun joinProc(): String{
        return "회원가입완료됨"
    }
}