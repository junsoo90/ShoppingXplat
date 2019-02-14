package com.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.VO.OrderVO;

public interface OrderDao {
	
	public List<OrderVO> orderList(Map<String, Object> map);
	
	public HashMap<String, Object> orderInfoBank(OrderVO orderVo);
	
	public HashMap<String, Object> orderInfoCard(OrderVO orderVo);
	
	public String getPayType(int purchaseSeq);
	
	public void updateOrderState(OrderVO orderVo);
	
	public int getCount(Map<String, Object> map);
	
	public String orderPhotoInfo(int pSeq);
}
