package com.zhku.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class Loginfilter extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String user=(String) request.getSession().getAttribute("userName");
		System.out.println("进入拦截器");
		if(user==null)	
		{System.out.println("拦截");
		return "jumplogin";
		}
		else
		{
		arg0.invoke();
		}
		return null;
	}

}
