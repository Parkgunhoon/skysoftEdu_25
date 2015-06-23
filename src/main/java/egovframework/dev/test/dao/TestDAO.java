package egovframework.dev.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.dev.test.vo.TestVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("testDAO")
public class TestDAO extends EgovAbstractDAO {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */
	public int insertTest(TestVO vo) {
		return (Integer) insert("testDAO.insertTestCreate", vo);
	}

	public void updateTest(TestVO vo) {
		update("testDAO.updateTestUpdate", vo);
	}

	public int deleteTest(TestVO vo) {
		return delete("testDAO.deleteTestDelete", vo);
	}

	public int deleteTest(Integer vo) {
		return delete("testDAO.deleteTestDelete", vo);
	}

	public TestVO listTest(TestVO vo) {
		return (TestVO)selectByPk("testDAO.selectTestList", vo);
	}

	public TestVO readTest(TestVO vo) {
		return (TestVO)selectByPk("testDAO.selectTestRead", vo);
	}

	public TestVO insertTestNaver(TestVO vo) {
		return (TestVO) insert("testDAO.insertTestNaver", vo);
	}



	/*@SuppressWarnings("unchecked")
	public List<TestVO> countTest(TestVO vo) {
		return list("testDAO.countTestList", vo);//리스트로 받자 public List<TestVO> selectTestList(TestVO srchVO)
	}*/

	public int countTest(TestVO vo){
		return (Integer) selectByPk("testDAO.countList", vo);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectTestList(TestVO srchVO) {
		return list("testDAO.selectTestList", srchVO);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> searchTest(TestVO srchVO) {
		return list("testDAO.selectTestSearch", srchVO);
	}

	public void fileInsertTest(TestVO vo) {
		insert("testDAO.fileInsertTest", vo);
	}

	public void fileDelete(TestVO srchVO) {
		update("testDAO.fileDelete", srchVO);
	}

	public TestVO nonFileRead(TestVO vo) {
		return (TestVO)selectByPk("testDAO.nonFileRead", vo);
	}

	public void fileUpdate(TestVO vo) {
		update("testDAO.fileUpdate", vo);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> excelAllDownload(TestVO vo) {
		return list("testDAO.excelAllDownload", vo);
	}

	@SuppressWarnings("unchecked")
	public List<TestVO> selectDraftList(TestVO srchVO) {
		return list("testDAO.selectDraftList", srchVO);
	}

}
