package egovframework.dev.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.net.ssl.internal.ssl.Debug;

import egovframework.dev.test.service.TestService;
import egovframework.dev.test.vo.PageUtilBean;
import egovframework.dev.test.vo.PaginationUtil;
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

		Map<String,Object> map = testService.retrieveTestList(srchVO);

		model.addAttribute("srchVO", srchVO);
		model.addAttribute("contentLIst", map.get("category"));
		model.addAttribute("paginationInfo", map.get("paginationInfo"));
		model.addAttribute("pageUtilBean", map.get("pageUtilBean"));
		return "test/list";
	}

/*	@PageTitle("리스트(L)")
	@RequestMapping(value = "/test/list2.do")
	public String retrieveListP(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model)  throws Exception {

		Map<String,Object> map = testService.retrieveTestList(srchVO);


		PaginationUtil pag = new PaginationUtil();
		String url = "/test/list2.do";
		pag.makePageAnchorByHref(Integer.toString(srchVO.getcPageNo()), Integer.toString(totCnum), "5", "5", url);

		model.addAttribute("srchVO", srchVO);
		model.addAttribute("contentLIst", map.get("category"));
		model.addAttribute("paginationInfo", map.get("paginationInfo"));
		return "test/list";
	}*/           // 페이징 util 나중에 확인해 보자

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
		return "redirect:/test/list.do";
	}

	@PageTitle("다운로드(Down) fileDownload")
	@RequestMapping(value = "/test/download.do")//경로지정
	@ResponseBody
	public byte[] fileDownloadTest(
			@ModelAttribute("vo") TestVO vo,
			ModelMap model,
			HttpServletResponse response) throws Exception {

		return testService.fileDownload(vo,response);
	}

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
			@ModelAttribute("vo") TestVO vo,
			ModelMap model) throws Exception {

		testService.updateTest(vo);
		return "redirect:/test/list.do";
	}

	@PageTitle("삭제(D)")
	@RequestMapping(value = "/test/delete.do")
	public String delete(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {
		/* Service, DAO 작성후 주석제거 */
		log.debug("REQ ::: "+StringUtil.nullConvert(srchVO.getTitle()));
		testService.deleteTest(srchVO);
		return "redirect:/test/list.do";
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

		}
		return "redirect:/test/list.do";
	}

	@PageTitle("파일삭제(D)")
	@RequestMapping(value = "/test/fileDelete.do")
	public String fileDelete(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {


		testService.fileDeleteTest(srchVO);
		return "redirect:/test/read.do?seq="+srchVO.getSeq();
	}

	@PageTitle("엑셀다운로드(다운로드)")
	@RequestMapping(value = "/test/excelDownload.do")
	public ModelAndView excelDownload(
			@ModelAttribute("vo") TestVO vo) throws Exception {

		Map<String,Object> map = testService.retrieveTestList(vo);

		return new ModelAndView("categoryExcelView", "categoryMap", map);
	}

	@PageTitle("엑셀전체다운로드(다운로드)")
	@RequestMapping(value = "/test/excelAllDownload.do")
	public ModelAndView excelAllDownload(
			@ModelAttribute("vo") TestVO vo) throws Exception {

		Map<String,Object> map = testService.excelAllDownload(vo);

		return new ModelAndView("categoryExcelView", "categoryMap", map);
	}

	@PageTitle("엑셀업로드화면(C)")
	@RequestMapping(value = "/test/excelUploadView.do")
	public String excelUploadView(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {

		return "test/categoryExcelView";
	}

	@PageTitle("엑셀업로드저장(C)")
	@RequestMapping(value = "/test/excelUpload.do", method=RequestMethod.POST)
	public String excelUpload(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {


		model.addAttribute("result", testService.excelUpload(srchVO));

		return "test/categoryExcelView";
	}

	@PageTitle("네이버API")
	@RequestMapping(value = "/test/naverApi.do", method=RequestMethod.POST)
	public String naverApi(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model) throws Exception {

		List<TestVO> naverList = testService.naverApi(srchVO);
		model.addAttribute("naverList", naverList);
		model.addAttribute("srchVO", srchVO);

		return "test/naverApi";
	}

	@PageTitle("기안 리스트")
	@RequestMapping(value = "/test/draftList.do")
	public String draftList(
			@ModelAttribute("srchVO") TestVO srchVO,
			ModelMap model)  throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srchVO.getcPageNo());
		paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(5);

		int firstRecordIndex = paginationInfo.getFirstRecordIndex();
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();

		srchVO.setFirstIndex(firstRecordIndex);
		srchVO.setRecordCountPerPage(recordCountPerPage);

		int totCnum = testService.countTest(srchVO);
		paginationInfo.setTotalRecordCount(totCnum);

		srchVO.setSeq(2);
		List<TestVO> list = testService.draftTestList(srchVO);
		model.addAttribute("srchVO", srchVO);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("list", list);
		return "test/draftList";
	}

}