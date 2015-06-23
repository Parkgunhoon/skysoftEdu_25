package egovframework.dev.test.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

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
	private MultipartFile file;
	private String saveFileNm;
	private String realFileNm;
	private int fileSeq;
	private String excelDate;
	private String useYn;

	private String nTitle;
	private String description;
	private String link;
	private String searchQuery;
	private String image;
	private String val;

	//기안자
	private int userSeq;
	private String draftTypeCd;
	private String groupCd;
	private String rankCd;
	private String userNm;
	private String emerPhone;
	private String reason;
	private String etc;
	private String halfCd;
	private String docNm;
	private String startDt;
	private String endDt;
	private String drafterNm;
	private String reviewerNm;
	private String approvalNm;
	private String stateCd;
	private String stepCd;
	//기안자 끝


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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getSaveFileNm() {
		return saveFileNm;
	}

	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}

	public String getRealFileNm() {
		return realFileNm;
	}

	public void setRealFileNm(String realFileNm) {
		this.realFileNm = realFileNm;
	}

	public int getFileSeq() {
		return fileSeq;
	}

	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}

	public String getExcelDate() {
		return excelDate;
	}

	public void setExcelDate(String excelDate) {
		this.excelDate = excelDate;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getDraftTypeCd() {
		return draftTypeCd;
	}

	public void setDraftTypeCd(String draftTypeCd) {
		this.draftTypeCd = draftTypeCd;
	}

	public String getEmerPhone() {
		return emerPhone;
	}

	public void setEmerPhone(String emerPhone) {
		this.emerPhone = emerPhone;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getHalfCd() {
		return halfCd;
	}

	public void setHalfCd(String halfCd) {
		this.halfCd = halfCd;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getDocNm() {
		return docNm;
	}

	public void setDocNm(String docNm) {
		this.docNm = docNm;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getDrafterNm() {
		return drafterNm;
	}

	public void setDrafterNm(String drafterNm) {
		this.drafterNm = drafterNm;
	}

	public String getReviewerNm() {
		return reviewerNm;
	}

	public void setReviewerNm(String reviewerNm) {
		this.reviewerNm = reviewerNm;
	}

	public String getApprovalNm() {
		return approvalNm;
	}

	public void setApprovalNm(String approvalNm) {
		this.approvalNm = approvalNm;
	}

	public String getStateCd() {
		return stateCd;
	}

	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	public String getStepCd() {
		return stepCd;
	}

	public void setStepCd(String stepCd) {
		this.stepCd = stepCd;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getGroupCd() {
		return groupCd;
	}

	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}

	public String getRankCd() {
		return rankCd;
	}

	public void setRankCd(String rankCd) {
		this.rankCd = rankCd;
	}

}
