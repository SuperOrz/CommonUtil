package com.hs.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hs.filter.EnhancedRequest;

@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet  {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			//处理响应编码
			if(request.getMethod().equalsIgnoreCase("get")){
				request = new EnhancedRequest(request,"utf-8");
			}else{
				request.setCharacterEncoding("utf-8");
			}
			response.setContentType("text/html;charset=UTF-8");
			
			String methodName = request.getParameter("method");
			Method method = null;
			try {
				method = this.getClass().getMethod(methodName,
						HttpServletRequest.class,HttpServletResponse.class);
			} catch (Exception e) {
				throw new RuntimeException("调用的方法："+methodName+"不存在",e);
			}
			
			try{
				String result = (String) method.invoke(this, request,response);
				result = result.trim();
				if(result!=null && !result.isEmpty()){
					int index = result.indexOf(":");
					if(index == -1){
						request.getRequestDispatcher(result).forward(request, response);
					}else{
						String act = result.substring(0,index);
						String path = result.substring(index+1);
						if(act.equals("f")){
							request.getRequestDispatcher(path).forward(request, response);
						}else if(act.equals("s")){
							response.sendRedirect(request.getContextPath()+path);
						}
					}
				}
			}catch(Exception e){
				throw new RuntimeException(e);
			}
	}
	
}
