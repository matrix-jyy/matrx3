package com.zkingsoft.authority;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;

import com.zkingsoft.constance.SystemConstance;
import com.zkingsoft.exception.GlobleExceptionResolver;
import com.zkingsoft.util.WebUtil;

public class MatrixButtonTag extends SimpleTagSupport {

	private String value;
	private Authority authority;
	private Logger log = Logger.getLogger(GlobleExceptionResolver.class);
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {

		authority = (Matrix) WebUtil.getSessionAttribute(SystemConstance.MATRIX);
		try {
			if(authority.isBtnPermitted(value)){
				getJspBody().invoke(sw);
				getJspContext().getOut().println(sw.toString());
			}
		} catch (Exception e) {
			log.error("MatrixButtonTag:" ,e);
		}
	}

}