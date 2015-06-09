package egovframework.dev.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.dev.test.dao.TestDAO;
import egovframework.dev.test.service.TestService;
import egovframework.dev.test.vo.TestVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("testService")
public class TestServiceImpl extends AbstractServiceImpl implements TestService {

	@Resource(name="testDAO")
	private TestDAO testDAO;

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */
	@Override
	public List<TestVO> retrieveTestList(TestVO srchVO) {
		return testDAO.selectTestList(srchVO);
	}

	@Override
	public void insertTest(TestVO vo) {
		// TODO Auto-generated method stub
		if(vo!=null)testDAO.insertTest(vo);

	}

	@Override
	public void updateTest(TestVO vo) {
		// TODO Auto-generated method stub
		if(vo!=null)testDAO.updateTest(vo);

	}

	@Override
	public int deleteTest(TestVO vo) {
		// TODO Auto-generated method stub
		return testDAO.deleteTest(vo);
	}

	@Override
	public TestVO readTest(TestVO vo) {
		// TODO Auto-generated method stub
		return testDAO.readTest(vo);

	}

	@Override
	public int countTest(TestVO vo) {
		// TODO Auto-generated method stub
		return testDAO.countTest(vo);
	}

	@Override
	public int deleteTest(Integer vo) {
		// TODO Auto-generated method stub
		return testDAO.deleteTest(vo);
	}

	@Override
	public List<TestVO> searchTest(TestVO vo) {
		// TODO Auto-generated method stub
		return testDAO.searchTest(vo);
	}

	/*@Override
	public List<TestVO> countTest(TestVO vo) {
		// TODO Auto-generated method stub
		return testDAO.selectTestList(vo);
	}
	*/



}
