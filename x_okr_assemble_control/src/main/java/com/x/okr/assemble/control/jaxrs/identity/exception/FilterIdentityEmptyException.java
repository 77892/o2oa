package com.x.okr.assemble.control.jaxrs.identity.exception;

import com.x.base.core.exception.PromptException;

public class FilterIdentityEmptyException extends PromptException {

	private static final long serialVersionUID = 1859164370743532895L;

	public FilterIdentityEmptyException() {
		super("进行查询的身份名称为空，无法继续进行查询操作。");
	}
}
