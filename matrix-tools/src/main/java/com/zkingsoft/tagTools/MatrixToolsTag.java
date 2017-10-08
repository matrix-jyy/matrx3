package com.zkingsoft.tagTools;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zkingsoft.util.StringUtils;

public class MatrixToolsTag extends SimpleTagSupport {

	private String id;
	private String setStr;
	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {

		if (StringUtils.isContentSet(id, setStr)) {
			getJspBody().invoke(sw);
			getJspContext().getOut().println(sw.toString());
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSetStr() {
		return setStr;
	}

	public void setSetStr(String setStr) {
		this.setStr = setStr;
	}

}
