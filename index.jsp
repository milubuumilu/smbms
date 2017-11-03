<%@page import="cn.bdqn.pojo.Bill"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账单总查询</title>
	
  </head>
  <%
  	List<Bill> list = (List<Bill>) request.getAttribute("list");
  	if(list==null){
  		response.sendRedirect("DoBillServlet?type=list");
  		return;
  	}
  %>
  <body>
    <h1>显示所有账单信息</h1>
     	<table class="layui-table">
     		<thead>
     			<td>账单id</td>
    			<td>账单编码</td>
    			<td>商品名称</td>
    			<td>商品描述</td>
    			<td>商品单位</td>
    			<td>商品数量</td>
    			<td>商品总额</td>
    			<td>是否支付</td>
    			<td>创建者</td>
    			<td>创建时间</td>
    			<td>更新者</td>
    			<td>更新时间</td>
    			<td>供应商姓名</td>
     		</thead>
     		<% 
     			for(Bill bill:list){
     		%>
	     		<tr>
	     			<td>
	     				<a href="DoBillServlet?type=one&id=<%=bill.getId() %>">
	     					<%=bill.getId() %>
	     				</a>
	     			</td>
	     			<td><%=bill.getBillCode() %></td>
	     			<td><%=bill.getProductName() %></td>
	     			<td><%=bill.getProductDesc() %></td>
	     			<td><%=bill.getProductUnit() %></td>
	     			<td><%=bill.getProductCount() %></td>
	     			<td><%=bill.getTotalPrice() %></td>
	     			<td><%=bill.getIsPayment() %></td>
	     			<td><%=bill.getCreatedBy() %></td>
	     			<td><%=bill.getCreationDate() %></td>
	     			<td><%=bill.getModifyBy() %></td>
	     			<td><%=bill.getModifyDate() %></td>
	     			<td><%=bill.getProName() %></td>
	     			<td align="center">
	     				<a href="DoBillServlet?type=updateControl&id=<%=bill.getId() %>">更新</a>&nbsp;&nbsp;
	     				<a href="DoBillServlet?type=delete&id=<%=bill.getId() %>">删除</a>
	     			</td>
	     		</tr>
     		<%		
     			}
     		%>
     		
     	</table>
     	<a href='add.jsp'>添加</a>
  </body>
</html>
