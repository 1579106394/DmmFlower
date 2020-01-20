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
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/lay/modules/layer.js"/>
</head>

<body>

<jsp:include page="/userpage/header.jsp"/>
<!-- 面包屑 -->
<div class="bread">
    <ol class="breadcrumb">
        <li>
            <a href="${pageContext.request.contextPath}/user/index.html">首页</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/api/flower/user/flowerList.html">花卉列表</a>
        </li>
        <li class="active">花卉详情</li>
    </ol>
</div>
<!-- 面包屑结束 -->

<!-- 花卉详情 -->
<div class="item-info">

    <div class="info-top">

        <!-- 左边轮播 -->
        <div class="info-top-left" style="height: 100%;">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="height: 100%;">
                <div class="carousel-inner" role="listbox" style="height: 100%;">
                    <div class="item active" style="height: 100%;">
                        <img src="${pageContext.request.contextPath}/${flower.flowerImg}" alt="..."
                             style="height: 100%;">
                    </div>
                </div>

            </div>
        </div>
        <!-- 左边轮播结束 -->

        <div class="info-top-right">
            <h4>${flower.flowerName}</h4>
            <p>
                <span class="badge">分类 ${flower.type.typeName}</span>
            </p>
            <p>
                <span>上架时间 ${flower.flowerCreatedTime}</span>
                <span style="padding-left: 30px;">库存 ${flower.flowerNum}</span>
            </p>
            <p>标价￥${flower.flowerPrice}</p>
            <p>
            <form action="${pageContext.request.contextPath}/api/history/addPriceHistory.html" id="form" method="post">
                <%--隐藏域，花卉id--%>
                <input type="hidden" name="flower.flowerId" value="${flower.flowerId}"/>
                <%--隐藏域，用户id--%>
                <input type="hidden" name="user.userId" value="${sessionScope.user.userId}"/>
                <div class="input-group">
                    <span class="input-group-btn">
                        <button class="btn btn-danger" type="button"
                                onclick="addCart('${flower.flowerId}')">加入购物车</button>
                    </span>
                </div>
            </form>
            </p>
        </div>
    </div>

    <div class="info-bottom">

        <div class="info-bottom-info2">
            <table width="100%">
                <tr width="100%">
                    <td width="13%" align="right"></td>
                    <td>
                        ${flower.flowerArticle}
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div class="info-history">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active">
                <a href="#comment" aria-controls="home" role="tab" data-toggle="tab">评论内容</a>
            </li>
        </ul>


        <div class="tab-content">

            <!-- 评论内容 -->
            <div role="tabpanel" class="tab-pane active info-history-comment" id="comment">
                <ul class="list-group">
                    <c:forEach items="${commentList}" var="comment">
                        <li class="list-group-item">
                            <div class="user-img">
                                <img src="${pageContext.request.contextPath}/${comment.staff.staffImg}" width="100%"
                                     height="100%">
                            </div>
                            <div class="price-info">
                                <p>
                                    <span class="username">${comment.staff.staffName}</span>
                                    <span class="price-date">${comment.commentCreatedTime}</span>
                                </p>
                                <p>
                                    <span class="comment-info">
                                            ${comment.commentArticle}
                                    </span>
                                </p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <!-- 评论框 -->
                <div class="comment-textarea">
                    <form action="${pageContext.request.contextPath}/api/comment/user/addComment.html" method="POST"
                          role="form">
                        <legend>发表评论</legend>
                        <%--隐藏域，花卉id--%>
                        <input type="hidden" name="flower.flowerId" value="${flower.flowerId}"/>
                        <%--隐藏域，用户id--%>
                        <input type="hidden" name="staff.staffId" value="${sessionScope.staff.staffId}"/>

                        <textarea class="form-control" rows="3" name="commentArticle"></textarea>
                        <br>
                        <button type="submit" class="btn btn-primary">评论</button>
                    </form>
                </div>
                <!-- 评论框结束 -->
            </div>
            <!-- 评论内容结束 -->
        </div>
    </div>

</div>
<!-- 花卉详情结束 -->


<!-- 底部导航 -->
<jsp:include page="/userpage/footer.jsp"/>
<!-- 底部导航结束 -->


<script type="text/javascript">

    /*加入购物车*/
    function addCart(flowerId) {
        var flag = window.confirm('确认要加入购物车吗？');
        if (flag) {
            var flower = "{\"flowerId\": \"" + flowerId + "\"}";
            $.ajax({
                url: "${pageContext.request.contextPath}/api/cart/addCart.action",
                data: flower,
                contentType: "application/json;charset=UTF-8",
                type: "post",
                dataType: "json",
                success: function (data) {

                    if (data.status == 200) {
                        alert("成功加入购物车");
                    } else {
                        alert("购物车已经存在，请勿重复添加");
                    }
                }

            })
        }
    }
</script>


</body>

</html>