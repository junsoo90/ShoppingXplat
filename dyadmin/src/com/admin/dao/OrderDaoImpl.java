package com.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.admin.VO.OrderVO;

@Component
public class OrderDaoImpl extends SqlSessionDaoSupport implements OrderDao {
	
	public List<OrderVO> orderList(Map<String, Object> map){
		return getSqlSession().selectList("orderList",map);
	}
	
	
	public HashMap<String, Object> orderInfoBank(OrderVO orderVo) {
		return getSqlSession().selectOne("orderInfoBank", orderVo);
	}

	
	public HashMap<String, Object> orderInfoCard(OrderVO orderVo){
		return getSqlSession().selectOne("orderInfoCard", orderVo);
	}
	
	public String getPayType(int purchaseSeq) {
		return getSqlSession().selectOne("getPayType", purchaseSeq);
	}
	
	public void updateOrderState(OrderVO orderVo) {
		getSqlSession().update("updateOrderState", orderVo);
	}
	
	public int getCount(Map<String, Object> map) {
		return (Integer) getSqlSession().selectOne("getCount", map);
	}
	public String orderPhotoInfo(int pSeq) {
		return getSqlSession().selectOne("orderPhotoInfo", pSeq);
	}
	
}
