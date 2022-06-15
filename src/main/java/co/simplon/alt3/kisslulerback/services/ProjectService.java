package co.simplon.alt3.kisslulerback.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@Service
public class ProjectService {

    @Autowired
    ProjectRepo projectRepo;

    protected double CalculateAllContribution(Project project ) {
        return 0;

    }

}


