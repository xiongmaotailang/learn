package org.xiongmaotailang.mybatis.batchinsert;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.xiongmaotailang.mybatis.MyBatisUtil;
import org.xiongmaotailang.mybatis.MyBatisUtil.DataSourceEnvironment;
import org.xiongmaotailang.mybatis.batchinsert.mappers.DataMapper;
import org.xiongmaotailang.mybatis.batchinsert.model.NewsPvUv;
/**
 * 
 * @author xiongmaotailang
 *
 */
public class DbUtil {
    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory(DataSourceEnvironment.test);
    }

    public static void main(String[] args) throws InterruptedException {
    	testInsert();
    	testBatchInsert();
    }
	private static void testInsert() {
		long start=System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			addPvUv(12,i,"123");
		}
    	System.out.println("insert 1000 row :"+(System.currentTimeMillis()-start)+"ms");
	}
	private static void testBatchInsert() {
		long start=System.currentTimeMillis();
    	List<NewsPvUv> list=new ArrayList<NewsPvUv>();
		for (int i = 0; i < 1000; i++) {
			NewsPvUv n = new NewsPvUv(12, i, "123");
			list.add(n);
		}
    	addPvUv(list);
    	System.out.println("batch insert 1000 row :"+(System.currentTimeMillis()-start)+"ms");
	}

    public static void addPvUv(int pv, int uv, String time) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
        	DataMapper userMapper = sqlSession.getMapper(DataMapper.class);
            userMapper.insertPVUV("newspvuv",pv, uv,time);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    public static void addPvUv(List<NewsPvUv> list) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
        	DataMapper userMapper = sqlSession.getMapper(DataMapper.class);
            userMapper.batchInsertPVUV("newspvuv",list);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}