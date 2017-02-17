package by.grsu.controller;

import by.grsu.entity.Users;
import by.grsu.service.Impl.RepnoteResServiceImpl;
import by.grsu.service.Impl.ReportingNoticeServiceImpl;
import by.grsu.service.Impl.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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
        if (user.getEmployer_id().getId() != reportingNoticeService.getById(id).getEmployers_id().getId()
                && request.isUserInRole("USER")) {
            return "403";
        }
        modelMap.addAttribute("reportingNotice", reportingNoticeService.getById(id));
        modelMap.addAttribute("subres", repnoteResService.getByNoticeId(id));
        return "reporting_notice";
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
