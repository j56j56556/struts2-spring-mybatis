package com.zhku.filter;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class Bianma extends MethodFilterInterceptor {

	@Override
	public String doIntercept(ActionInvocation invo) throws Exception {
        ActionContext  actionContext = invo.getInvocationContext();
        
        HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response= (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
         if( request.getMethod().compareToIgnoreCase("post")>=0){ 
              try { 
                      request.setCharacterEncoding("utf-8");
                  } catch (UnsupportedEncodingException e) { 
                      e.printStackTrace(); 
                  } 
           }else{ 
              Iterator<String[]> iterval=request.getParameterMap().values().iterator(); 
              while(iterval.hasNext()){ 
                  String[] parames = iterval.next(); 
                   for (int i = 0; i < parames.length; i++) { 
                    try { 
                            parames[i]=new String(parames[i].getBytes("iso8859-1"),"utf-8");
                        } catch (UnsupportedEncodingException e) {  
                            e.printStackTrace(); 
                        } 
                   }    
                }    
            } 
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache"); 
            return invo.invoke(); 
    }



}
