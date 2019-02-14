package com.admin.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.admin.VO.CustomVO;

@Component
public class CustomDaoImpl extends SqlSessionDaoSupport implements CustomDao {
	
	public List<CustomVO> list(Map<String, Object> paramMap){
		return getSqlSession().selectList("customList", paramMap);
	}
	
	public CustomVO getCustomInfo(int cSeq) {
		return getSqlSession().selectOne("getCustomInfo", cSeq);
	}
	
	public void delete(String cId) {
		getSqlSession().delete("delete", cId);
	}
	
	public int getCustomCount(Map<String, Object> paramMap) {
		return ((Integer) getSqlSession().selectOne("getCustomCount", paramMap)).intValue();
	}
}
