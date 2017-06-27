package com.pb.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.framework.dao.ExtHibernateDaoSupport;


@Repository("baseDAO")
public class BaseDAO extends ExtHibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BaseDAO.class);
	
	@Autowired  
	public void setMySessionFactory(SessionFactory sessionFactory){  
	    super.setSessionFactory(sessionFactory);  
	}  
	
	public void save(Object transientInstance) {
		log.debug("saving Object instance");
		try {
			getSession().save(transientInstance);
//			getSession().flush();//save直接flush
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Object persistentInstance) {
		log.debug("deleting Object instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void update(Object persistentInstance) {
		try {
			getSession().update(persistentInstance);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	//cls
	public <T> T findById(java.lang.Object id, Class<T> cls) {
		String tableName = cls.getName();
		log.debug("getting "+tableName+" instance with id: " + id);
		try {
			T instance = (T) getSession().get(
//					"com.project.entity.BrchCntrctRcrd", id);
					tableName, (Serializable)id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//cls
	public List findByExample(Object instance, Class cls) {
		String tableName = cls.getName();
		log.debug("finding "+tableName+" instance by example");
		try {
			List results = getSession()
//					.createCriteria("com.project.entity.BrchCntrctRcrd")
					.createCriteria(tableName)
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	//cls
	public List findByProperty(String propertyName, Object value, Class cls) {
		String tableName = cls.getName();
		log.debug("finding "+tableName+" instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from "+tableName+" as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	//cls
	public List findAll(Class cls) {
		String tableName = cls.getName();
		log.debug("finding all "+tableName+" instances");
		try {
			String queryString = "from "+tableName;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public <T>T merge(T detachedInstance) {
		log.debug("merging Object instance");
		try {
			T result = (T) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Object instance) {
		log.debug("attaching dirty Object instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Object instance) {
		log.debug("attaching clean Object instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void saveAuto(final Object instance) {
		log.debug("saving instance");
		try {
//			getHibernateTemplate().execute(new HibernateCallback() {
//				@Override
//				public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
//					return arg0.save(instance);
//				}
//			}) ;
			
			Session session = getSession();
			session.setFlushMode(FlushMode.AUTO);
			session.save(instance);
			session.flush();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
}
