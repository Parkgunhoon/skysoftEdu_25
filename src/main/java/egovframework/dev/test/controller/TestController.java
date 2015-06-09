package egovframework.dev.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.catalina.connector.Request;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.net.ssl.internal.ssl.Debug;

import egovframework.dev.test.service.TestService;
import egovframework.dev.test.vo.TestVO;
import egovframework.framework.annotation.PageTitle;
import egovframework.framework.util.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**-----------------------------------------------------------------------
 * skysoft edu Project
 *------------------------------------------------------------------------
 * @Class TestController.java
 * @Description Test 과제 프로젝트
 * @author anonymous
 * @since yyyy.mm.dd
 * @version 1.0
 *
 * @Copyright (c) (주) 하늘연소프트 개발사업부 개발팀 All rights reserved.
 *------------------------------------------------------------------------
 * Modification Information
 *------------------------------------------------------------------------
 * 수정일			수정자			수정내용
 * ------------------------------------------------------------------------
 * yyyy.mm.dd	anonymous	최초생성
 */
@Controller
public class TestController {//주석

	/** Log Info */
	protected Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "testService")
	private TestService testService;

	/**
	 * 리스트(L)
	 *
	 * @param TestVO 검색조건정보
	 * @param model 화면모델
	 * @return Test
	 * @throws Exception
	 */
	@PageTitle("리스트(L)")
	@RequestMapping(value = "/test/list.do")
	public String retrieveList(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model)  throws Exception {
		/* Service, DAO 작성후 주석제거 */
		log.debug("REQ ::: "+StringUtil.nullConvert(srchVO.getTitle()));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srchVO.getcPageNo());
		paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(5);

		int firstRecordIndex = paginationInfo.getFirstRecordIndex();
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();

		srchVO.setFirstIndex(firstRecordIndex);
		srchVO.setRecordCountPerPage(recordCountPerPage);

		List<TestVO> list = testService.retrieveTestList(srchVO);
		int totCnum = testService.countTest(srchVO);

		//List<TestVO> totCount = testService.countTest(srchVO);
		//paginationInfo.setTotalRecordCount(totCount.size());
		paginationInfo.setTotalRecordCount(totCnum);

		model.addAttribute("srchVO", srchVO);
		model.addAttribute("contentLIst", list);
		model.addAttribute("paginationInfo", paginationInfo);
		return "test/list";
	}

	@PageTitle("검색")
	@RequestMapping(value = "/test/search.do")//경로지정
	public String searchTest (
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srchVO.getcPageNo());
		paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(5);

		srchVO.setSearchE(1);
		int firstRecordIndex = paginationInfo.getFirstRecordIndex();
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();
		int totCnum = testService.countTest(srchVO);

		srchVO.setFirstIndex(firstRecordIndex);
		srchVO.setRecordCountPerPage(recordCountPerPage);
		paginationInfo.setTotalRecordCount(totCnum);

		List<TestVO> list = testService.searchTest(srchVO);


		model.addAttribute("srchVO", srchVO);
		model.addAttribute("contentLIst", list);
		model.addAttribute("paginationInfo", paginationInfo);
		return "test/list";//리스트 같이 하나 더 만들자
	}

	@PageTitle("입력(C)")
	@RequestMapping(value = "/test/insert.do")//경로지정
	public String insertTest(
			@ModelAttribute("vo") TestVO vo,
			ModelMap model) throws Exception {
		model.addAttribute("vo",vo);
		return "test/insert";
	}

	@PageTitle("입력(C) Process")
	@RequestMapping(value = "/test/insertProc.do")//경로지정
	public String insertProcTest(
			@ModelAttribute("vo") TestVO vo,
			ModelMap model) throws Exception {
		testService.insertTest(vo);
		return "redirect:/test/list.do?cPageNo=" + vo.getcPageNo();
	}

	/*@RequestMapping(value = "/test/list.do", params = "type=input", method = RequestMethod.POST)
	public String retrieveList1(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model, String pageNo) throws Exception {
		 Service, DAO 작성후 주석제거
		log.debug("REQ ::: "+StringUtil.nullConvert(srchVO.getTitle()));
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srchVO.getcPageNo());
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(8);

		int firstRecordIndex = paginationInfo.getFirstRecordIndex();
		srchVO.setFirstIndex(firstRecordIndex);
		srchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


		testService.insertTest(srchVO);
		List<TestVO> list = testService.retrieveTestList(srchVO);
		paginationInfo.setTotalRecordCount(list.size());
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("contentLIst", list);
		return "test/list";
	}
*/

	@PageTitle("조회(R)")
	@RequestMapping(value = "/test/read.do")//경로지정
	public String readTest(
			@ModelAttribute("vo") TestVO vo,
			ModelMap model) throws Exception {
		    TestVO read = testService.readTest(vo);
		    model.addAttribute("contentRead", read);
			return "test/read";
	}

	@PageTitle("수정(U)")
	@RequestMapping(value = "/test/update.do", method = RequestMethod.POST)
	public String update(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {
		/* Service, DAO 작성후 주석제거 */
		log.debug("REQ ::: "+StringUtil.nullConvert(srchVO.getTitle()));
		testService.updateTest(srchVO);
		return "redirect:/test/list.do?cPageNo=" + srchVO.getcPageNo();
	}

	@PageTitle("삭제(D)")
	@RequestMapping(value = "/test/delete.do")
	public String delete(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {
		/* Service, DAO 작성후 주석제거 */
		log.debug("REQ ::: "+StringUtil.nullConvert(srchVO.getTitle()));
		testService.deleteTest(srchVO);
		return "redirect:/test/list.do?cPageNo=" + srchVO.getcPageNo();
	}

	@PageTitle("삭제(D)2")
	@RequestMapping(value = "/test/delete2.do")
	public String deleteChck(
			@RequestParam(value="delList") String[] delList,
			ModelMap model) throws Exception {
		TestVO srchVO = new TestVO();
		if(delList != null && delList.length > 0) {
			/* Service, DAO 작성후 주석제거 */
			ArrayList<Integer> cks = new ArrayList<Integer>();
			for(int i=0;i<delList.length;i++){
				cks.add(Integer.parseInt(delList[i])) ;
				srchVO.setSeq(cks.get(i));
				testService.deleteTest(srchVO);
				log.debug(Integer.parseInt(delList[i]));
			}

			//srchVO.getSeq()
			/*for(int i=0;i<cks.length;i++){
			System.out.println("1");
			}*/

		}
		return "redirect:/test/list.do";
	}
}