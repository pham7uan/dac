package vn.seven.stc.masterdata.service;

import org.springframework.stereotype.Service;
import vn.seven.stc.core.CrudService;
import vn.seven.stc.masterdata.models.Project;
import vn.seven.stc.masterdata.repositories.ProjectRepository;

@Service
public class ProjectService extends CrudService<Project, Long> {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.repository = this.projectRepository = projectRepository;
    }
}