package com.homework.foshan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.homework.foshan.object.Business;
import com.homework.foshan.service.Businessservice;
import com.homework.foshan.service.LoginService;
import com.homework.foshan.service.Orderservice;
import com.opensymphony.xwork2.ActionSupport;

public class Ordersure extends ActionSupport {
		private Orderservice orderservice;
		private LoginService loginservice;
		private Businessservice businessservice;
		private Business business;
		public void add()
		{	HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			int id1=1;
			int id2=Integer.parseInt(request.getParameter("food_id"));		
			orderservice.add(id1, id2);
		}
		public void deletefood()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			int id1=1;
			int id2=Integer.parseInt(request.getParameter("food_id"));		
			orderservice.deletefood(id1, id2);
		}
		public String showbuycar()
		{
			
			
			return "success";
		}
		public String showuserorder()
		{	HttpServletRequest request=ServletActionContext.getRequest();
			String s1=(String) request.getSession().getAttribute("userName");
			int id=getLoginservice().getid(s1);
			request.setAttribute("userobject", orderservice.showuserorder(id));
			return "success";
		}
		public String showbusorder()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			String s1=(String) request.getSession().getAttribute("busName");
			int id=businessservice.getbyname(s1).getBid();
			request.setAttribute("busobject", orderservice.showbusorder(id));
			return "success";
		}
		public String usercommitorder()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			String s1=(String)request.getSession().getAttribute("userName");
			String flag=request.getParameter("bid");
			int bid=0;
			if(flag==null)
			{	
				bid=businessservice.getbyname(business.getBname()).getBid();

			}
			else
			{
				bid=Integer.parseInt((String)request.getParameter("bid"));
			}
			int id=getLoginservice().getid(s1);
			orderservice.userCommitOrder(id, bid);
			return "success";
		}
		public String operateorder()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			String s1=(String) request.getSession().getAttribute("busName");
			int bid=businessservice.getbyname(s1).getBid();
			int uid=Integer.parseInt((String) request.getParameter("uid"));
			int state=Integer.parseInt((String) request.getParameter("state"));
			int cla=Integer.parseInt((String) request.getParameter("cla"));
			orderservice.busOperateOrder(uid, bid, cla, state);
			return "success";
		}
		
		
		public void setLoginservice(LoginService loginservice) {
			this.loginservice = loginservice;
		}

		public LoginService getLoginservice() {
			return loginservice;
		}
		public void setOrderservice(Orderservice orderservice) {
			this.orderservice = orderservice;
		}

		public Orderservice getOrderservice() {
			return orderservice;
		}
		public void setBusinessservice(Businessservice businessservice) {
			this.businessservice = businessservice;
		}
		public Businessservice getBusinessservice() {
			return businessservice;
		}
		public void setBusiness(Business business) {
			this.business = business;
		}
		public Business getBusiness() {
			return business;
		}
}
