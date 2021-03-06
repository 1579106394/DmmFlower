<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
<script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
<style>
    .container {
        width: 100%;
        height: 71px;
        background-color: rgba(0, 0, 0, 0.25);
        border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    }
    .layui-nav {
        background-color: transparent;
    }
    .layui-nav-child {
        background-color: transparent;
    }
    .layui-nav .layui-nav-child a {
        color: #fff;
    }
</style>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="${pageContext.request.contextPath}/index.html" style="color: white;text-align: center;">锦绣花城后台管理</a></div>
    <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;" style="color: white;">
                <%--${sessionScope.staff.staffName}--%>
                <img src="${pageContext.request.contextPath}/${sessionScope.staff.staffImg}" style="height: 60px;width: 60px;border-radius: 50%; margin-top: 5px;">
            </a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a href="${pageContext.request.contextPath}/user/index.html">网站首页</a></dd>
                <dd><a href="${pageContext.request.contextPath}/logout.html">退出</a></dd>
            </dl>
        </li>
    </ul>
</div>
<!-- 顶部结束 -->
