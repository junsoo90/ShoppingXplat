package custom.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import custom.VO.CartVO;
import custom.mybatis.connect.SqlMapConfig;



public class PurchaseDao {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();

	SqlSession sqlSession;
	public PurchaseDao() {
		
	}
	public List<CartVO> getCart(String getcId) {
		sqlSession = factory.openSession(true);
		List<CartVO> vo = sqlSession.selectList("getCart", getcId);
		sqlSession.close();
		return vo;
	}

	public int getMaxCnt(String userId) {
		sqlSession = factory.openSession(true);
		int maxCnt = sqlSession.selectOne("getMaxCnt", userId);
		sqlSession.close();
		return maxCnt;
	}

	public void cartIn(CartVO cartvo) {
		sqlSession = factory.openSession(true);
		sqlSession.insert("cartIn", cartvo);
		sqlSession.close();
	}


	public int getOptionStock(CartVO cartVO) {
		sqlSession = factory.openSession(true);
		int optionStock = sqlSession.selectOne("getOptionStock", cartVO);
		sqlSession.close();
		return optionStock;
	}

	public void cartDelete(Map<String, Object> map) {
		sqlSession = factory.openSession(true);
		sqlSession.delete("cartDelete", map);
		sqlSession.close();
	}
}
