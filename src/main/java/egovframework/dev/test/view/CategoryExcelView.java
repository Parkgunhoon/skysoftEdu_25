package egovframework.dev.test.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.dev.test.vo.TestVO;

public class CategoryExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook wb,
            HttpServletRequest req,
            HttpServletResponse resp3) throws Exception {

		HSSFCell cell = null;

		HSSFSheet sheet = wb.createSheet("Test List");
	    sheet.setDefaultColumnWidth(12);

        // put text in first cell
        cell = getCell(sheet, 0, 0);
        setText(cell, "Test List");

        // set header information
        setText(getCell(sheet, 2, 0), "번호");
        setText(getCell(sheet, 2, 1), "제목");
        setText(getCell(sheet, 2, 2), "등록일");

        Map<String, Object> map= (Map<String, Object>) model.get("categoryMap");
        List<Object> categories = (List<Object>) map.get("category");

        boolean isVO = false;

        if (categories.size() > 0) {
        	Object obj = categories.get(0);
        	isVO = obj instanceof TestVO;
        }

        for (int i = 0; i < categories.size(); i++) {

        	if (isVO) {	// VO

        		TestVO category = (TestVO) categories.get(i);

	            cell = getCell(sheet, 3 + i, 0);
	            setText(cell, String.valueOf(category.getSeq()));

	            cell = getCell(sheet, 3 + i, 1);
	            setText(cell, category.getTitle());

	            cell = getCell(sheet, 3 + i, 2);
	            setText(cell, category.getExcelDate());

        	 } else {	// Map

        		Map<String, String> category = (Map<String, String>) categories.get(i);

 	            cell = getCell(sheet, 3 + i, 0);
 	            setText(cell, category.get("seq"));

 	            cell = getCell(sheet, 3 + i, 1);
 	            setText(cell, category.get("title"));

 	            cell = getCell(sheet, 3 + i, 2);
 	            setText(cell, category.get("excelDate"));

        	 }
        }

        Date date = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyyMMdd");
        String today = fm.format(date);

        //xls확장자로 다운로드
        resp3.setContentType("application/x-msdownload");
        resp3.setHeader("Content-Disposition", "attachment; filename=\"ExcelDownload_"+today+".xls\"");
	}

}
