package by.grsu.controller.data;

import by.grsu.entity.*;
import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EditTableDataController {

    @Autowired
    private DivisionServiceImpl divisionService;

    @Autowired
    private EmployerServiceImpl employerService;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private RegimeAccessServiceImpl regimeAccessService;

    @Autowired
    private RegionServiceImpl regionService;

    @Autowired
    private ResourceServiceImpl resourceService;

    @Autowired
    private SectorServiceImpl sectorService;

    @Autowired
    private SubResourceServiceImp subResourceService;

    @Autowired
    private ZuesServiceImpl zuesService;

    @Autowired
    private RepnoteResServiceImpl repnoteResService;

    @Autowired
    private UsersServiceImp usersService;

    @Autowired
    private ReportingNoticeServiceImpl reportingNoticeService;

    @RequestMapping(value = "/admin/table_division_edit/{id}")
    public String editDivisionData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("divhead", employerService.getAll());
        modelMap.addAttribute("dataType", "division");
        if (id == 0) {
            modelMap.addAttribute("data", new Division());
            return "add_edit_data";
        }
        if(employerService.getByDivisionId(id).size() == 0 &&
                sectorService.getByDivisionId(id).size() == 0){
            modelMap.addAttribute("isused", false);
        }
        modelMap.addAttribute("data", divisionService.getById(id));
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/table_employer_edit/{id}")
    public String editEmployerData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("post", postService.getAll());
        modelMap.addAttribute("zues", zuesService.getAll());
        modelMap.addAttribute("sector", sectorService.getAll());
        modelMap.addAttribute("division", divisionService.getAll());
        modelMap.addAttribute("dataType", "employer");
        if (id == 0) {
            modelMap.addAttribute("data", new Employer());
            return "add_edit_data";
        }
        if(usersService.getByEmployerId(id).size() == 0 &&
                reportingNoticeService.getByEmployerId(id).size() == 0){
            modelMap.addAttribute("isused", false);
        }
        modelMap.addAttribute("data", employerService.getById(id));
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/table_post_edit/{id}")
    public String editPostData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("dataType", "post");
        if (id == 0) {
            modelMap.addAttribute("data", new Post());
            return "add_edit_data";
        }
        if(employerService.getByPostId(id).size() == 0){
            modelMap.addAttribute("isused", false);
        }
        modelMap.addAttribute("data", postService.getById(id));
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/table_regimeaccess_edit/{id}")
    public String editRegimeAccessData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("dataType", "regimeaccess");
        if (id == 0) {
            modelMap.addAttribute("data", new RegimeAccess());
            return "add_edit_data";
        }
        if(repnoteResService.getByRegimeAccessId(id).size() == 0){
            modelMap.addAttribute("isused", false);
        }
        modelMap.addAttribute("data", regimeAccessService.getById(id));
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/table_resource_edit/{id}")
    public String editResourceData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("dataType", "resource");
        if (id == 0) {
            modelMap.addAttribute("data", new Resource());
            return "add_edit_data";
        }
        if(subResourceService.getByResourceId(id).size() == 0){
            modelMap.addAttribute("isused", false);
        }
        modelMap.addAttribute("data", resourceService.getById(id));
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/table_sector_edit/{id}")
    public String editSectorData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("division", divisionService.getAll());
        modelMap.addAttribute("dataType", "sector");
        if (id == 0) {
            modelMap.addAttribute("data", new Sector());
            return "add_edit_data";
        }
        if(employerService.getBySectorId(id).size() == 0){
            modelMap.addAttribute("isused", false);
        }
        modelMap.addAttribute("data", sectorService.getById(id));
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/table_subresource_edit/{id}")
    public String editSubResourceData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("resource", resourceService.getAll());
        modelMap.addAttribute("dataType", "subresource");
        if (id == 0) {
            modelMap.addAttribute("data", new SubResource());
            return "add_edit_data";
        }
        if(repnoteResService.getBySubResourceId(id).size() == 0){
            modelMap.addAttribute("isused", false);
        }
        modelMap.addAttribute("data", subResourceService.getById(id));
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/table_zues_edit/{id}")
    public String editZuesData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("region", regionService.getAll());
        modelMap.addAttribute("dataType", "zues");
        if (id == 0) {
            modelMap.addAttribute("data", new Zues());
            return "add_edit_data";
        }
        if(employerService.getByZuesId(id).size() == 0){
            modelMap.addAttribute("isused", false);
        }
        modelMap.addAttribute("data", zuesService.getById(id));
        return "add_edit_data";
    }
}
