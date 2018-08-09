package com.homework.foshan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.homework.foshan.object.Food;
import com.homework.foshan.service.Businessservice;
import com.homework.foshan.service.Foodservice;
import com.homework.foshan.service.LoginService;
import com.homework.foshan.service.Orderservice;
import com.opensymphony.xwork2.ActionSupport;

public class Foodshow extends ActionSupport {
		private Foodservice foodservice;
		private Orderservice orderservice;
		private Businessservice businessservice;
		private LoginService loginservice;
		private Food food;
		public String show()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			
			String s1=(String) request.getSession().getAttribute("userName");
			int id=loginservice.getid(s1);
 			int i=Integer.parseInt(request.getParameter("businessID"));
			request.setAttribute("business", businessservice.getbyid(i));
			request.setAttribute("businessInter",foodservice.show(i));
			request.setAttribute("buycar", orderservice.showbuycar(id, i));
			
			return "success";
		}
		public String busmenushow()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			String s1=(String) request.getSession().getAttribute("busName");
			int bid=businessservice.getbyname(s1).getBid();
			request.setAttribute("businessmenu", foodservice.show(bid));
			return "success";
		}
		public String addfood()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			String s1=(String) request.getSession().getAttribute("busName");
			int bid=businessservice.getbyname(s1).getBid();
			foodservice.addfood(bid, food.getName(), food.getPrice(), food.getFdimg());
			return "success";
		}
		public String deletefood()
		{

			foodservice.deletefood(food.getId());
			return "success";
		}
		public String updatefood()
		{
			HttpServletRequest request=ServletActionContext.getRequest();
			String s1=(String)request.getSession().getAttribute("busName");
			int bid=businessservice.getbyname(s1).getBid();
			foodservice.updatefood(food.getId(), bid, food.getName(), food.getPrice(), food.getFdimg());
			return "success";
		}
		public void setFoodservice(Foodservice foodservice) {
			this.foodservice = foodservice;
		}
		public Foodservice getFoodservice() {
			return foodservice;
		}
		public void setBusinessservice(Businessservice businessservice) {
			this.businessservice = businessservice;
		}
		public Businessservice getBusinessservice() {
			return businessservice;
		}
		public void setOrderservice(Orderservice orderservice) {
			this.orderservice = orderservice;
		}
		public Orderservice getOrderservice() {
			return orderservice;
		}
		public void setLoginservice(LoginService loginservice) {
			this.loginservice = loginservice;
		}
		public LoginService getLoginservice() {
			return loginservice;
		}
		public void setFood(Food food) {
			this.food = food;
		}
		public Food getFood() {
			return food;
		}
}
