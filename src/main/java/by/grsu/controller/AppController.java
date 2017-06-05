package by.grsu.controller;

import by.grsu.entity.RepnoteRes;
import by.grsu.entity.Users;
import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AppController {

    @Autowired
    private ReportingNoticeServiceImpl reportingNoticeService;

    @Autowired
    private RepnoteResServiceImpl repnoteResService;

    @Autowired
    private UsersServiceImp usersService;

    @Autowired
    private SubResourceServiceImp subResourceService;

    @Autowired
    private ResourceServiceImpl resourceService;

    @Autowired
    private RegimeAccessServiceImpl regimeAccessService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "admin";
        }
        return "user";
    }

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().equals("anonymousUser"))
            return "login_page";
        return homePage(request);
    }

    @RequestMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/notice/{id}", method = RequestMethod.GET)
    public String findOneReportingNotice(@PathVariable long id, ModelMap modelMap, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getByUsername(username);
        if (user != null && request.isUserInRole("USER")
                && user.getEmployer_id().getId() != reportingNoticeService.getById(id).getEmployers_id().getId()) {
            return "403";
        }
        RepnoteRes repnoteRes = new RepnoteRes();
        modelMap.addAttribute("newSubres", repnoteRes);
        modelMap.addAttribute("regimeaccess", regimeAccessService.getAll());
        modelMap.addAttribute("resource", resourceService.getAll());
        modelMap.addAttribute("subresource", subResourceService.getAll());
        modelMap.addAttribute("reportingNotice", reportingNoticeService.getById(id));
        modelMap.addAttribute("subres", repnoteResService.getByNoticeId(id));
        return "reporting_notice";
    }

    @RequestMapping(value = "/notice_add_subres/{notice_id}", method = RequestMethod.POST)
    public String addSubresForNotice(@PathVariable long notice_id, @ModelAttribute("newSubres") RepnoteRes newSubres){
        newSubres.setReportingNotice_id(reportingNoticeService.getById(notice_id));
        repnoteResService.save(newSubres);
        return "redirect:/notice/" + notice_id;
    }

    @RequestMapping(value = "/detele_{noticeId}_subres/{subresId}")
    public String deleteNoticeSubres(@PathVariable long noticeId, @PathVariable long subresId){
        repnoteResService.delete(subresId);
        return "redirect:/notice/" + noticeId;
    }

    @RequestMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "/user")
    public String userPage() {
        return "user";
    }

    @RequestMapping(value = "/403")
    public String accessDeniedPage(){
        return "403";
    }
}
