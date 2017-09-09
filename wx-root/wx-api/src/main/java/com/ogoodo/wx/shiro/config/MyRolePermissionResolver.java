package com.ogoodo.wx.shiro.config;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;


public class MyRolePermissionResolver implements RolePermissionResolver {

	/**
	 * MyAnyPermissionsAuthorizationFilter.isAccessAllowed调用会进来
	 */
	@Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if ("admin".equals(roleString)) {
            return Arrays.asList(((Permission) new WildcardPermission("admin:*")));
        }
        return null;
    }

}
