package co.simplon.alt3.kisslulerback.services;


import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.alt3.kisslulerback.entites.Consideration;
import co.simplon.alt3.kisslulerback.entites.Project;
import co.simplon.alt3.kisslulerback.entites.UserOrder;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@Service
public class ProjectService {

    @Autowired
    ProjectRepo projectRepo;

    protected double CalculateAllContribution(Project project ) {

        return project.getOrders()
            .stream()
            .mapToDouble(UserOrder::getAmount)
            .sum(); // .map(el -> el.getAmount())
    }

}


