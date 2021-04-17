package top.codenergy.eduservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.codenergy.commonutils.ResultObj;

/**
 * @author wangyu
 */
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {
    /**
     * 登录
     */
    @PostMapping("/login")
    public ResultObj login(){
        return ResultObj.ok().data("token","admin");
    }

    /**
     * info
     * @return
     */
    @GetMapping("/info")
    public ResultObj info(){
        return ResultObj.ok()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp4.ssl.cdn.btime.com%2Ft0162fc6854a8bda678.jpg%3Fsize%3D500x500&refer=http%3A%2F%2Fp4.ssl.cdn.btime.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621213831&t=998b037bd760e9167f03c3380db13f7b");
    }
}
