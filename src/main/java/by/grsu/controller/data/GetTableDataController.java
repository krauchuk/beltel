package by.grsu.controller.data;

import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GetTableDataController {

    @Autowired
    private DivisionServiceImpl divisionService;

    @Autowired
    private EmployerServiceImpl employerService;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private RegimeAccessServiceImpl regimeAccessService;

    @Autowired
    private ResourceServiceImpl resourceService;

    @Autowired
    private SectorServiceImpl sectorService;

    @Autowired
    private SubResourceServiceImp subResourceService;

    @Autowired
    private ZuesServiceImpl zuesService;

    @RequestMapping(value = "/admin/table_division")
    public String getDivisionData(ModelMap modelMap) {
        modelMap.addAttribute("data", divisionService.getAll());
        modelMap.addAttribute("dataType", "division");
        return "get_table_data";
    }

    @RequestMapping(value = "/admin/table_employer")
    public String getEmployerData(ModelMap modelMap) {
        modelMap.addAttribute("data", employerService.getAll());
        modelMap.addAttribute("dataType", "employer");
        return "get_table_data";
    }

    @RequestMapping(value = "/admin/table_post")
    public String getPostData(ModelMap modelMap) {
        modelMap.addAttribute("data", postService.getAll());
        modelMap.addAttribute("dataType", "post");
        return "get_table_data";
    }

    @RequestMapping(value = "/admin/table_regimeaccess")
    public String getRegimeAccessData(ModelMap modelMap) {
        modelMap.addAttribute("data", regimeAccessService.getAll());
        modelMap.addAttribute("dataType", "regimeaccess");
        return "get_table_data";
    }

    @RequestMapping(value = "/admin/table_resource")
    public String getResourceData(ModelMap modelMap) {
        modelMap.addAttribute("data", resourceService.getAll());
        modelMap.addAttribute("dataType", "resource");
        return "get_table_data";
    }

    @RequestMapping(value = "/admin/table_sector")
    public String getSectorData(ModelMap modelMap) {
        modelMap.addAttribute("data", sectorService.getAll());
        modelMap.addAttribute("dataType", "sector");
        return "get_table_data";
    }

    @RequestMapping(value = "/admin/table_subresource")
    public String getSubResourceData(ModelMap modelMap) {
        modelMap.addAttribute("data", subResourceService.getAll());
        modelMap.addAttribute("dataType", "subresource");
        return "get_table_data";
    }

    @RequestMapping(value = "/admin/table_zues")
    public String getZuesData(ModelMap modelMap) {
        modelMap.addAttribute("data", zuesService.getAll());
        modelMap.addAttribute("dataType", "zues");
        return "get_table_data";
    }
}
