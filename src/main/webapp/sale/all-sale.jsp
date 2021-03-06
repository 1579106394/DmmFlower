<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>总销量</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

</head>
<body>
<!-- 顶部开始 -->
<jsp:include page="/header.jsp"></jsp:include>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<div class="wrapper">
    <!-- 左侧菜单开始 -->
    <jsp:include page="/left.jsp"></jsp:include>
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->

            <form id="saleForm" class="layui-form xbs layui-form-pane"
                  action="${pageContext.request.contextPath}/api/sale/saleList.html" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
                <div class="" style="text-align: center;">
                    <div class="layui-form-item" style="display: inline-block;">
                        <label class="layui-form-label xbs768">花卉名</label>
                        <div class="layui-input-inline xbs768">
                            <input class="layui-input" name="params[flowerName]" value="${page.params.flowerName}"
                                   placeholder="花卉名" id="LAY_demorange_s">
                        </div>
                        <div class="layui-input-inline" style="width:80px">
                            <button class="layui-btn" type="submit"><i
                                    class="layui-icon">&#xe615;</i></button>
                        </div>
                    </div>
                </div>
            </form>

            <xblock>
                <span class="x-right" style="line-height:40px">共有数据：${saleList.size()} 条</span>&nbsp;&nbsp;</xblock>
            <table class="layui-table">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>花卉名</th>
                    <th>总销量</th>
                </tr>
                </thead>
                <tbody>
                <form>
                    <c:forEach items="${saleList}" var="sale" varStatus="index">

                        <tr>
                            <td>${index.index+1}</td>
                            <td>${sale.flower.flowerName}</td>
                            <td>${sale.saleNum}</td>

                        </tr>
                    </c:forEach>
                </form>
                </tbody>
            </table>

        </div>
        <!-- 右侧内容框架，更改从这里结束 -->
    </div>
</div>
<!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <jsp:include page="/footer.jsp"></jsp:include>
</div>
<!-- 底部结束 -->
<!-- 背景切换开始 -->
<jsp:include page="/bg.jsp"></jsp:include>
<!-- 背景切换结束 -->
<!-- 页面动态效果 -->

</body>
</html>