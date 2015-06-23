package egovframework.dev.test.service.impl;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.terracotta.agent.repkg.de.schlichtherle.io.FileOutputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import egovframework.dev.test.dao.TestDAO;
import egovframework.dev.test.service.TestService;
import egovframework.dev.test.vo.PageUtilBean;
import egovframework.dev.test.vo.PaginationUtil;
import egovframework.dev.test.vo.TestVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

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
	public Map<String,Object> retrieveTestList(TestVO srchVO) {

		PaginationInfo paginationInfo = new PaginationInfo();
		PageUtilBean pageUtilBean = new PageUtilBean();
		paginationInfo.setCurrentPageNo(srchVO.getcPageNo());
		paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(5);

		pageUtilBean.setCurrPage(srchVO.getcPageNo());
		pageUtilBean.setRowsPerPage(5);
		pageUtilBean.setNumsPerPage(5);


		int firstRecordIndex = paginationInfo.getFirstRecordIndex();
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();



		srchVO.setFirstIndex(firstRecordIndex);
		srchVO.setRecordCountPerPage(recordCountPerPage);

		Map<String,Object> map = new HashMap<String,Object>();

		List<TestVO> list = testDAO.selectTestList(srchVO);
		int totCnum = testDAO.countTest(srchVO);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i < list.size(); i++){
			String date = format.format(list.get(i).getRegDtm());
			list.get(i).setExcelDate(date);
			String useYn = list.get(i).getUseYn();
			if(useYn.equals("N")){
				useYn= null;
				list.get(i).setRealFileNm(useYn);
			}
		}


		map.put("category", list);



		paginationInfo.setTotalRecordCount(totCnum);
		pageUtilBean.setRows(totCnum);

		map.put("paginationInfo", paginationInfo);
		map.put("pageUtilBean", pageUtilBean.getNavStr());

		return map;
	}

	@Override
	public void insertTest(TestVO vo) {

		TestVO testVO = new TestVO();
		if(vo.getFile().getSize()>0){
			//하드에 파일 저장
			testVO = fileWrite(vo);
		}

		//저장된 글의 SEQ 리턴값 받아서 입력
		int seq = testDAO.insertTest(vo);
		testVO.setSeq(seq);

		// 파일 저장하기.
		testDAO.fileInsertTest(testVO);

	}

	@Override
	public void updateTest(TestVO vo) {

		if(vo.getFile().getSize()>0){
			//하드에 파일 저장
			TestVO testVO = fileWrite(vo);
			testDAO.updateTest(vo);
			testDAO.fileUpdate(testVO);
		}else {
			testDAO.updateTest(vo);
		}
	}

	@Override
	public int deleteTest(TestVO vo) {
		// TODO Auto-generated method stub
		return testDAO.deleteTest(vo);
	}

	@Override
	public TestVO readTest(TestVO vo) {
		TestVO testVO = testDAO.readTest(vo);
		if(testVO==null){
			testVO = testDAO.nonFileRead(vo);
		}
		return testVO;
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

	@Override
	public byte[] fileDownload(TestVO vo, HttpServletResponse response) {

		File file = new File("C:/Users/SkysoftD002/Desktop/saveFile/"+vo.getRealFileNm());

		byte[] bytes = null;
		String fn = null;
		try {
			  bytes = FileCopyUtils.copyToByteArray(file);
			  // http에 한글은 적용할 수없기때문에 영문으로 인코딩하여 적용
			  fn = new String(vo.getRealFileNm().getBytes(), "iso_8859_1");
		} catch (IOException e) {
			e.printStackTrace();
		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fn + "\"");
        response.setContentLength(bytes.length);
        response.setContentType("image/jpeg");

		return bytes;
	}

	@Override
	public void fileDeleteTest(TestVO srchVO) {

		testDAO.fileDelete(srchVO);
	}

	private TestVO fileWrite(TestVO vo){
		MultipartFile file = vo.getFile();
		InputStream inputStream = null;
		OutputStream outputStream = null;

		String realFileNm = "";
		String saveFileNm = "";

		try {
			 realFileNm = file.getOriginalFilename();

			 saveFileNm = URLEncoder.encode(realFileNm,"UTF-8");

			 inputStream = file.getInputStream();

			 File newFile = new File("C:/Users/SkysoftD002/Desktop/saveFile/"+realFileNm);
			 if(!newFile.exists()){
				 newFile.createNewFile();
			 }
			 outputStream = new FileOutputStream(newFile);
			 int read = 0;
			 byte[] bytes = new byte[1024];

			 while ((read = inputStream.read(bytes)) != -1){
				 outputStream.write(bytes, 0, read);
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
	        try {
	            outputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

		vo.setSaveFileNm(saveFileNm);
		vo.setRealFileNm(realFileNm);

		return vo;
	}

	@Override
	public Map<String,Object> excelDownload(TestVO srchVO) {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(srchVO.getcPageNo());
		paginationInfo.setRecordCountPerPage(5);
		paginationInfo.setPageSize(5);

		int firstRecordIndex = paginationInfo.getFirstRecordIndex();
		int recordCountPerPage = paginationInfo.getRecordCountPerPage();

		srchVO.setFirstIndex(firstRecordIndex);
		srchVO.setRecordCountPerPage(recordCountPerPage);

		Map<String,Object> map = new HashMap<String,Object>();

		List<TestVO> list = testDAO.selectTestList(srchVO);
		map.put("category", list);

		int totCnum = testDAO.countTest(srchVO);

		paginationInfo.setTotalRecordCount(totCnum);

		map.put("paginationInfo", paginationInfo);

		return map;
	}

	@Override
	public Map<String, Object> excelAllDownload(TestVO vo) {

		Map<String, Object> map = new HashMap<String,Object>();
		List<TestVO> list = testDAO.excelAllDownload(vo);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0; i < list.size(); i++){
			String date = format.format(list.get(i).getRegDtm());
			list.get(i).setExcelDate(date);
		}

		map.put("category", list);

		return map;
	}

	@Override
	public boolean excelUpload(TestVO srchVO) {

		MultipartFile file = srchVO.getFile();
		boolean error = false;
		List<TestVO> list = new ArrayList<TestVO>();
		if (file != null && file.getSize() > 0) {
			try {
				Workbook wb = new HSSFWorkbook(file.getInputStream());
				Sheet sheet = wb.getSheetAt(0);

				int last = sheet.getLastRowNum();

				log.debug("라스트넘몇개?"+last);

				for(int i=1; i<=last; i++){
					Row row = sheet.getRow(i);
					TestVO vo = new TestVO();;
						if(row==null){
							error = true;
							return true;
						}

						vo.setTitle(row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
						vo.setContents(row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue());

						list.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}

		if(error!=true){
			for(int i=0; i < list.size(); i++){
				TestVO vo = list.get(i);
				//저장된 글의 SEQ 리턴값 받아서 입력
				int seq = testDAO.insertTest(vo);
				vo.setSeq(seq);

				// 파일 저장하기.
				testDAO.fileInsertTest(vo);
			}
		}

		return error;
	}

	@Override
	public List<TestVO> naverApi(TestVO srchVO) {

		String searchQuery = srchVO.getSearchQuery();
		String uri = "";
		try {
			uri = "http://openapi.naver.com/search?key=d88d81775da582190444e005cb2303d5&target=book&display=10&start=1" +"&query=" +
					URLEncoder.encode(searchQuery, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//NaverParse naverAPI = new NaverParse();
    	List<TestVO> naverList = parse(uri);

    	//testDAO.insertTestNaver(srchVO);

    	log.debug("서치쿼리"+searchQuery);
    	log.debug("서치url"+uri);
		return naverList;
	}

	@Override
	public void naverInsertTest(TestVO vo) {
		testDAO.fileInsertTest(vo);
	}

	@Override
	public String getContent(Element element, String tagName) {
		NodeList list = element.getElementsByTagName(tagName);
		Element cElement = (Element) list.item(0);

		if (cElement.getFirstChild() != null) {
		return cElement.getFirstChild().getNodeValue();
		} else {
		return "";
		}
	}

	@Override
	public List<TestVO> parse(String uri) {
		List<TestVO> searchList = new ArrayList<TestVO>();
		NodeList list = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		//TestDAO testDAO = new TestDAO();

		try {
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(uri);
		Element root = doc.getDocumentElement();
		list = root.getElementsByTagName("item");

		for (int i = 0; i < list.getLength(); i++) {
		Element element = (Element) list.item(i);
		TestVO srchVO = new TestVO();
		srchVO.setnTitle(getContent(element, "title"));
		srchVO.setDescription(getContent(element, "description"));
		srchVO.setLink(getContent(element, "link"));
		srchVO.setImage(getContent(element, "image"));
		System.out.println("타이틀"+srchVO.getDescription());
		testDAO.insertTestNaver(srchVO);
		searchList.add(srchVO);


		// 이부분이 출력 부분입니다.
		 System.out.println("** Naver Open API Connect **");
		 System.out.println("검색제목 : "+getContent(element, "title"));
		 //System.out.println("description : "+getContent(element,
		 //"description"));
		 System.out.println("책 이미지(링크): "+getContent(element,
		 "image"));
		 System.out.println("책 설명(내용) : "+getContent(element,
		 "description"));
		 System.out.println("ddddddddddddddddddddddddd"+srchVO.getSearchQuery());
		}
		} catch (ParserConfigurationException e) {
		e.printStackTrace();
		} catch (SAXException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		return searchList;
		}

	@Override
	public List<TestVO> draftTestList(TestVO srchVO) {
		return testDAO.selectDraftList(srchVO);
	}


	//기안



}
