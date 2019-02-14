package custom.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import custom.VO.CartVO;
import custom.VO.OptionVO;
import custom.VO.PhotoVO;
import custom.VO.ProductVO;
import custom.mybatis.connect.SqlMapConfig;

public class ProductDao {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();

	SqlSession sqlSession;
	
	public ProductDao() {
	}
	public List<ProductVO> productList(Map<String,Object> map){
		sqlSession = factory.openSession(true);
		
		List<ProductVO> list = sqlSession.selectList("productLists", map);

		System.out.println("list.size() = " + list.size());
		//System.out.println(list.get(0).getpName());
		sqlSession.close();
		return list;

	}
	
	public ProductVO getInfo(int pSeq){
		sqlSession = factory.openSession(true);
		
		ProductVO vo = sqlSession.selectOne("getInfo",pSeq);
		sqlSession.close();
		return vo;
	}
	public List<OptionVO> getOptionInfo(int seq){
		sqlSession = factory.openSession(true);
		
		List<OptionVO> list = sqlSession.selectList("getOptionInfo", seq);
		sqlSession.close();
		return list;
	}
		
	public List<PhotoVO> photoGetInfo(int seq){
		sqlSession = factory.openSession(true);
		
		List<PhotoVO> vo = sqlSession.selectList("photoGetInfo", seq);
		sqlSession.close();
		return vo;
	}

	public int checkStock(Map<String,Object> optionMap) {
		sqlSession = factory.openSession(true);
		
		int result = sqlSession.selectOne("checkStock", optionMap);
		sqlSession.close();
		return result;
	}
	public int getCount(HashMap<String, Object> map) {
		sqlSession = factory.openSession(true);
		
		int result = sqlSession.selectOne("getCount", map);
		sqlSession.close();
		return result;
	}

	public String productStateCheck(int pSeq) {
		sqlSession = factory.openSession(true);
		
		String result = sqlSession.selectOne("productStateCheck", pSeq);
		sqlSession.close();
		return result;
	}

}
