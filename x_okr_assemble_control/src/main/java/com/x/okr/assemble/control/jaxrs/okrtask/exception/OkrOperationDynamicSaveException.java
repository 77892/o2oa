package com.x.okr.assemble.control.jaxrs.okrtask.exception;

import com.x.base.core.exception.PromptException;

public class OkrOperationDynamicSaveException extends PromptException {

	private static final long serialVersionUID = 1859164370743532895L;

	public OkrOperationDynamicSaveException( Throwable e, String id ) {
		super("系统在保存待办信息操作动态信息时发生异常。ID:" + id, e );
	}
	
	public OkrOperationDynamicSaveException( Throwable e ) {
		super("系统在保存待办信息操作动态信息时发生异常。", e );
	}
}
