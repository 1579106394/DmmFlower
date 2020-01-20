<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>

<!-- 顶部 -->
<div class="header">
    <img src="${pageContext.request.contextPath}/userpage/img/logo.gif" class="header-logo">
    <span class="header-title">锦绣花城</span>


    <div class="btn-group">
        <c:if test="${sessionScope.staff == null}">
            <a href="${pageContext.request.contextPath}/login.jsp" class="btn btn-default">登录</a>
            <a href="${pageContext.request.contextPath}/register.jsp" class="btn btn-default">注册</a>
        </c:if>
        <c:if test="${sessionScope.staff != null}">
            <a class="btn btn-default">
                <img src="${pageContext.request.contextPath}/${sessionScope.staff.staffImg}" style="height: 60px;width: 60px;border-radius: 50%; margin-top: -15px;">
            </a>
            <a href="${pageContext.request.contextPath}/index.html" class="btn btn-default">后台管理</a>
            <a href="${pageContext.request.contextPath}/logout.html" class="btn btn-default">注销</a>
        </c:if>
    </div>

<style>
    .navbar-inverse {
        background-color: #fccffc;
        border-color: #ffb7f5;
    }

    .header-nav-bottom {
        background-color: #ffb7f5;
    }

    .navbar-inverse .navbar-collapse, .navbar-inverse .navbar-form {
        border-color: #ffb7f5;
    }
</style>
</div>
<!-- 顶部结束 -->

<!-- 导航栏 -->
<div class="header-nav-div">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="ul" class="nav navbar-nav header-nav">

                </ul>
                <form id="searchForm" action="${pageContext.request.contextPath}/api/flower/user/flowerList${page.params.typeId}.html"
                      class="navbar-form navbar-right" method="post">
                    <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
                    <div class="form-group">
                        <input type="text" name="search" value="${page.search}" class="form-control" placeholder="请输入关键字">
                    </div>
                    <button type="submit" class="btn btn-default">全局搜索</button>
                </form>
            </div>
        </div>
    </nav>
</div>

<script>
    window.onload = function () {

        $.ajax({
            url: "${pageContext.request.contextPath}/api/type/user/typeList.action",
            type: "get",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
                var typeList = result.data
                var ul = document.getElementById("ul");
                var liArrays = ul.getElementsByTagName("li");
                //清除操作
                for(var i=0;i<liArrays.length;i++){
                    ul.removeChild(liArrays[i]);
                    i--;
                }
                $("#ul").append(
                    "<li>\n" +
                    "<a href=\"${pageContext.request.contextPath}/user/index.html\" style=\"color: #a0c534;\">网站首页</a>\n" +
                    "</li>"
                );
                for(var i = 0; i < typeList.length; i++) {
                    $("#ul").append(
                        "<li>\n" +
                        "<a href=\"${pageContext.request.contextPath}/api/flower/user/flowerList"+typeList[i].typeId+".html\" style=\"color: #a0c534;\">" +
                        ""+typeList[i].typeName+"</a>\n" +
                        "</li>"
                    );
                }

            }
        });

    }
</script>

<!-- 导航栏结束 -->
