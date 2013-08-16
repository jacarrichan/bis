package com.avicit.bis.hr.user.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.avicit.bis.hr.user.model.UserModel;
import com.avicit.bis.hr.user.model.UserQueryModel;
import com.avicit.bis.hr.user.service.UserService;
import com.avicit.framework.constants.Constants;
import com.avicit.framework.web.support.editor.DateEditor;
import com.avicit.framework.web.support.pagination.Page;



@Controller
public class UserController {

    @Autowired
    @Qualifier("UserService")
    private UserService userService;
    

    @RequestMapping(value = "/user", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model) {

        setCommonData(model);
        model.addAttribute(Constants.COMMAND, new UserModel());

        int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
        Integer id = ServletRequestUtils.getIntParameter(request, "id", -1);
        boolean pre = ServletRequestUtils.getBooleanParameter(request, "pre", false);
        Page<UserModel> page = null;
        if(id > 0) {
            if(pre) {
                page = userService.pre(id, pn);
            }
            else {
                page = userService.next(id, pn);
            }
        } 
        else {
            page = userService.listAll(pn);
        }
        request.setAttribute("page", page);
        return "user/list";
    }



    @RequestMapping(value = "/user/query", method = {RequestMethod.GET})
    public String query(HttpServletRequest request, Model model, @ModelAttribute("command") UserQueryModel command) {
        setCommonData(model);
        model.addAttribute(Constants.COMMAND, command);
        int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
        model.addAttribute("page", userService.query(pn, Constants.DEFAULT_PAGE_SIZE, command));

        return "user/list";
    }


    private void setCommonData(Model model) {
        //设置通用属性
    }

    @RequestMapping(value="/user/{userId}/view", method = {RequestMethod.GET})
    public String view(@PathVariable Integer topicId, HttpServletRequest request) {
        request.setAttribute(Constants.COMMAND, userService.get(topicId));
        return "user/view";
    }



    
    @RequestMapping(value = "/user/add", method = {RequestMethod.GET})
    public String toAdd(Model model) {
        
        if(!model.containsAttribute(Constants.COMMAND)) {
            model.addAttribute(Constants.COMMAND, new UserModel());
        }
        setCommonData(model);
        return "user/add";
    }
    
    @RequestMapping(value = "/user/{id}/update", method = {RequestMethod.POST})
    public String toUpdate(Model model, @PathVariable Integer id) {
        if(!model.containsAttribute(Constants.COMMAND)) {
            model.addAttribute(Constants.COMMAND,  userService.get(id));
        }
        setCommonData(model);
        return "user/update";
    }
    
    @RequestMapping(value = "/user/{id}/delete", method = {RequestMethod.GET})
    public String toDelete(@PathVariable Integer  id) {
        return "user/delete";
    }


    @RequestMapping(value = "/user/add", method = {RequestMethod.POST})
    public String add(Model model, @ModelAttribute("command") @Valid UserModel command, BindingResult result) {
        
        //如果有验证错误 返回到form页面
        if(result.hasErrors()) {
            model.addAttribute(Constants.COMMAND, command);
            return toAdd(model);
        }
         userService.save(command);
        return "redirect:/user/success";
    }
    
    @RequestMapping(value = "/user/{id}/update", method = {RequestMethod.PUT})
    public String update(Model model, @ModelAttribute("command") @Valid UserModel command, BindingResult result) {
        if(result.hasErrors()) {
            model.addAttribute(Constants.COMMAND, command);
            return toUpdate(model, command.getId());
        }
        userService.update(command);
        return "redirect:/user/success";
    }
    
    @RequestMapping(value = "/user/{id}/delete", method = {RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/user/success";
    }
    
    @RequestMapping(value = "/user/success", method = {RequestMethod.GET})
    public String success() {
        return "user/success";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
  
    
}
