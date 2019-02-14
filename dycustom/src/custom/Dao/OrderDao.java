package custom.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import custom.VO.OrderVO;
import custom.mybatis.connect.SqlMapConfig;

public class OrderDao {

	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	SqlSession sqlSession;
	

	public OrderDao() {
		
	}
	public void insertOrder(Map<String, Object> map) {
		sqlSession = factory.openSession(true);
		sqlSession.insert("insertOrder", map);
		sqlSession.close();
	}

	public void insertBank(Map<String, Object> map) {
		sqlSession = factory.openSession(true);
		sqlSession.insert("insertBank", map);
		sqlSession.close();
	}

	public void insertCard(Map<String, Object> map) {
		sqlSession = factory.openSession(true);
		sqlSession.insert("insertCard", map);
		sqlSession.close();
	}

	public void stockDec(Map<String, Object> map) {
		sqlSession = factory.openSession(true);
		sqlSession.update("stockDec", map);
		sqlSession.close();
	}
	public int orderCount(HashMap<String, Object> map) {
		sqlSession = factory.openSession(true);
		int result = sqlSession.selectOne("orderCount", map); 
		sqlSession.close();
		return result;
	}
	public List<OrderVO> orderListInfo(HashMap<String, Object> map) {
		sqlSession = factory.openSession(true);
		List<OrderVO> list = sqlSession.selectList("orderListInfo", map);
		sqlSession.close();
		return list;
	}
	
	public int getMax() {
		sqlSession = factory.openSession(true);
		int maxSeq = sqlSession.selectOne("getMax");
		sqlSession.close();
		return maxSeq;
	}
	public void updateStateCheck(int purchaseSeq) {
		sqlSession = factory.openSession(true);
		sqlSession.update("updateStateCheck", purchaseSeq);
		sqlSession.close();
		
	}
	public void updateRefundReqeust(int purchaseSeq) {
		sqlSession = factory.openSession(true);
		sqlSession.update("updateRefundReqeust", purchaseSeq);
		sqlSession.close();
	}
	public void updateRefundCancel(int purchaseSeq) {
		sqlSession = factory.openSession(true);
		sqlSession.update("updateRefundCancel", purchaseSeq);
		sqlSession.close();
	}

	public String orderStateCheck(OrderVO vo) {
		sqlSession = factory.openSession(true);
		String result = sqlSession.selectOne("orderStateCheck", vo);
		sqlSession.close();
		return result;
	}
}
