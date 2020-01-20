<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公告列表</title>
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

    <script type="text/javascript">
        function list(p) {
            $("#currentPage").val(p);
            $("#newsForm").submit();
        }
    </script>

    <style type="text/css">

        .news-article {
            height: 50px;
            width: 100%;
            overflow: hidden;
            display: inline-block;
        }

        .th-article {
            width: 410px;
        }

        .news-title {
            height: 50px;
            width: 100%;
            overflow: hidden;
            display: inline-block;
        }

        .th-title {
            width: 190px;
        }
    </style>

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

            <form id="newsForm" class="layui-form xbs layui-form-pane"
                  action="${pageContext.request.contextPath}/api/news/newsList.html" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
                <div class="" style="text-align: center;">
                    <div class="layui-form-item" style="display: inline-block;">
                        <label class="layui-form-label xbs768">公告内容</label>
                        <div class="layui-input-inline xbs768">
                            <input class="layui-input" name="params[newsArticle]"
                                   value="${page.params.newsArticle}"
                                   placeholder="公告内容" id="LAY_demorange_s">
                        </div>
                        <div class="layui-input-inline" style="width:80px">
                            <button class="layui-btn" type="submit"><i
                                    class="layui-icon">&#xe615;</i></button>
                        </div>
                    </div>
                </div>
            </form>

            <xblock>
                <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon">&#xe640;</i>批量删除
                </button>
                <a class="layui-btn" href="${pageContext.request.contextPath}/news/news-add.jsp"><i
                        class="layui-icon">&#xe608;</i>发表公告
                </a>
                <span class="x-right" style="line-height:40px">共有数据：${page.totalCount} 条</span></xblock>
            <table class="layui-table">
                <thead>
                <tr>
                    <th>
                    </th>
                    <th>编号</th>
                    <th class="th-title">公告标题</th>
                    <th>发表人</th>
                    <th>发表时间</th>
                    <th>阅读量</th>
                    <th class="th-article">公告内容</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <form id="deleteForm" action="${pageContext.request.contextPath}/api/news/deleteNews.html"
                      method="post">
                    <c:forEach items="${page.list}" var="news" varStatus="index">

                        <tr>
                            <td class="news-checkbox"><input type="checkbox" name="ids" value="${news.newsId}">
                            </td>
                            <td class="news-id">${index.index+1}</td>
                            <td class="news-title">${news.newsTitle}</td>
                            <td class="news-time">${news.staff.staffName}</td>
                            <td class="news-time">${news.newsCreatedTime}</td>
                            <td class="news-time">${news.newsReadNum}</td>
                            <td class="news-article">${news.newsArticle}</td>

                            <td class="td-manage news-edit">
                                <a title="编辑" href="javascript:;" onclick="toEditNews('${news.newsId}')"
                                   class="ml-5" style="text-decoration:none">
                                    <i class="layui-icon">&#xe642;</i>
                                </a>
                                <a title="删除" href="javascript:;" onclick="deleteNews('${news.newsId}')"
                                   style="text-decoration:none">
                                    <i class="layui-icon">&#xe640;</i>
                                </a>

                                <a title="查看公告内容" href="javascript:;"
                                   onclick="getNewsArticle('${news.newsId}')"
                                   style="text-decoration:none">
                                    <i class="layui-icon">&#xe615;</i>
                                </a>

                            </td>
                        </tr>
                    </c:forEach>
                </form>
                </tbody>
            </table>
            <div style="float: right;">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${page.currentPage == 1}">
                            <li>
                                <a class="disabled" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${page.currentPage != 1}">
                            <li>
                                <a href="javascript:void(0)" onclick="list(${page.currentPage - 1})"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:forEach begin="1" end="${page.totalPage}" var="p">
                            <c:if test="${p == page.currentPage}">
                                <li class="active">
                                    <a href="javascript:void(0)">${p}</a>
                                </li>
                            </c:if>
                            <c:if test="${p != page.currentPage}">
                                <li>
                                    <a href="javascript:void(0)" onclick="list(${p})">${p}</a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${page.currentPage == page.totalPage}">
                            <li class="disabled">
                                <a aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${page.currentPage != page.totalPage}">
                            <li>
                                <a href="javascript:void(0)" onclick="list(${page.currentPage + 1})"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    </span>
                    </ul>
                </nav>
            </div>
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
<script>

    /*编辑*/
    function toEditNews(id) {
        layer.confirm('确认要编辑吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/news/toEdit" + id + ".html"
        });
    }

    /*删除*/
    function deleteNews(id) {
        layer.confirm('确认要删除吗？', function (index) {
            window.location.href = "${pageContext.request.contextPath}/api/news/deleteNews" + id + ".html"
        });
    }

    /*批量删除*/
    function delAll() {
        layer.confirm('确认要删除吗？', function (index) {
            $('#deleteForm').submit();
        });
    }

    /*查看公告内容*/
    function getNewsArticle(id) {
        var data = "{\"newsId\": \"" + id + "\"}";

        $.ajax({
            url: '${pageContext.request.contextPath}/api/news/ajaxReadNews.action',
            data: data,
            type: 'post',
            contentType: "application/json;charset=UTF-8",
            dataType: 'json',
            success: function (data) {
                layer.alert(data.data.newsArticle, {
                    area: '600px'
                })
            }
        })

    }
</script>

</body>
</html>
