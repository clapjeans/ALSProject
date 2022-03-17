<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    List<Map<String,Object>> rList =( List<Map<String,Object>>)request.getAttribute("Titlelist");
    String SORTNM=CmmUtil.nvl((String) session.getAttribute("SORTNM"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SeoGram - SEO Agency Template</title>

    <link rel="stylesheet" href="./assets/css/maicons.css">

    <link rel="stylesheet" href="./assets/css/bootstrap.css">

    <link rel="stylesheet" href="./assets/vendor/animate/animate.css">

    <link rel="stylesheet" href="./assets/css/theme.css">

    <style>
        h2 {
            position: relative;
            font-size: 18px;
            color: #111;
            text-transform: uppercase;
            text-align: center;
            font-weight: bold;
        }
        ul{
            text-align: center;
        }
        li{
            list-style: none;
            display: inline-block;

        }

    </style>
</head>
<body>

<!-- Back to top button -->
<div class="back-to-top"></div>

<header>
  <%@include file="../../views/inc/navbar.jsp"%>

    <div class="container">
        <div class="page-banner">
            <div class="row justify-content-center align-items-center h-100">
                <div class="col-md-6">
                    <nav aria-label="Breadcrumb">
                        <ul class="breadcrumb justify-content-center py-0 bg-transparent">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active">Method</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">스마트한 재활용방법</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="page-section">
    <ul class="menuCategory" style="margin-bottom: 30px">
        <h2 ><span>Category</span></h2>
        <li><a href="/search?SORTNM=가연성">가연성<span> | </span></a>
        </li>
        <li><a href="/search?SORTNM=대형폐기물">대형폐기물<span> | </span></a>
        </li>
        <li><a href="/search?SORTNM=불연성">불연성<span> | </span></a>
        </li>
        <li><a href="/search?SORTNM=비닐류">비닐류<span> | </span></a>
        </li>
        <li><a href="/search">전체<span>  </span></a>
        </li>
    </ul>
    <div class="container">
        <div class="row">
            <div class="col-sm-10">
                <form action="#" class="form-search-blog">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <select id="categories" class="custom-select bg-light">
                                <option>종류</option>
                                <option value="travel">내용</option>
                                <option value="travel">생각나는거있음</option>

                            </select>
                        </div>
                        <input type="text" class="form-control" placeholder="Enter keyword..">
                    </div>
                </form>
            </div>
            <div class="col-sm-2 text-sm-right">
                <button class="btn btn-secondary">Filter <span class="mai-filter"></span></button>
            </div>
        </div>

        <div class="row my-5">
            <%for(Map<String, Object> pMap :rList){  %>
            <div class="col-lg-4 py-3">
                <div class="card-blog">
                    <div class="header">
                        <div class="post-thumb">
                            <img src="../assets/img/trash.jpg" alt="">
                        </div>
                    </div>

                        <h5 class="post-title"><a href="/infoPg?DICNM=<%=pMap.get("DICNM")%>">&nbsp&nbsp<%=pMap.get("DICNM")%></a></h5>
                        <div class="post-date">&nbsp&nbsp&nbsp<%=pMap.get("SORTNM")%></div>
                        <p></p>
                    </div>
                </div>
            <%} %>
        </div>


<!---pagenation-->

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">

                <!-- 이전 -->
                <c:if test="${paging.currentPage eq 1}">
                    <li class="page-item">
                        <a class="page-link no-before" tabindex="-1" aria-disabled="true">이전</a>
                    </li>
                </c:if>
                <c:if test="${paging.currentPage ne 1}">
                    <c:url var="before" value="">
                        <c:param name="currentPage" value="${paging.currentPage - 1}"/>
                    </c:url>

                    <li class="page-item">
                        <a class="page-link" tabindex="-1" href="${before}" aria-disabled="true">이전</a>
                    </li>
                </c:if>

                <!-- 페이지 -->
                <c:forEach var="page" begin="${paging.startPage}" end="${paging.endPage}">
                    <c:if test="${page eq paging.currentPage }">
                        <li class="page-item"><a class="page-link bg-primary text-light">${page}</a></li>
                    </c:if>

                    <c:if test="${page ne paging.currentPage }">
                        <c:url var="pagination" value="/search?SORTNM=${SORTNM}">
                            <c:param name="currentPage" value="${paging.currentPage + 1}"/>

                        </c:url>

                        <li class="page-item"><a class="page-link" href="${pagination}">${page}</a></li>
                    </c:if>
                </c:forEach>

                <!-- 다음 -->
                <c:if test="${paging.currentPage eq paging.maxPage}">
                    <li class="page-item">
                        <a class="page-link no-before" tabindex="-1" aria-disabled="true">다음</a>
                    </li>
                </c:if>
                <c:if test="${paging.currentPage ne paging.maxPage}">
                    <c:url var="after" value="/search?SORTNM=${SORTNM}">
                        <c:param name="currentPage" value="${paging.currentPage + 1}"/>
                    </c:url>

                    <li class="page-item">
                        <a class="page-link" tabindex="-1" href="${after}" aria-disabled="true">다음</a>
                    </li>
                </c:if>

            </ul>
        </nav>

        <!---pagenation-->

    </div>
</div>

<%@include file="../../views/inc/footbar.jsp"%>


<script src="./assets/js/jquery-3.5.1.min.js"></script>

<script src="./assets/js/bootstrap.bundle.min.js"></script>

<script src="./assets/js/google-maps.js"></script>

<script src="./assets/vendor/wow/wow.min.js"></script>

<script src="./assets/js/theme.js"></script>

</body>
</html>