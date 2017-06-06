package by.grsu.controller;

import by.grsu.RepnoteResList;
import by.grsu.entity.RepnoteRes;
import by.grsu.entity.ReportingNotice;
import by.grsu.entity.Users;
import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UsersServiceImp usersService;

    @Autowired
    ReportingNoticeServiceImpl reportingNoticeService;

    @Autowired
    RegimeAccessServiceImpl regimeAccessService;

    @Autowired
    SubResourceServiceImp subResourceService;

    @Autowired
    RepnoteResServiceImpl repnoteResService;

    @Autowired
    ResourceServiceImpl resourceService;

    @Autowired
    private OperationsHistoryController operationsHistory;

    @RequestMapping(value = "/user_notices", method = RequestMethod.GET)
    public String userRequest(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getByUsername(username);
        long userId = user.getEmployer_id().getId();
        modelMap.addAttribute("reportingNotice", reportingNoticeService.getByEmployerId(userId));
        return "user_notices";
    }

    @RequestMapping(value = "/create_notice", method = RequestMethod.GET)
    public String createRequest(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getByUsername(username);
        List<RepnoteRes> repnotes = new ArrayList<RepnoteRes>();
        for(int i = 0; i < 10; i++){
            repnotes.add(new RepnoteRes());
        }
        RepnoteResList list = new RepnoteResList();
        list.setRepnotes(repnotes);
        modelMap.addAttribute("employer", user.getEmployer_id());
        modelMap.addAttribute("subresource", subResourceService.getAll());
        modelMap.addAttribute("resource", resourceService.getAll());
        modelMap.addAttribute("regimeaccess", regimeAccessService.getAll());
        modelMap.addAttribute("data", list);
        return "create_notice";
    }

    @RequestMapping(value = "/user/notice_add/", method = RequestMethod.POST)
    public String addNewNotice(@ModelAttribute("data") RepnoteResList data){
        ReportingNotice reportingNotice = new ReportingNotice();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getByUsername(username);
        reportingNotice.setEmployers_id(user.getEmployer_id());
        reportingNotice.setStatus(false);
        try {
            reportingNotice.setIpAddr(InetAddress.getLocalHost().getHostAddress());
        }catch(Exception e){
            reportingNotice.setIpAddr("*нет данных*");
        }
        try {
            reportingNotice.setUserPcName(InetAddress.getLocalHost().getHostName());
        }catch (Exception e){
            reportingNotice.setUserPcName("*нет данных*");
        }
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        reportingNotice.setDateSet(date);
        reportingNoticeService.save(reportingNotice);
        for(int i = 0; i < data.getRepnotes().size(); i++){
            RepnoteRes repnoteRes = new RepnoteRes();
            repnoteRes.setReportingNotice_id(reportingNotice);
            repnoteRes.setRegimeAccess_id(
                    regimeAccessService.getById(data.getRepnotes().get(i).getRegimeAccess_id().getId())
            );
            repnoteRes.setSubResource_id(
                    subResourceService.getById(data.getRepnotes().get(i).getSubResource_id().getId())
            );
            repnoteResService.save(repnoteRes);
        }
        operationsHistory.saveOperation("Добавление заявки #" + reportingNotice.getId());
        return "redirect:/create_notice";
    }
}
