package custom.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import custom.VO.CustomVO;
import custom.mybatis.connect.SqlMapConfig;

public class CustomDao {

	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	SqlSession sqlSession;
	

	public CustomDao() {
		
	}
	public int idCheck(String cId){
		sqlSession = factory.openSession(true);
		int result = sqlSession.selectOne("idCheck", cId);
		sqlSession.close();
		return result;
	}
	
	public String passCheck(String cId){
		sqlSession = factory.openSession(true);
		String result = sqlSession.selectOne("passCheck", cId);
		return result;
	}
	
	
	public void customInsert(CustomVO customVo) {
		sqlSession = factory.openSession(true);
		System.out.println("insert 성공여부(1 이면성공) = " + sqlSession.insert("customInsert", customVo));
		
	}
	
	public CustomVO login(String cId) {
		sqlSession = factory.openSession(true);
		CustomVO customVo = sqlSession.selectOne("login", cId);
		sqlSession.close();
		return customVo;
	}

	public List<CustomVO> idSearch(CustomVO customVo) {
		sqlSession = factory.openSession(true);
		List<CustomVO> vo = sqlSession.selectList("idSearch", customVo);
		sqlSession.close();
		return vo;
	}
	public String passSearch(CustomVO customVo) {
		sqlSession = factory.openSession(true);
		String resultPass = sqlSession.selectOne("passSearch", customVo);
		sqlSession.close();
		return resultPass;
	}
	
	
	public CustomVO customInfo(String cId) {
		sqlSession = factory.openSession(true);
		CustomVO vo = sqlSession.selectOne("customInfo", cId);
		sqlSession.close();
		return vo;
	}
	public void customUpdate(CustomVO vo) {
		sqlSession = factory.openSession(true);
		System.out.println("update 성공여부(1 이면성공) = " + sqlSession.update("customUpdate", vo));
		sqlSession.close();
	}
	
	public void customDelete(String cId) {
		sqlSession = factory.openSession(true);
		System.out.println(sqlSession.delete("customDelete", cId));
		sqlSession.close();
	}
	
	
}
