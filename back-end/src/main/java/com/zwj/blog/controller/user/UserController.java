package com.zwj.blog.controller.user;

import com.zwj.blog.auth.CurrentUser;
import com.zwj.blog.domain.User;
import com.zwj.blog.service.ArticleSerivice;
import com.zwj.blog.service.TagSerivice;
import com.zwj.blog.service.UserService;
import com.zwj.blog.utils.Md5Util;
import com.zwj.blog.utils.ResultInfo;
import com.zwj.blog.utils.ResultInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleSerivice articleSerivice;

    @Autowired
    private TagSerivice tagSerivice;

    @RequestMapping(value = "/user/info/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo loadUserInfo(@PathVariable("name")String name, Model model){
        User user = this.userService.loadUserByUsername(name);
        if(user == null)
            return ResultInfoFactory.getErrorResultInfo("用户不存在!");
        model.addAttribute("user",user);
        this.loadModel(model,name);
        return ResultInfoFactory.getSuccessResultInfo();
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo loadUserInfo(Model model){
        String userId = this.currentUser.getId();
        if(userId == null)
            return ResultInfoFactory.getErrorResultInfo("你还没有登录!");
        else {
            User u = this.userService.loadUserByUserId(userId);
            model.addAttribute("user",u);
            this.loadModel(model,u.getUserName());
            return ResultInfoFactory.getSuccessResultInfo();
        }
    }

    @RequestMapping("/user/index/{authorName}")
    public String index(@PathVariable("authorName")  String authorName, Model model){
        model.addAttribute("userName",authorName);
        return "blog/user/index";
    }

    private void loadModel(Model model,String name){
        Long articleCount = this.articleSerivice.countByAuthorName(name);
        Long tagCount =  this.tagSerivice.CountByAuthorName(name);
        model.addAttribute("articleCount",articleCount);
        model.addAttribute("tagCount",tagCount);
    }

}
