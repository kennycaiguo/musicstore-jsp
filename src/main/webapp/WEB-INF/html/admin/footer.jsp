<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String basePaths = request.getContextPath();
%>
<html>
  <head>
    <meta charset="UTF-8">
  </head>
  <body>
    <div class="pg-footer">
      <span>Music Store © 2019 Copyright</span>
      <span class="float-right">Powered by 
        <a href="https://github.com/zhonghuasheng">种花生的读书人</a>
      </span>
    </div>
    <script src="<%=basePaths%>/static/js/jquery-3.4.1.min.js"></script>
    <script src="<%=basePaths%>/static/js/admin-management.js"></script>
  </body>
</html>