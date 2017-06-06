package by.grsu.controller.data;

import by.grsu.controller.OperationsHistoryController;
import by.grsu.entity.*;
import by.grsu.entity.validator.*;
import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SaveUpdateTableDataController {

    @Autowired
    private DivisionServiceImpl divisionService;

    @Autowired
    private DivisionValidator divisionValidator;

    @Autowired
    private EmployerServiceImpl employerService;

    @Autowired
    private EmployerValidator employerValidator;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private PostValidator postValidator;

    @Autowired
    private RegimeAccessServiceImpl regimeAccessService;

    @Autowired
    private RegimeAccessValidator regimeAccessValidator;

    @Autowired
    private RegionServiceImpl regionService;

    @Autowired
    private ResourceServiceImpl resourceService;

    @Autowired
    private ResourceValidator resourceValidator;

    @Autowired
    private SectorServiceImpl sectorService;

    @Autowired
    private SectorValidator sectorValidator;

    @Autowired
    private SubResourceServiceImp subResourceService;

    @Autowired
    private SubResourceValidator subResourceValidator;

    @Autowired
    private ZuesServiceImpl zuesService;

    @Autowired
    private ZuesValidator zuesValidator;

    @Autowired
    private OperationsHistoryController operationsHistory;

    @RequestMapping(value = "/admin/table_division_add", method = RequestMethod.POST)
    public String addUpdateDivisionData(@ModelAttribute("data") Division data, BindingResult result) {
        divisionValidator.validate(data, result);
        if (result.hasErrors()) {
            return "";
        }
        if (data.getId() == 0) {
            divisionService.save(data);
            operationsHistory.saveOperation("Добавлен отдел " + data.getName());
            return "redirect:/admin/table_division_edit/0";
        }
        divisionService.save(data);
        operationsHistory.saveOperation("Изменена информация отдела " + data.getName()+"(id: " + data.getId() +")");
        return "redirect:/admin/table_division_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_employer_add", method = RequestMethod.POST)
    public String addUpdateEmployerData(@ModelAttribute("data") Employer data, BindingResult result) {
        employerValidator.validate(data, result);
        if (result.hasErrors()) {
            return "";
        }
        String fio = data.getSurname() + " " + data.getName().toUpperCase().charAt(0)
                + "." + data.getMiddlename().toUpperCase().charAt(0) + ".";
        data.setFio(fio);
        Post post = postService.getById(data.getPost_id().getId());
        Zues zues = zuesService.getById(data.getZues_id().getId());
        Division division = divisionService.getById(data.getDivision_id().getId());
        Sector sector = sectorService.getById(data.getSector_id().getId());
        data.setPost_id(post);
        data.setZues_id(zues);
        data.setDivision_id(division);
        data.setSector_id(sector);
        if (data.getId() == 0) {
            employerService.save(data);
            operationsHistory.saveOperation("Добавлен сотрудник " + data.getFio());
            return "redirect:/admin/table_employer_edit/0";
        }
        employerService.save(data);
        operationsHistory.saveOperation("Изменена информация о сотруднике " + data.getFio()+"(id: " + data.getId() +")");
        return "redirect:/admin/table_employer_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_post_add", method = RequestMethod.POST)
    public String addUpdatePostData(@ModelAttribute("data") Post data, BindingResult result) {
        postValidator.validate(data, result);
        if (result.hasErrors()) {
            return "";
        }
        if (data.getId() == 0) {
            postService.save(data);
            operationsHistory.saveOperation("Добавлена должность " + data.getName());
            return "redirect:/admin/table_post_edit/0";
        }
        String oldName = postService.getById(data.getId()).getName();
        postService.save(data);
        operationsHistory.saveOperation("Изменено название должности " + oldName +"(id: " + data.getId() +") на " + data.getName());
        return "redirect:/admin/table_post_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_regimeaccess_add", method = RequestMethod.POST)
    public String addUpdateRegimeAccessData(@ModelAttribute("data") RegimeAccess data, BindingResult result) {
        regimeAccessValidator.validate(data, result);
        if (result.hasErrors()) {
            return "";
        }
        if (data.getId() == 0) {
            regimeAccessService.save(data);
            operationsHistory.saveOperation("Добавлен режим доступа " + data.getName());
            return "redirect:/admin/table_regimeaccess_edit/0";
        }
        String oldName = regimeAccessService.getById(data.getId()).getName();
        regimeAccessService.save(data);
        operationsHistory.saveOperation("Изменено название режима доступа " + oldName +"(id: " + data.getId() +") на " + data.getName());
        return "redirect:/admin/table_regimeaccess_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_resource_add", method = RequestMethod.POST)
    public String addUpdateResourceData(@ModelAttribute("data") Resource data, BindingResult result) {
        resourceValidator.validate(data, result);
        if (result.hasErrors()) {
            return "";
        }
        if (data.getId() == 0) {
            resourceService.save(data);
            operationsHistory.saveOperation("Добавлен ресурс " + data.getName());
            return "redirect:/admin/table_resource_edit/0";
        }
        String oldName = resourceService.getById(data.getId()).getName();
        resourceService.save(data);
        operationsHistory.saveOperation("Изменено название ресурса " + oldName +"(id: " + data.getId() +") на " + data.getName());
        return "redirect:/admin/table_resource_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_sector_add", method = RequestMethod.POST)
    public String addUpdateSectorData(@ModelAttribute("data") Sector data, BindingResult result) {
        sectorValidator.validate(data, result);
        if (result.hasErrors()) {
            return "";
        }
        Division d = divisionService.getById(data.getDivision_id().getId());
        data.setDivision_id(d);
        if (data.getId() == 0) {
            sectorService.save(data);
            operationsHistory.saveOperation("Добавлен сектор " + data.getName());
            return "redirect:/admin/table_sector_edit/0";
        }
        sectorService.save(data);
        operationsHistory.saveOperation("Изменена информация о секторе " + data.getName()+"(id: " + data.getId() +")");
        return "redirect:/admin/table_sector_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_subresource_add", method = RequestMethod.POST)
    public String addUpdateSubResourceData(@ModelAttribute("data") SubResource data, BindingResult result) {
        subResourceValidator.validate(data, result);
        if (result.hasErrors()) {
            return "";
        }
        Resource resource = resourceService.getById(data.getResource_id().getId());
        data.setResource_id(resource);
        if (data.getId() == 0) {
            subResourceService.save(data);
            operationsHistory.saveOperation("Добавлен подресурс " + data.getName());
            return "redirect:/admin/table_subresource_edit/0";
        }
        subResourceService.save(data);
        operationsHistory.saveOperation("Изменена информация о подресурсе " + data.getName()+"(id: " + data.getId() +")");
        return "redirect:/admin/table_subresource_edit/" + data.getId();
    }

    @RequestMapping(value = "/admin/table_zues_add", method = RequestMethod.POST)
    public String addUpdateZuesData(@ModelAttribute("data") Zues data, BindingResult result) {
        zuesValidator.validate(data, result);
        if (result.hasErrors()) {
            return "";
        }
        Region region = regionService.getById(data.getRegion_id().getId());
        data.setRegion_id(region);
        if (data.getId() == 0) {
            zuesService.save(data);
            operationsHistory.saveOperation("Добавлен ЗУЕС " + data.getName());
            return "redirect:/admin/table_zues_edit/0";
        }
        zuesService.save(data);
        operationsHistory.saveOperation("Изменена информация о ЗУЕСе " + data.getName()+"(id: " + data.getId() +")");
        return "redirect:/admin/table_zues_edit/" + data.getId();
    }

}
