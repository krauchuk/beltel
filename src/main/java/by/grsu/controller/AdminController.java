package by.grsu.controller;

import by.grsu.entity.*;
import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class AdminController {

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
    private RepnoteResServiceImpl repnoteResService;

    @Autowired
    private ReportingNoticeServiceImpl reportingNoticeService;

    @Autowired
    private ResourceServiceImpl resourceService;

    @Autowired
    private SectorServiceImpl sectorService;

    @Autowired
    private SubResourceServiceImp subResourceService;

    @Autowired
    private ZuesServiceImpl zuesService;

    @Autowired
    private UsersServiceImp usersService;

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
    public String editDB(ModelMap modelMap) {
        return "edit_db";
    }

    @RequestMapping(value = "/notice/{id}", method = RequestMethod.GET)
    public String findOneReportingNotice(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("reportingNotice", reportingNoticeService.getById(id));
        modelMap.addAttribute("subres", repnoteResService.getByNoticeId(id));
        return "reporting_notice";
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
        ReportingNotice rn = reportingNoticeService.getById(id);
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        rn.setDateDone(date);
        if (rn.isStatus() == false) {
            rn.setStatus(true);
            reportingNoticeService.save(rn);
            //PDF
            return "redirect:/notice/" + id;
        }
        rn.setStatus(false);
        reportingNoticeService.save(rn);
        return "redirect:/notice/" + id;
    }


    //----------get DB tables data----------


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


    //----------edit DB tables data----------


    @RequestMapping(value = "/admin/table_division_edit/{id}")
    public String editDivisionData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("divhead", employerService.getAll());
        modelMap.addAttribute("dataType", "division");
        if (id == 0) {
            modelMap.addAttribute("data", new Division());
            return "add_edit_data";
        }
        modelMap.addAttribute("data", divisionService.getById(id));
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/table_employer_edit/{id}")
    public String editEmployerData(@PathVariable long id, ModelMap modelMap) {
        modelMap.addAttribute("post", postService.getAll());
        modelMap.addAttribute("zues", zuesService.getAll());
        modelMap.addAttribute("sector", sectorService.getAll());
        modelMap.addAttribute("dataType", "employer");
        if (id == 0) {
            modelMap.addAttribute("data", new Employer());
            return "add_edit_data";
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
        modelMap.addAttribute("data", zuesService.getById(id));
        return "add_edit_data";
    }


    //----------add or update DB tables data----------


    @RequestMapping(value = "/admin/table_division_add", method = RequestMethod.POST)
    public String addUpdateDivisionData(@ModelAttribute("data") Division data) {
        Employer emp = employerService.getById(data.getDivhead_id().getId());
        data.setDivhead_id(emp);
        if (data.getId() == 0) {
            divisionService.save(data);
            return "redirect:/admin/table_division_edit/0";
        }
        divisionService.save(data);
        return "redirect:/admin/table_division_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_employer_add", method = RequestMethod.POST)
    public String addUpdateEmployerData(@ModelAttribute("data") Employer data) {
        Post p = postService.getById(data.getPost_id().getId());
        Zues z = zuesService.getById(data.getZues_id().getId());
        Sector s = sectorService.getById(data.getSector_id().getId());
        data.setPost_id(p);
        data.setZues_id(z);
        data.setSector_id(s);
        if (data.getId() == 0) {
            employerService.save(data);
            return "redirect:/admin/table_employer_edit/0";
        }
        employerService.save(data);
        return "redirect:/admin/table_employer_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_post_add", method = RequestMethod.POST)
    public String addUpdatePostData(@ModelAttribute("data") Post data) {
        if (data.getId() == 0) {
            postService.save(data);
            return "redirect:/admin/table_post_edit/0";
        }
        postService.save(data);
        return "redirect:/admin/table_post_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_regimeaccess_add", method = RequestMethod.POST)
    public String addUpdateRegimeAccessData(@ModelAttribute("data") RegimeAccess data) {
        if (data.getId() == 0) {
            regimeAccessService.save(data);
            return "redirect:/admin/table_regimeaccess_edit/0";
        }
        regimeAccessService.save(data);
        return "redirect:/admin/table_regimeaccess_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_resource_add", method = RequestMethod.POST)
    public String addUpdateResourceData(@ModelAttribute("data") Resource data) {
        if (data.getId() == 0) {
            resourceService.save(data);
            return "redirect:/admin/table_resource_edit/0";
        }
        resourceService.save(data);
        return "redirect:/admin/table_resource_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_sector_add", method = RequestMethod.POST)
    public String addUpdateSectorData(@ModelAttribute("data") Sector data) {
        Division d = divisionService.getById(data.getDivision_id().getId());
        data.setDivision_id(d);
        if (data.getId() == 0) {
            sectorService.save(data);
            return "redirect:/admin/table_sector_edit/0";
        }
        sectorService.save(data);
        return "redirect:/admin/table_sector_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_subresource_add", method = RequestMethod.POST)
    public String addUpdateSubResourceData(@ModelAttribute("data") SubResource data) {
        Resource r = resourceService.getById(data.getResource_id().getId());
        data.setResource_id(r);
        if (data.getId() == 0) {
            subResourceService.save(data);
            return "redirect:/admin/table_subresource_edit/0";
        }
        subResourceService.save(data);
        return "redirect:/admin/table_subresource_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_zues_add", method = RequestMethod.POST)
    public String addUpdateZuesData(@ModelAttribute("data") Zues data) {
        Region r = regionService.getById(data.getRegion_id().getId());
        data.setRegion_id(r);
        if (data.getId() == 0) {
            zuesService.save(data);
            return "redirect:/admin/table_zues_edit/0";
        }
        zuesService.save(data);
        return "redirect:/admin/table_zues_edit/" + data.getId();
    }
}
