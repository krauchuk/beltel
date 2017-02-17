package by.grsu.controller.data;

import by.grsu.entity.*;
import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SaveUpdateTableDataController {

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

    @RequestMapping(value = "/admin/table_division_add", method = RequestMethod.POST)
    public String addUpdateDivisionData(@ModelAttribute("data") Division data) {
        Employer employer = employerService.getById(data.getDivhead_id().getId());
        data.setDivhead_id(employer);
        if (data.getId() == 0) {
            divisionService.save(data);
            return "redirect:/admin/table_division_edit/0";
        }
        divisionService.save(data);
        return "redirect:/admin/table_division_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_employer_add", method = RequestMethod.POST)
    public String addUpdateEmployerData(@ModelAttribute("data") Employer data) {
        Post post = postService.getById(data.getPost_id().getId());
        Zues zues = zuesService.getById(data.getZues_id().getId());
        Sector sector = sectorService.getById(data.getSector_id().getId());
        data.setPost_id(post);
        data.setZues_id(zues);
        data.setSector_id(sector);
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
        Resource resource = resourceService.getById(data.getResource_id().getId());
        data.setResource_id(resource);
        if (data.getId() == 0) {
            subResourceService.save(data);
            return "redirect:/admin/table_subresource_edit/0";
        }
        subResourceService.save(data);
        return "redirect:/admin/table_subresource_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_zues_add", method = RequestMethod.POST)
    public String addUpdateZuesData(@ModelAttribute("data") Zues data) {
        Region region = regionService.getById(data.getRegion_id().getId());
        data.setRegion_id(region);
        if (data.getId() == 0) {
            zuesService.save(data);
            return "redirect:/admin/table_zues_edit/0";
        }
        zuesService.save(data);
        return "redirect:/admin/table_zues_edit/" + data.getId();
    }

}
