package egovframework.dev.test.vo;

import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {

	private String currentPage;
	private String totalCnt;
	private String viewCnt;
	private String pageScale;
	private String url;

	private int totalPageCount;
	private int firstPageNoOnPageList;
	private int lastPageNoOnPageList;
	private int firstRecordIndex;
	private int lastRecordIndex;

	public String makePageAnchorByHref(
		    String currentPage, //현재 페이지
		    String totalCnt, //전체 레코드 수
		    String viewCnt, //페이지당 보여줄 리스트 수
		    String pageScale, //페이지 블록 사이즈 << < 1 2 3 4 5 > >> 인 경우는 5
		    String url //url
		){
		    //구현부
		this.currentPage = currentPage;
		this.totalCnt = totalCnt;
		this.viewCnt = viewCnt;
		this.pageScale = pageScale;
		this.url = url;


		    return url;
		}

	public int getTotalPageCount() {
		totalPageCount = ((Integer.parseInt(totalCnt)-1)/Integer.parseInt(viewCnt)) + 1;
		return totalPageCount;
	}

	public int getFirstPageNo(){
		return 1;
	}

	public int getLastPageNo(){
		return getTotalPageCount();
	}

	public int getFirstPageNoOnPageList() {
		firstPageNoOnPageList = ((Integer.parseInt(currentPage)-1)/Integer.parseInt(pageScale))*Integer.parseInt(pageScale) + 1;
		return firstPageNoOnPageList;
	}

	public int getLastPageNoOnPageList() {
		lastPageNoOnPageList = getFirstPageNoOnPageList() + Integer.parseInt(pageScale) - 1;
		if(lastPageNoOnPageList > getTotalPageCount()){
			lastPageNoOnPageList = getTotalPageCount();
		}
		return lastPageNoOnPageList;
	}

	public int getFirstRecordIndex() {
		firstRecordIndex = (Integer.parseInt(currentPage) - 1) * Integer.parseInt(viewCnt);
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		lastRecordIndex = Integer.parseInt(currentPage) * Integer.parseInt(viewCnt);
		return lastRecordIndex;
	}
}
