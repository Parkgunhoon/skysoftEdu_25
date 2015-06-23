package egovframework.dev.test.vo;


public class PageUtilBean {

	 private int rows; // DB에 저장된 총 레코드 수
	 private int currPage; // 이용자가 요청한 페이지 번호
	 private int rowsPerPage; // 한 화면에 출력할 DB레코드 수
	 // 위의 값은 DAO 클래스에서 한페이지에 가져오는 레코드수와 일치해야 함
	 private int numsPerPage; // 한 화면의 하단에 출력할 페이지번호 수
	 private int totalPages;
	 private String link = "javascript:linkPage"; // 자동으로 설정됨, 페이지번호를 클릭하면 이동할 링크
	 private String paramName = "cPageNo"; // 페이지번호 파라미터 이름 (예, BoardServlet?cmd=list&page=3)
	 private String leftSign = "[이전]"; // 페이지번호가 많아서 한 화면을 넘을 때 이전으로 이동하는 링크
	 private String rightSign = "[다음]"; // 다음 링크
	 private String leftEnd = "［처음]"; // 처음 페이지로 이동
	 private String rightEnd = "[마지막]";// 마지막 페이지로 이동



		 public String getNavStr() {

		  totalPages = getTotalPages();
		  //int n = currPage/numsPerPage;
		  //n += currPage%numsPerPage==0 ? 0 : 1;
		  //int begin = currPage;
		  //int end = numsPerPage;
		  int n = currPage/numsPerPage;
		  n += currPage%numsPerPage==0 ? 0 : 1;
		  int begin = n*numsPerPage-(numsPerPage-1);
		  int end = n*numsPerPage;


		  StringBuffer sb = new StringBuffer();
		  String cPageNo = null;
		  System.out.println("ssssssssssssssssssssssssssssssssssssssssss"+begin);
		  if(begin>end) end += begin;
		  if(end>totalPages) end = totalPages;
		  for(int i=begin;i<=end;i++){
			  System.out.println("dddddddddddddddddddddddddddd"+i);
		   if(i==currPage) cPageNo = "<span style=\"color:red;font-size:17pt;\">"+i+"</span>";
		   else cPageNo = String.valueOf(i);
		   sb.append("<a href=\""+link+"("+i+")"+"\">"+cPageNo+"</a>&nbsp;&nbsp;");

		  }
		  if(begin!=1)sb.insert(0, "<a href=\""+link+"("+(begin-(begin-1))+")"+"\">"+leftSign+"&nbsp;&nbsp;</a> ");
		  if(end<totalPages)sb.append(" <a href=\""+link+"("+(end+1)+")"+"\">&nbsp;&nbsp;"+rightSign+" </a>");
		  sb.insert(0, "<a href=\""+link+"("+1+")"+"\">"+leftEnd+"&nbsp;&nbsp;</a> ");
		  sb.append("<a href=\""+link+"("+totalPages+")"+"\">&nbsp;&nbsp;"+rightEnd+"</a>");

		  return sb.toString();
		 }


		 public int getRows() {
		  return rows;
		 }

		 public void setRows(int rows) {
		  this.rows = rows;
		 }

		 public int getCurrPage() {
		  return currPage;
		 }
		 public int getRowsPerPage() {
		  return rowsPerPage;
		 }
		 public int getNumsPerPage() {
		  return numsPerPage;
		 }

		 public int getTotalPages() {
		  totalPages = rows/rowsPerPage;
		  totalPages += rows%rowsPerPage==0 ? 0 : 1;
		  return totalPages;
		 }
		 public String getLink() {
		  return link;
		 }
		 public String getParamName() {
		  return paramName;
		 }
		 public String getLeftSign() {
		  return leftSign;
		 }
		 public String getRightSign() {
		  return rightSign;
		 }

		 public void setCurrPage(int currPage) {
		  this.currPage = currPage;
		 }
		 public void setRowsPerPage(int rowsPerPage) {
		  this.rowsPerPage = rowsPerPage;
		 }
		 public void setNumsPerPage(int numsPerPage) {
		  this.numsPerPage = numsPerPage;
		 }
		 public void setTotalPages(int totalPages) {
		  this.totalPages = totalPages;
		 }
		 public void setLink(String link) {
		  this.link = link;
		 }
		 public void setParamName(String paramName) {
		  this.paramName = paramName;
		 }
		 public void setLeftSign(String leftSign) {
		  this.leftSign = leftSign;
		 }
		 public void setRightSign(String rightSign) {
		  this.rightSign = rightSign;
		 }


}
