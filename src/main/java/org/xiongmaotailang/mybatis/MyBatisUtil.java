package org.xiongmaotailang.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

/**
 * 
 * @author xiongmaotailang
 *
 */
public class MyBatisUtil {
	public final static Logger logger = Logger.getLogger(MyBatisUtil.class);

	/**
	 * 默认获取测试数据源
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return getSqlSessionFactory(DataSourceEnvironment.test);
	}

	private static final Map<DataSourceEnvironment, SqlSessionFactory> SQLSESSIONFACTORYS = new HashMap<DataSourceEnvironment, SqlSessionFactory>();

	/**
	 * 根据指定的DataSourceEnvironment获取对应的SqlSessionFactory
	 * 
	 * @param environment
	 *            数据源environment
	 * @return SqlSessionFactory
	 */
	public static SqlSessionFactory getSqlSessionFactory(
			DataSourceEnvironment environment) {

		SqlSessionFactory sqlSessionFactory = SQLSESSIONFACTORYS
				.get(environment);
		if (null != sqlSessionFactory)
			return sqlSessionFactory;
		else {
			InputStream inputStream = null;
			try {
				String resource = "org/xiongmaotailang/mybatis/mybatis-config.xml";
				inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(
						inputStream, environment.name());

				logger.info("Get " + environment.name()
						+ " SqlSessionFactory successfully.");
			} catch (IOException e) {
				logger.warn("Get " + environment.name()
						+ " SqlSessionFactory error.");
				logger.error(e.getMessage(), e);
			} finally {
				IOUtils.closeQuietly(inputStream);
			}

			SQLSESSIONFACTORYS.put(environment, sqlSessionFactory);
			return sqlSessionFactory;
		}
	}

	/**
	 * 配置到mybatis-config.xml文件中的数据源的environment的枚举描述
	 */
	public static enum DataSourceEnvironment {
		test, userTest, chuping, weixin,pay;
	}
}
