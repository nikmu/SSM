package com.ssm.rest.demo.service;

import java.util.List;

import com.ssm.rest.demo.entity.Organization;

public interface IOrganizationService {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
