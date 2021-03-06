package com.x.organization.assemble.control.alpha.jaxrs.departmentattribute;

import com.x.base.core.cache.ApplicationCache;
import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.entity.annotation.CheckPersistType;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.http.WrapOutId;
import com.x.organization.assemble.control.alpha.Business;
import com.x.organization.assemble.control.alpha.wrapin.WrapInDepartmentAttribute;
import com.x.organization.core.entity.Department;
import com.x.organization.core.entity.DepartmentAttribute;

public class ActionCreate extends ActionBase {

	protected WrapOutId execute(Business business, EffectivePerson effectivePerson, WrapInDepartmentAttribute wrapIn)
			throws Exception {
		EntityManagerContainer emc = business.entityManagerContainer();
		DepartmentAttribute o = inCopier.copy(wrapIn);
		Department department = emc.find(o.getDepartment(), Department.class);
		if (!business.companyEditAvailable(effectivePerson, department.getCompany())) {
			throw new Exception("person{name:" + effectivePerson.getName() + "} has sufficient permissions");
		}
		emc.beginTransaction(DepartmentAttribute.class);
		emc.persist(o, CheckPersistType.all);
		emc.commit();
		ApplicationCache.notify(DepartmentAttribute.class);
		WrapOutId wrap = new WrapOutId(o.getId());
		return wrap;
	}

}
