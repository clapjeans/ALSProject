<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="kopo.poly.util.EncryptUtil" %>
<%@ page import="kopo.poly.dto.MapDTO" %>
<%@ page import="java.net.URLEncoder" %>

<%
    List<MapDTO> rList = (List<MapDTO>) request.getAttribute("MapList");
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

        .divTable{
            border-top: 2px solid #ccc;
            display: table;
            width: 100%;
            text-align: center;
        }
        .divTableRow {
            display: table-row;
        }
        .divTableHeading {

            display: table-header-group;
        }
        .divTableCell, .divTableHead {
            display: table-cell;
            padding: 10px;
        }
        .divTableHeading {

            display: table-header-group;
            font-weight: bold;
        }
        .divTableFoot {

            display: table-footer-group;
            font-weight: bold;
        }
        .divTableBody {
            display: table-row-group;
        }
        .divTableHead{
            border-bottom: 2px solid #e7e7e7;

        }
        .divTableCell{
            border-bottom: 1px solid #e7e7e7;
        }

        .tablemargin{
            margin-top: 20px;
            margin-bottom: 50px;
        }
   </style>

    <script type="text/javascript">

        function gourl(guplace,gu_name) {
            console.log("guplace : " + guplace);
            console.log("guPlace : " + encodeURI(guplace, "UTF-8"));
            //location.href = “test.jsp?name=” + encodeURI(“한글나라”, “UTF-8″);
            location.href = "/mapInfo?GU_PLACE=" + encodeURI(guplace, "UTF-8") + "&GU_NAME=" + encodeURI(gu_name, "UTF-8");
        }
    </script>


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
                            <li class="breadcrumb-item">Home</li>
                            <li class="breadcrumb-item active">Blog</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">우리동네 재활용</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="page-section">
    <div class="container">
        <h2 class="title-section">검색결과</h2>
        <div class="divider"></div>
        <div class="row">

            <div class="col-sm-10">

                <form action="/mapListInfo" class="form-search-blog" id="key">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <select id="categories" class="custom-select bg-light" name="category">
                                <option value="GU_NAME">GU</option>
                                <option value="GU_PLACE">Dong</option>
                            </select>
                        </div>
                        <input type="text" class="form-control" placeholder="Enter keyword.." name="keyword">
                    </div>
                </form>
            </div>
            <div class="col-sm-2 text-sm-right">
                <button type="submit" form="key" class="btn btn-secondary">Filter <span class="mai-filter"></span></button>
            </div>
        </div>

        <div class="tablemargin">
            <div class="divTable">
                <div class="divTableHeading">
                    <div class="divTableRow">
                        <div class="divTableHead">지역구명</div>
                        <div class="divTableHead">관리구역명</div>
                        <div class="divTableHead">등록일</div>
                    </div>
                </div>
                <%for ( MapDTO mDTO : rList) { %>
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell"><%=mDTO.getGu_name()%></div>
                        <div class="divTableCell" style="width: 50%;"><a href="/mapInfo?GU_PLACE=<%=mDTO.getGu_place()%>&GU_NAME=<%=mDTO.getGu_name()%>"><%=mDTO.getGu_place()%></a></div>
                        <div class="divTableCell"><%=mDTO.getDate()%></div>
                    </div>
                </div>
                <% } %>
            </div> <!--divtable 시작-->
        </div> <!--그냥 div-->

    </div> <!--container-->

    <!-- 페이징 처리 -->
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">

            <!-- 이전 -->
            <c:if test="${paging.currentPage eq 1}">
                <li class="page-item">
                    <a class="page-link no-before" tabindex="-1" aria-disabled="true">이전</a>
                </li>
            </c:if>
            <c:if test="${paging.currentPage ne 1}">
                <c:url var="before" value="/mapListInfo">
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
                    <c:url var="pagination" value="/mapListInfo">
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
                <c:url var="after" value="/mapListInfo">
                    <c:param name="currentPage" value="${paging.currentPage + 1}"/>
                </c:url>

                <li class="page-item">
                    <a class="page-link" tabindex="-1" href="${after}" aria-disabled="true">다음</a>
                </li>
            </c:if>

        </ul>
    </nav>


</div><!---pagenation-->
<%@include file="../../views/inc/footbar.jsp"%>

<script src="./assets/js/jquery-3.5.1.min.js"></script>

<script src="./assets/js/bootstrap.bundle.min.js"></script>

<script src="./assets/js/google-maps.js"></script>

<script src="./assets/vendor/wow/wow.min.js"></script>

<script src="./assets/js/theme.js"></script>

</body>
</html>