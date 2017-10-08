package com.zkingsoft.authority;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.util.WebUtil;

public class MatrixFunctionTag extends SimpleTagSupport {

	

	private String value;
	private Authority authority;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	StringWriter sw = new StringWriter();
	String mybtns = "";

	public void doTag() throws JspException, IOException {

		authority = (Matrix) WebUtil.getSessionAttribute(SystemConstance.MATRIX);
		if(authority.isFnPermitted(value)){
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}
		
		
	}

}