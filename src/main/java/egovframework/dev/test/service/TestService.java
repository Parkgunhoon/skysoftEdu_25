package egovframework.dev.test.service;

import java.util.List;

import egovframework.dev.test.vo.TestVO;

public interface TestService {

    /**
     * 리스트(L)
     * @param TestVO 검색VO
     * @return List<TestVO>
     */
	public void insertTest(TestVO vo);

	public void updateTest(TestVO vo);

	public int deleteTest(TestVO vo);

	public int deleteTest(Integer vo);

	//public List<TestVO> countTest(TestVO vo);

	public TestVO readTest(TestVO vo);

	public List<TestVO> searchTest(TestVO vo);

	public int countTest(TestVO vo);

	public List<TestVO> retrieveTestList(TestVO srchVO);
}
