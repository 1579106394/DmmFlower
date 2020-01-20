<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>编辑花卉</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/editor/dist/css/wangEditor.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xadmin.js"></script>
    <style type="text/css">
        #div1 {
            width: 100%;
            height: 300px;
        }
        .wangEditor-container {
            background-color: transparent;
        }
        .wangEditor-menu-container {
            background-color: transparent;
        }
    </style>

    <script type="text/javascript">
        function formSubmit() {
            var article = $("#div1").html();
            $(".article").val(article);
            $("#form").submit();
        }
    </script>
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
            <form id="form" class="layui-form layui-form-pane"
                  action="${pageContext.request.contextPath}/api/flower/editFlower.html" method="post" enctype="multipart/form-data">
                <input type="hidden" name="staff.staffId" value="${sessionScope.staff.staffId}"/>
                <input type="hidden" name="flowerId" value="${flower.flowerId}">
                <!-- 富文本编辑器内容 -->
                <input type="hidden" name="flowerArticle" value='' class="article"/>
                <div class="layui-form-item">
                    <label for="L_flower_name" class="layui-form-label">
                        花卉名
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="L_flower_name" value="${flower.flowerName}" name="flowerName" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label for="L_type_name" class="layui-form-label">
                        分类
                    </label>
                    <div class="layui-input-inline">
                        <select id="L_type_name" name="type.typeId">
                            <c:forEach items="${typeList}" var="type">
                                <c:if test="${type.typeId == flower.type.typeId}">
                                    <option selected value="${type.typeId}">${type.typeName}</option>
                                </c:if>
                                <c:if test="${type.typeId != flower.type.typeId}">
                                    <option value="${type.typeId}">${type.typeName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="L_flower_price" class="layui-form-label">
                        单价
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="L_flower_price" value="${flower.flowerPrice}" name="flowerPrice" required=""
                               autocomplete="off" class="layui-input">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label for="L_flower_img" class="layui-form-label">
                        图片
                    </label>
                    <div class="layui-input-inline">
                        <input type="file" id="L_flower_img" name="photo" required=""
                               autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <img src="${pageContext.request.contextPath}/${flower.flowerImg}" style="width: 40px;height: 40px;">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">
                        花卉描述
                    </label>
                    <div class="layui-input-inline" style="width: 80%;">
                        <!-- 富文本编辑器 -->
                        <div id="div1">
                            ${flower.flowerArticle}
                        </div>
                        <script type="text/javascript"
                                src="${pageContext.request.contextPath }/editor/dist/js/wangEditor.js"></script>
                        <script type="text/javascript">
                            $(function () {
                                var editor = new wangEditor('div1');
                                editor.create();
                            });
                        </script>

                        <!-- 富文本编辑器 -->
                    </div>
                </div>
                <div class="layui-form-item">
                    </label>
                    <button class="layui-btn" type="button" onclick="formSubmit();">
                        编辑
                    </button>
                </div>
            </form>
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