package egovframework.dev.test.service;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import egovframework.dev.test.vo.TestVO;

public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object uploadedFile, Errors errors) {

		TestVO file = (TestVO)uploadedFile;

		if(file.getFile().getSize() == 0 ){
			errors.rejectValue("file", "uploadForm.selectFile",
						"please select a file!");
		}
	}

}
