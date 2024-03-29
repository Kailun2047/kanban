package gatech.edu.ppmtool.web;

import gatech.edu.ppmtool.domain.Project;
import gatech.edu.ppmtool.services.FormValidationService;
import gatech.edu.ppmtool.services.ProjectService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private FormValidationService formValidationService;

    @PostMapping(value = "")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<?> resp = formValidationService.validate(bindingResult);
        if (resp != null) {
            return resp;
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        projectService.saveOrUpdateProject(project, username);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{projectId}")
    public ResponseEntity<?> getProject(@PathVariable String projectId, Principal principal) {
        Project proj = projectService.readProjectByProjectId(projectId.toUpperCase(), principal);
        return new ResponseEntity<>(proj, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public Iterable<Project> getAllProjects(Principal principal) {
        Iterable<Project> projects = projectService.readAllProjects(principal);
        return projects;
    }

    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId, Principal principal) {
        projectService.deleteProjectByProjectId(projectId, principal);
        return new ResponseEntity<>("Project '" + projectId + "' deleted successfully", HttpStatus.OK);
    }
}
