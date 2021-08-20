package vn.seven.stc.masterdata.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.masterdata.models.Project;
import vn.seven.stc.masterdata.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectEndpoint extends CrudApiEndpoint<Project, Long> {
    private ProjectService projectService;
    public ProjectEndpoint(ProjectService service) {
        super(service);
        this.projectService = service;
        this.baseUrl = "/api/project";
    }
}