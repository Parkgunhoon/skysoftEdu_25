package egovframework.dev.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.w3c.dom.Element;

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

	public Map<String,Object> retrieveTestList(TestVO srchVO);

	public byte[] fileDownload(TestVO vo, HttpServletResponse response);

	public void fileDeleteTest(TestVO srchVO);

	public Map<String,Object> excelDownload(TestVO srchVO);

	public Map<String, Object> excelAllDownload(TestVO vo);

	public boolean excelUpload(TestVO srchVO);

	public List<TestVO> naverApi(TestVO vo);

	public void naverInsertTest(TestVO vo);

	public String getContent(Element element, String tagName);

	public List<TestVO> parse(String uri);


	//기안
	public List<TestVO> draftTestList(TestVO srchVO);
}
