package com.framework.dao;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import oracle.jdbc.driver.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.springframework.jdbc.support.nativejdbc.C3P0NativeJdbcExtractor;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.common.util.DateUtil;
import com.framework.exceptions.ServiceException;


/**
 * 
 * File name：ExtHibernateDaoSupport
 * Date: 2012-4-6 汇环
 * Description：hibernate助手类，用于分页查询，SQL操作等 文件名稱：ExtHibernateDaoSupport
 * Modify History:
 */
public class ExtHibernateDaoSupport extends HibernateDaoSupport {


	/**
	 * HQL查询方法，用于多表的HQL查询
	 * 
	 * @param hql
	 * @return
	 */
	public List findByHQL(final String hql) {
		List list = getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 对象保存操作
	 * Function Name :
	 * @param obj
	 * Description : 
	 * Modification History :
	 */
	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}
	
	/**
	 * 对象更新操作
	 * Function Name: update
	 * @param obj
	 */
	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	/**
	 * HQL查询方法，用于带参数的查询 Function Name: findByHQL
	 * 
	 * @param hql
	 * @param value
	 * @return description: Modification History:
	 */
	public List findByHQL(final String hql, final Object[] values) {
		// 通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行Hibernate
				Query query = session.createQuery(hql);
				if (values != null) {
					// 为hql语句传入参数
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				List result=null;
				try {
					result = query.list();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return result;
			}
		});
		return list;

	}

	/**
	 * 使用hql语句进行分页查询
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param offset
	 *            第一条记录索引
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final int offset, final int pageSize) {
		List list = findByPage(hql, null, offset, pageSize);
		return list;
	}

	/**
	 * 使用hql语句进行分页查询
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param value
	 *            如果hql有一个参数需要传入，value就是传入hql语句的参数
	 * @param offset
	 *            第一条记录索引
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final Object value, final int offset, final int pageSize) {
		Object[] values = null;
		if (value != null) {
			values = new Object[] { value };
		}
		List list = findByPage(hql, values, offset, pageSize);
		return list;
	}

	/**
	 * 使用hql语句进行分页查询
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param values
	 *            如果hql有多个个参数需要传入，values就是传入hql的参数数组
	 * @param offset
	 *            第一条记录索引
	 * @return 当前页的所有记录
	 */
	public List findByPage(final String hql, final Object[] values,
			final int offset, final int pageSize) {
		// 通过一个HibernateCallback对象来执行查询
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// 执行Hibernate分页查询
				Query query = session.createQuery(hql);
				if (values != null) {
					// 为hql语句传入参数
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				if (offset >= 0) {
					query.setFirstResult(offset).setMaxResults(pageSize);
				}
				List result = query.list();
				return result;
			}
		});
		return list;
	}

	/**
	 * 使用hql语句查询记录总数
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @return 记录总数
	 */
	public int getTotalSize(final String hql) {
		int totalSize = getTotalSize(hql, null);
		return totalSize;
	}

	/**
	 * 使用hql语句查询记录总数
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param value
	 *            如果hql有一个参数需要传入，value就是传入hql语句的参数
	 * @return 记录总数
	 */
	public int getTotalSize(String hql, Object value) {
		Object[] values = null;
		if (value != null) {
			values = new Object[] { value };
		}
		int totalSize = getTotalSize(hql, values);
		return totalSize;
	}

	/**
	 * 使用hql语句查询记录总数
	 * 
	 * @param hql
	 *            需要查询的hql语句
	 * @param values
	 *            如果hql有多个个参数需要传入，values就是传入hql的参数数组
	 * @return 记录总数
	 */
	public int getTotalSize(final String hql, final Object[] values) {
		// 通过一个HibernateCallback对象来执行查询
		List totalSize = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					// 实现HibernateCallback接口必须实现的方法
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hsql = hql;
						if (!hsql.toLowerCase().startsWith("select")) {
							hsql = "select count(*) " + hsql;
						}
						// 执行Hibernate分页查询
						Query query = session.createQuery(hsql);
						// 为hql语句传入参数
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						List result = query.list();
						return result;
					}
				});
		return Integer.parseInt(totalSize.get(0).toString());
	}

	/**
	 * 
	 * Function Name: executeHQL
	 * @param hql
	 * @param values
	 * @return
	 * description:执行带参数的HQL语句
	 * Modification History:
	 */
	public int executeHQL(final String hql, final Object[] values) {
		int ret = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// 执行Hibernate
				Query query = session.createQuery(hql);
				if (values != null) {
					// 为hql语句传入参数
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.executeUpdate();
			}
		});
		return ret;
		
	}

	/**
	 * 
	 * Function Name: executeSQL
	 * @param sql
	 * @param values
	 * @return
	 * description:执行带参数的SQL语句
	 * Modification History:
	 */
	public int executeSQL(final String sql, final Object[] values) {
		int ret = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// 执行Hibernate
				SQLQuery query = session.createSQLQuery(sql);
				if (values != null) {
					// 为hql语句传入参数
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.executeUpdate();
			}
		});
		return ret;
		
	}

	/**
	 * 
	 * Function Name: executeSQL
	 * @param sql
	 * @param values
	 * @return
	 * description:执行带参数的SQL语句
	 * Modification History:
	 */
	public int executeLockSQL(final String sql, final Object[] values) {
		int ret = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.setFlushMode(FlushMode.AUTO);
				// 执行Hibernate
				SQLQuery query = session.createSQLQuery(sql);
				if (values != null) {
					// 为hql语句传入参数
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.executeUpdate();
			}
		});
		return ret;
	}
	
	/**
	 * 
	 * Function Name: executeSQL
	 * @param sql
	 * description:执行不带参数SQL或存储过程
	 * Modification History:
	 */
	public void executeSQL(final String sql) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				CallableStatement cs = null;
				try{
					cs = session.connection().prepareCall(sql);
					cs.execute();
				}catch (Exception e) {
					// TODO: handle exception
				}finally{
					if(null!=cs)
						cs.close();
				}
				return "";
			}
		});
	}
	
	/**
	 * Function Name: executeSQL
	 * @param sql
	 * @param outNum
	 * description:执行不带参数SQL或存储过程并返回指定值
	 * Modification History:
	 */
	public long executeSQLAndRetData(final String sql) {
		Object objNum=getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws SQLException {
				CallableStatement cs = null;
				long rstNum=0l;
				try{
					cs = session.connection().prepareCall(sql);
					cs.registerOutParameter(1, Types.INTEGER);
					cs.execute();
					rstNum=cs.getLong(1);
				}catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}finally{
					if(null!=cs)
						cs.close();
				}
				return rstNum;
			}
		});
		return Long.valueOf(objNum.toString());
	}

	/**
	 * 
	 * Function Name: findBySQL
	 * @param sql
	 * @param values
	 * @return
	 * description:带参数不分页的SQL
	 * Modification History:
	 */
	public List findBySQL(final String sql, final Object[] values) {
		
		List ret = getHibernateTemplate().executeFind(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// 执行Hibernate
				SQLQuery query = session.createSQLQuery(sql);
				if (values != null) {
					// 为hql语句传入参数
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return query.list();
			}
		});
		return ret;
		
	}

	/**
	 * 
	 * Function Name: findByPageForSQL
	 * @param sql
	 * @param values 参数值
	 * @param offset 起始位置，若查询全部则传入-1
	 * @param pageSize 每页记录数
	 * @return
	 * description: 执行带参数带分页的SQL
	 * Modification History:
	 */
	public List findByPageForSQL(final String sql, final Object[] values,
			final int offset, final int pageSize) {
		List ret = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						if(values[i] instanceof Date)
							query.setDate(i, (Date)values[i]);
						else
							query.setParameter(i, values[i]);
					}
				}
				if (offset >= 0) {
					query.setFirstResult(offset).setMaxResults(pageSize);
				}
				return query.list();
			}
		});
		return ret;
	}
	
	/**
	 * 
	 * Function Name: getTotalSizeForSQL
	 * @param sql
	 * @param values
	 * @return
	 * description:执行带参数的SQL统计记录总数
	 * Modification History:
	 */
	public int getTotalSizeForSQL(final String sql, final Object[] values) {
		// 通过一个HibernateCallback对象来执行查询
		List totalSize = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					// 实现HibernateCallback接口必须实现的方法
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// 执行Hibernate分页查询
						Query query = session.createSQLQuery(sql);
						// 为hql语句传入参数
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						return query.list();
					}
				});
		if (null != totalSize && totalSize.size() > 0)
			return totalSize.size();
		else
			return 0;
	}
	
	/**
	 * 执行非查询类SQL语句
	 * @param cmd
	 * @return
	 */
	public int executeSqlCmd(String cmd)
	{
		int ret = 0;
		CallableStatement pstmt = null;
		Session session = null;
		Connection conn = null;
		try{
			session = this.getSession();
			conn = session.connection();
			pstmt = conn.prepareCall(cmd);
			ret = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.releaseSession(session);
		}
		return ret;
	}
	
	/**
	 * 执行存储过程的方法
	 * @param excutorSql
	 * @param parameter
	 * @param resultnum
	 * @return
	 */
	public String[] callProcedure(String excutorSql, String[] parameter, int resultnum) {
		String[] returnExec = null;
		CallableStatement pstmt = null;
		Session session = this.getSession();
		Connection conn = session.connection();
		try {
			String sql = "{call " + excutorSql + "(";
			for (int i = 0; i < parameter.length; i++) {
				sql += "?,";
			}
			for (int j = 0; j < resultnum; j++) {
				if (j == resultnum - 1) {
					sql += "?";
					continue;
				}
				sql += "?,";
			}
			sql +=")}";
			pstmt = conn.prepareCall(sql);

			for (int i = 0; i < parameter.length; i++) {
				pstmt.setString(i + 1, parameter[i]);
			}
			int len = parameter.length  + resultnum;
			for (int j=parameter.length;j < len; j++) {
				pstmt.registerOutParameter(j+1, java.sql.Types.VARCHAR);
			}
			
			pstmt.executeUpdate();
			returnExec = new String[resultnum];
			for (int i=0;i<resultnum;i++){
				returnExec[i]=pstmt.getString(i+parameter.length+1);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.getMessage();
		}finally{
			parameter = null;
			this.releaseSession(session);
		}
		return returnExec;
	}
	
	/**
	 * 调用复杂结构存储过程，输入一维数组，无返回值
	 * @param sql {call P_PROCEDURE1(?)}
	 * @param type Oracle数据库中定义的type名称
	 * @param values 传入参数一维数组
	 */
	public void callProcedureNOReturn(final String sql, final String type, final String[] values){
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				CallableStatement cs = null;
				try{
					Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
					
					C3P0NativeJdbcExtractor c3p0 = new C3P0NativeJdbcExtractor();
					conn = c3p0.getNativeConnection(conn);
					
					ArrayDescriptor arrayDept = ArrayDescriptor.createDescriptor(type, conn);
					ARRAY deptArrayObject = new ARRAY(arrayDept, conn, values);
					
					cs = conn.prepareCall(sql);
					//输入结构
					cs.setObject(1, deptArrayObject);
					cs.execute();
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(null!=cs)
						cs.close();
				}
				return null;
			}
			
		});
	}
	
	/**
	 * 调用复杂结构存储过程，输入一维数组，返回二维表
	 * @param sql {call P_PROCEDURE1(?,?)}
	 * @param type Oracle数据库中定义的type名称
	 * @param values 传入参数一维数组
	 * @return 二维表
	 */
	public List callProcedureHaveReturn(final String sql, final String type, final String[] values){
		
		List returnVal = null;
		returnVal = (List)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				List result = new ArrayList(0);
				CallableStatement cs = null;
				try{
					Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
					
					C3P0NativeJdbcExtractor c3p0 = new C3P0NativeJdbcExtractor();
					conn = c3p0.getNativeConnection(conn);
					
					ArrayDescriptor arrayDept = ArrayDescriptor.createDescriptor(type, conn);
					ARRAY deptArrayObject = new ARRAY(arrayDept, conn, values);
					
					cs = conn.prepareCall(sql);
					//输入结构
					cs.setObject(1, deptArrayObject);
					
					//输出指针
					cs.registerOutParameter(2, OracleTypes.CURSOR);
					cs.execute();
					
					//返回结果集
					ResultSet rs = (ResultSet)cs.getObject(2);
					//返回列名
					ResultSetMetaData rsmd = rs.getMetaData();
					String[] colName = new String[rsmd.getColumnCount()];
					for(int i=1;i<=rsmd.getColumnCount();i++){
						colName[i-1] = rsmd.getColumnName(i);
					}
					
					//根据列名取返回值（可以使用反射机制将返回值组装为对象）
					while(rs.next()){
						Map tmp = new HashMap();
						for(int i=0;i<colName.length;i++){
							tmp.put(colName[i], rs.getString(colName[i]));
						}
						result.add(tmp);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(null!=cs)
						cs.close();
				}
				return result;
			}
		});
		return returnVal;
		
	}
	
	/**
	 * 调用复杂结构存储过程，输入二维数组，返回二维表
	 * @param sql {call P_PROCEDURE1(?,?)}
	 * @param type Oracle数据库中定义的type名称
	 * @param values 传入参数二维数组
	 * @return 二维表
	 */
	public List callProcedureHaveReturn(final String sql, final String type, final String[][] values){
		
		List returnVal = null;
		returnVal = (List)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
				List result = new ArrayList(0);
				CallableStatement cs = null;
				try{
					Connection conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
					
					C3P0NativeJdbcExtractor c3p0 = new C3P0NativeJdbcExtractor();
					conn = c3p0.getNativeConnection(conn);
					
					ArrayDescriptor arrayDept = ArrayDescriptor.createDescriptor(type, conn);
					ARRAY deptArrayObject = new ARRAY(arrayDept, conn, values);
					
					cs = conn.prepareCall(sql);
					//输入结构
					cs.setObject(1, deptArrayObject);
					
					//输出指针
					cs.registerOutParameter(2, OracleTypes.CURSOR);
					cs.execute();
					
					//返回结果集
					ResultSet rs = (ResultSet)cs.getObject(2);
					//返回列名
					ResultSetMetaData rsmd = rs.getMetaData();
					String[] colName = new String[rsmd.getColumnCount()];
					for(int i=1;i<=rsmd.getColumnCount();i++){
						colName[i-1] = rsmd.getColumnName(i);
					}
					
					//根据列名取返回值（可以使用反射机制将返回值组装为对象）
					while(rs.next()){
						Map tmp = new HashMap();
						for(int i=0;i<colName.length;i++){
							tmp.put(colName[i], rs.getString(colName[i]));
						}
						result.add(tmp);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(null!=cs)
						cs.close();
				}
				return result;
			}
		});
		return returnVal;
		
	}
	
	public void flush(){
		getSession().flush();
	}
	
	public void clear(){
		getSession().clear();
	}
	
	/**
	 * 通过sql方式获取信息后，封装为指定的VO对象集合
	 * Function Name: findBySQLForVO
	 * @param sql
	 * @param classes 指定VO
	 * @param values 传入参数值
	 * @return 返回指定VO集合
	 * @throws ServiceException
	 */
	public List findBySQLForVO(final String sql, final Class classes, final Object[] values) {
		
		List ret = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				try{
					SQLQuery query = session.createSQLQuery(sql);
					if (values != null) {
						// 为hql语句传入参数
						for (int i = 0; i < values.length; i++) {
							query.setParameter(i, values[i]);
						}
					}
					for(Field f : classes.getDeclaredFields()){
						if(Pattern.compile("as\\s+"+f.getName()+"[\\s|,]+").matcher(sql).find()){
							AbstractSingleColumnStandardBasicType type = (AbstractSingleColumnStandardBasicType)Class.forName("org.hibernate.type."+f.getType().getSimpleName()+"Type").newInstance();
							query.addScalar(f.getName(), type);
						}
					}
					query.setResultTransformer(new AliasToBeanResultTransformer(classes));
					return query.list();
				}catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		return ret;
	}
	
	/**
	 * 
	 * Function Name: findByPageForSQL
	 * @param sql
	 * @param values 参数值
	 * @param offset 起始位置，若查询全部则传入-1
	 * @param pageSize 每页记录数
	 * @return
	 * description: 执行带参数带分页的SQL
	 * Modification History:
	 */
	public List findBySQLForVO(final String sql, final Class classes, final Object[] values, final int offset, final int pageSize) {
		
		List ret = getHibernateTemplate().executeFind(new HibernateCallback() {
			// 实现HibernateCallback接口必须实现的方法
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// 执行Hibernate
				try{
					SQLQuery query = session.createSQLQuery(sql);
					if (values != null) {
						// 为hql语句传入参数
						for (int i = 0; i < values.length; i++) {
							query.setParameter(i, values[i]);
						}
					}
					
					for(Field f : classes.getSuperclass().getDeclaredFields()){
						if(Pattern.compile("as\\s+"+f.getName()+"[\\s|,]+").matcher(sql).find()){
							AbstractSingleColumnStandardBasicType type = (AbstractSingleColumnStandardBasicType)Class.forName("org.hibernate.type."+f.getType().getSimpleName()+"Type").newInstance();
							query.addScalar(f.getName(), type);
						}
					}
					
					for(Field f : classes.getDeclaredFields()){
						if(Pattern.compile("as\\s+"+f.getName()+"[\\s|,]+").matcher(sql).find()){
							AbstractSingleColumnStandardBasicType type = (AbstractSingleColumnStandardBasicType)Class.forName("org.hibernate.type."+f.getType().getSimpleName()+"Type").newInstance();
							query.addScalar(f.getName(), type);
						}
					}
					query.setResultTransformer(new AliasToBeanResultTransformer(classes));
					if (offset >= 0) {
						query.setFirstResult(offset).setMaxResults(pageSize);
					}
					return query.list();
				}catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		});
		return ret;
	}
}