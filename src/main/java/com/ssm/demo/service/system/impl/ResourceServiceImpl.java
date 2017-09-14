package com.ssm.demo.service.system.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.demo.common.utils.StringUtil;
import com.ssm.demo.dao.system.IResourceDao;
import com.ssm.demo.entity.system.Resource;
import com.ssm.demo.service.system.IResourceService;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;
	
	@Override
	public Resource createResource(Resource resource) {
		resourceDao.insert(resource);
		return resource;
	}

	@Override
	public Resource updateResource(Resource resource) {
		resourceDao.updateByPrimaryKey(resource);
		return null;
	}

	@Override
	public void deleteResource(Long resourceId) {
		resourceDao.deleteByPrimaryKey(resourceId);
	}

	@Override
	public Resource findOne(Long resourceId) {
		return resourceDao.selectByPrimaryKey(resourceId);
	}

	@Override
	public List<Resource> findAll() {
		return resourceDao.findAll();
	}

	@Override
	public Set<String> findPermissions(Set<Long> resourceIds) {
		Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            Resource resource = findOne(resourceId);
            if(resource != null && !StringUtil.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
	}

	@Override
	public List<Resource> findMenus(Set<String> permissions) {
		List<Resource> allResources = findAll();
		List<Resource> menus = new ArrayList<Resource>();
        for(Resource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(resource.getType() != Resource.ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
            	continue;
            }
            menus.add(resource);
        }
        return menus;
	}

	private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtil.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
