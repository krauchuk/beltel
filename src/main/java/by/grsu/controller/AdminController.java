package by.grsu.controller;

import by.grsu.entity.*;
import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private RepnoteResServiceImpl repnoteResService;

    @Autowired
    private ReportingNoticeServiceImpl reportingNoticeService;

    @RequestMapping(value = "/new_notices", method = RequestMethod.GET)
    public String newRequest(ModelMap modelMap) {
        return "new_notices";
    }

    @RequestMapping(value = "/all_notices", method = RequestMethod.GET)
    public String allRequest(ModelMap modelMap) {
        modelMap.addAttribute("reportingNotice", reportingNoticeService.getAll());
        return "all_notices";
    }

    @RequestMapping(value = "/edit_db", method = RequestMethod.GET)
    public String editDB() {
        return "edit_db";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteReportingNotice(@PathVariable long id) {
        repnoteResService.deleteByNoticeId(id);
        reportingNoticeService.delete(id);
        return "redirect:/all_notices";
    }


    //PDF
    //Transactional(?)


    @RequestMapping(value = "/change_status/{id}")
    public String changeReportingNoticeStatus(@PathVariable long id) {
        ReportingNotice reportingNotice = reportingNoticeService.getById(id);
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        reportingNotice.setDateDone(date);
        if (reportingNotice.isStatus() == false) {
            reportingNotice.setStatus(true);
            reportingNoticeService.save(reportingNotice);
            //PDF
            return "redirect:/notice/" + id;
        }
        reportingNotice.setStatus(false);
        reportingNoticeService.save(reportingNotice);
        return "redirect:/notice/" + id;
    }
}
