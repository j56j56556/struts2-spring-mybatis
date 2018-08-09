package com.homework.foshan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.homework.foshan.service.Businessservice;
import com.homework.foshan.service.LoginService;
import com.opensymphony.xwork2.ActionContext;

public class Businessshow {
	
	private Businessservice businessservice;

	public void setBusinessservice(Businessservice businessservice) {
		this.businessservice = businessservice;
	}

	public Businessservice getBusinessservice() {
		return businessservice;
	}
	public String show()
	{ActionContext.getContext().get("hello");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setAttribute("home",businessservice.show());
		
		return "success";
	}
	public String showline()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		int line=Integer.parseInt(request.getParameter("line"));
		request.setAttribute("home",businessservice.limitshow(line));		
		return "success";
	}
	public String buycar()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		
		String s1=(String) request.getSession().getAttribute("userName");

			return "success";
	}
	public String businessmessage()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		String s1=(String)request.getSession().getAttribute("busName");
		request.setAttribute("busmessage", businessservice.getbyname(s1));
		return "success";
	}


		
}
