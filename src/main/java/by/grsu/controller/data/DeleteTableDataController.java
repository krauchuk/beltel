package by.grsu.controller.data;

import by.grsu.controller.OperationsHistoryController;
import by.grsu.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeleteTableDataController {
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

    @Autowired
    private OperationsHistoryController operationsHistory;

    @RequestMapping(value = "/admin/delete_division/{id}")
    public String deleteDivision(@PathVariable long id){
        operationsHistory.saveOperation("Удаление отдела " + divisionService.getById(id).getName() + "(id: " + id + ")");
        divisionService.delete(id);
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/delete_employer/{id}")
    public String deleteEmployer(@PathVariable long id){
        operationsHistory.saveOperation("Удаление сотрудника " + employerService.getById(id).getFio() + "(id: " + id + ")");
        employerService.delete(id);
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/delete_post/{id}")
    public String deletePost(@PathVariable long id){
        operationsHistory.saveOperation("Удаление должности " + postService.getById(id).getName() + "(id: " + id + ")");
        postService.delete(id);
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/delete_regimeaccess/{id}")
    public String deleteRegimeAccess(@PathVariable long id){
        operationsHistory.saveOperation("Удаление режима доступа " + regimeAccessService.getById(id).getName() + "(id: " + id + ")");
        regimeAccessService.delete(id);
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/delete_resource/{id}")
    public String deleteResource(@PathVariable long id){
        operationsHistory.saveOperation("Удаление ресурса " + resourceService.getById(id).getName() + "(id: " + id + ")");
        resourceService.delete(id);
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/delete_sector/{id}")
    public String deleteSector(@PathVariable long id){
        operationsHistory.saveOperation("Удаление сектора " + sectorService.getById(id).getName() + "(id: " + id + ")");
        sectorService.delete(id);
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/delete_subresource/{id}")
    public String deleteSubResource(@PathVariable long id){
        operationsHistory.saveOperation("Удаление подресурса " + subResourceService.getById(id).getName() + "(id: " + id + ")");
        subResourceService.delete(id);
        return "add_edit_data";
    }

    @RequestMapping(value = "/admin/delete_zues/{id}")
    public String deleteZues(@PathVariable long id){
        operationsHistory.saveOperation("Удаление ЗУЕС " + zuesService.getById(id).getName() + "(id: " + id + ")");
        zuesService.delete(id);
        return "add_edit_data";
    }
}
