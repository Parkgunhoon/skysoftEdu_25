package egovframework.dev.test.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import egovframework.dev.test.dao.TestDAO;
import egovframework.dev.test.vo.TestVO;


public class NaverParse {

	public static String getContent(Element element, String tagName) {
		NodeList list = element.getElementsByTagName(tagName);
		Element cElement = (Element) list.item(0);

		if (cElement.getFirstChild() != null) {
		return cElement.getFirstChild().getNodeValue();
		} else {
		return "";
		}
		}

	public List<TestVO> parse(String uri) {
		List<TestVO> searchList = new ArrayList<TestVO>();
		NodeList list = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		TestDAO testDAO = new TestDAO();

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
}
