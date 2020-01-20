<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <script src="${pageContext.request.contextPath}/userpage/js/jquery-1.9.1.js"></script>
    <script src="${pageContext.request.contextPath}/userpage/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/userpage/css/mycss.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/userpage/css/bootstrap.min.css">
</head>

<body>

<jsp:include page="/userpage/header.jsp"/>

<!-- 面包屑 -->
<div class="bread">
    <ol class="breadcrumb">
        <li>
            <a href="${pageContext.request.contextPath}/user/index.html">首页</a>
        </li>
        <li class="active">公告阅读</li>
    </ol>
</div>
<!-- 面包屑结束 -->

<!-- 阅读公告 -->
<div class="read-news">
    <p class="news-title">${news.newsTitle}</p>
    <p class="news-date">作者： ${news.staff.staffName}&nbsp;&nbsp;&nbsp;发布时间：${news.newsCreatedTime}</p>
    <div class="news-info">
        ${news.newsArticle}
    </div>

</div>
<!-- 阅读公告结束 -->


<!-- 底部导航 -->
<jsp:include page="/userpage/footer.jsp"/>
<!-- 底部导航结束 -->


</body>

</html>
