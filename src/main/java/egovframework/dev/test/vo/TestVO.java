package egovframework.dev.test.vo;

import java.util.Date;

import egovframework.dev.test.dao.TestDAO;


public class TestVO {

	private int seq;
	private String title;
	private String contents;
	private String search;
	private Date regDtm;
	private int cPageNo = 1;
	private int firstIndex;
	private int recordCountPerPage;
	private int searchE;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}

	public int getcPageNo() {
		return cPageNo;
	}

	public void setcPageNo(int cPageNo) {
		this.cPageNo = cPageNo;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getSearchE() {
		return searchE;
	}

	public void setSearchE(int searchE) {
		this.searchE = searchE;
	}

}
