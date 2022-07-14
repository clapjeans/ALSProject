<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="org.bson.Document" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kopo.poly.dto.BoardDTO" %>

<%

    List<Document> rList = (List<Document>) request.getAttribute("rList");

//게시판 조회 결과 보여주기
    if (rList == null) {
        rList = new ArrayList<Document>();

    }

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
</head>
<body>

<header>


    <%@include file="../../views/inc/navbar.jsp"%>

    <div class="container">
        <div class="page-banner">
            <div class="row justify-content-center align-items-center h-100">
                <div class="col-md-6">
                    <nav aria-label="Breadcrumb">
                        <ul class="breadcrumb justify-content-center py-0 bg-transparent">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active">Blog</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">중고나라</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="page-section">
    <div class="container">
        <div class="row">
            <div class="col-sm-10">
                <form action="#" class="form-search-blog">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <select id="categories" class="custom-select bg-light">
                                <option>All Categories</option>
                                <option value="travel">Travel</option>
                                <option value="lifestyle">LifeStyle</option>
                                <option value="healthy">Healthy</option>
                                <option value="food">Food</option>
                            </select>
                        </div>
                        <input type="text" class="form-control" placeholder="Enter keyword..">
                    </div>
                </form>
            </div>
            <div class="col-sm-2 text-sm-right">
                <button class="btn btn-secondary">검색 <span class="mai-filter"></span></button>
            </div>
        </div>
        <!---사진올려저장하기-->
        <hr>

        <!--반복 for 문 시작 -->
        <%
            for (Document doc : rList) {

        %>
        <div style="margin-top: 30px;" class="blog-item">
            <a class="post-thumb" href="">
                <img src=<%=doc.getString("save_thumfile_path")%> alt="">
            </a>
            <div class="content">
                <p></p>
                <h6 class="post-title"><a href="/boardInfo?seq=<%=doc.getString("seq")%>"><%=doc.getString("title")%></a></h6>
                <div class="meta">
                    <span class="mai-person"></span> <%=doc.getString("user_name")%>
                </div>
            </div>
        </div>

        <% } %>


        <button class="btn btn-secondary" style="float:right;" onclick="location.href='/boardReg'">글쓰기</button>

        <!---페이징 -->
        <nav aria-label="Page Navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active" aria-current="page">
                    <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                </li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>

    </div>
</div> <!-- .container -->



<%@include file="../../views/inc/footbar.jsp"%>

<script src="../assets/js/jquery-3.5.1.min.js"></script>

<script src="../assets/js/bootstrap.bundle.min.js"></script>

<script src="../assets/js/google-maps.js"></script>

<script src="../assets/vendor/wow/wow.min.js"></script>

<script src="../assets/js/theme.js"></script>

</body>
</html>