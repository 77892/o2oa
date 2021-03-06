package com.x.organization.assemble.authentication.factory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.x.organization.assemble.authentication.AbstractFactory;
import com.x.organization.assemble.authentication.Business;
import com.x.organization.core.entity.Person;
import com.x.organization.core.entity.Person_;

public class PersonFactory extends AbstractFactory {

	public PersonFactory(Business business) throws Exception {
		super(business);
	}

	/* "根据给定的Person Name获取Person Id */
	public String getWithName(String name) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Person.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Person> root = cq.from(Person.class);
		Predicate p = cb.equal(root.get(Person_.name), name);
		cq.select(root.get(Person_.id)).where(p);
		List<String> list = em.createQuery(cq).setMaxResults(1).getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/* "根据给定的Person unique获取Person Id */
	public String getWithUnique(String unique) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Person.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Person> root = cq.from(Person.class);
		Predicate p = cb.equal(root.get(Person_.unique), unique);
		cq.select(root.get(Person_.id)).where(p);
		List<String> list = em.createQuery(cq).setMaxResults(1).getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	public String getWithCredential(String credential) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Person.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Person> root = cq.from(Person.class);
		Predicate p = cb.equal(root.get(Person_.name), credential);
		p = cb.or(p, cb.equal(root.get(Person_.mail), credential));
		p = cb.or(p, cb.equal(root.get(Person_.mobile), credential));
		p = cb.or(p, cb.equal(root.get(Person_.employee), credential));
		cq.select(root.get(Person_.id));
		List<String> list = em.createQuery(cq.where(p)).setMaxResults(1).getResultList();
		if (list.isEmpty()) {
			return null;
		} else if (list.size() == 1) {
			return list.get(0);
		} else {
			throw new Exception("not unique credential:" + credential + ".");
		}
	}
}