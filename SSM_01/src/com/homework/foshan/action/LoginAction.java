package com.homework.foshan.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.bind.annotation.ResponseBody;

import com.homework.foshan.object.User;
import com.homework.foshan.service.Businessservice;
import com.homework.foshan.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private LoginService loginservice;

	private InputStream streamActionResult=null;
		public String checklogin() throws UnsupportedEncodingException
		{	
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String m1 = (String) request.getParameter("member");
			String p1 = (String) request.getParameter("password");
			String kind1 = (String) request.getParameter("kind");
			System.out.println(m1+p1+kind1);
			setStreamActionResult(new ByteArrayInputStream("empty".getBytes("UTF-8")));
			int i;

			if(kind1.equals("2"))
			{
			i=loginservice.checkbus(m1, p1);	
			}
			else
			i=loginservice.checkuser(m1,p1);
			System.out.println("终于成功啦！"+m1+p1);
			if(m1==null)
			{
				System.out.println("空");

			}
		
			if(i==1)
			{	
				setStreamActionResult(new ByteArrayInputStream("success".getBytes("UTF-8")));
				if(kind1.equals("2"))
				{
				request.getSession().setAttribute("busName", m1);
				
				}
				else
				{
				request.getSession().setAttribute("userName", m1);
				System.out.println(request.getSession().getAttribute("userName"));
				}
				 return "success";
			}
			else if(i==-1)
			{	
				request.setAttribute("mes","用户名字不存在");
				return "error";
			}
			else
			{	System.out.println("0");
				request.setAttribute("mes","密码出错");
				request.setAttribute("name", m1);
				return "error";
			}
			
		}
		public String register()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			String m1 = (String) request.getParameter("member");
			String p1 = (String) request.getParameter("password");
			User user=new User();
			user.setUsername(m1);
			user.setUserpassword(p1);
			loginservice.register(user);
			return "success";
		}
		public String checkrepeat() throws UnsupportedEncodingException
		{	HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			String m1 = (String) request.getParameter("member");
			System.out.println(m1);
			int i=loginservice.checkrepeat(m1);
			if(i==1)
			{
				setStreamActionResult(new ByteArrayInputStream("1".getBytes("UTF-8")));
			}
			else
				setStreamActionResult(new ByteArrayInputStream("0".getBytes("UTF-8")));
			return "success";
		}
		public String usermessage()
		{	HttpServletRequest request=ServletActionContext.getRequest();
			String name=(String) request.getSession().getAttribute("userName");
			
			request.setAttribute("usermessage",loginservice.getuserbyname(name));
			return "success";
		}
		public void setLoginservice(LoginService loginservice) {
			this.loginservice = loginservice;
		}
		public LoginService getLoginservice() {
			return loginservice;
		}
		public void setStreamActionResult(InputStream streamActionResult) {
			this.streamActionResult = streamActionResult;
		}
		public InputStream getStreamActionResult() {
			return streamActionResult;
		}
}
