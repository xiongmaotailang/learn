package org.xiongmaotailang.mybatis.batchinsert.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.xiongmaotailang.mybatis.batchinsert.model.NewsPvUv;

/**
 * 
 * @author xiongmaotailang
 *
 */
public interface DataMapper {
	 public void insertPVUV(@Param("table") String table,@Param("pv") int pv,@Param("uv") int uv,@Param("time") String time);
	 public void batchInsertPVUV(@Param("table") String table,@Param("list") List<NewsPvUv> list);

}
