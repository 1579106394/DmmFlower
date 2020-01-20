<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

<!-- 轮播图 -->
<div id="carousel-example-generic" class="carousel slide header-slide" data-ride="carousel" style="height: 558px;">
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
    </ol>
    <div class="carousel-inner" role="listbox" style="height: 558px;">
        <div class="item active">
            <img src="${pageContext.request.contextPath}/userpage/img/1.jpg" alt="...">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/userpage/img/2.jpg" alt="...">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/userpage/img/3.jpg" alt="...">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/userpage/img/4.jpg" alt="...">
            <div class="carousel-caption">
            </div>
        </div>
    </div>

    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<!-- 轮播图结束 -->

<!-- 公告资讯 -->
<div class="news">

    <div class="news-left">
        <ul class="list-group">
            <h4>[ 花卉展览  ]</h4>
            <li class="list-group-item">2018年毕业晚会花卉展
                <br> 作品征集中...
                <br> 欢迎致电:123456789987
                <br> 花卉部:010-12345678
                <br> 邮箱：123456789@qq.com
            </li>
        </ul>
    </div>

    <div class="news-right">
        <div class="top">
            <span>公告资讯</span>
        </div>
        <div class="bottom">
            <ul class="list-group">

                <c:forEach items="${newsPage.list }" var="news">
                    <li class="list-group-item">
                        <a href="${pageContext.request.contextPath}/api/news/user/readNews/${news.newsId}.html">
                                ${news.newsTitle}
                            <span class="date">${news.newsCreatedTime}</span>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<!-- 公告资讯结束 -->

<!-- 花卉推荐 -->
<div class="lots">


    <div class="lot-right">
        <div class="top">
            <span>推荐鲜花</span>
        </div>
        <div class="bottom">

            <c:forEach items="${flowerPage.list}" var="flower">

                <div class="lot">
                    <a href="${pageContext.request.contextPath}/api/flower/user/readFlower/${flower.flowerId}.html">
                        <img src="${pageContext.request.contextPath}/${flower.flowerImg}" alt="">
                            ${flower.flowerName}
                    </a>
                </div>

            </c:forEach>

        </div>
    </div>
</div>
<!-- 花卉推荐结束 -->


<!-- 底部导航 -->
<jsp:include page="/userpage/footer.jsp"/>
<!-- 底部导航结束 -->


</body>

</html>
