package com.ssm.demo.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.demo.dao.system.IOrganizationDao;
import com.ssm.demo.entity.system.Organization;
import com.ssm.demo.service.system.IOrganizationService;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

	@Autowired
	private IOrganizationDao organizationDao;
	
	@Override
	public Organization createOrganization(Organization organization) {
		organizationDao.insert(organization);
		return organization;
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		organizationDao.updateByPrimaryKey(organization);
		return organization;
	}

	@Override
	public void deleteOrganization(Long organizationId) {
		organizationDao.deleteByPrimaryKey(organizationId);
	}

	@Override
	public Organization findOne(Long organizationId) {
		return organizationDao.selectByPrimaryKey(organizationId);
	}

	@Override
	public List<Organization> findAll() {
		return organizationDao.findAll();
	}

	@Override
	public Object findAllWithExclude(Organization excludeOraganization) {
		return organizationDao.findAllWithExclude(excludeOraganization, excludeOraganization.makeSelfAsParentIds());
	}

	@Override
	public void move(Organization source, Organization target) {
		Organization tmp = new Organization();
		tmp.setId(source.getId());
		tmp.setParentId(target.getId());
		tmp.setParentIds(target.makeSelfAsParentIds());
		organizationDao.updateByPrimaryKeySelective(tmp);
	}

}
