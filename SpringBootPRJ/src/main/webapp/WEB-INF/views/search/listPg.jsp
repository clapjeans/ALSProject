<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<<<<<<< HEAD
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%
    List<Map<String,Object>> rList =( List<Map<String,Object>>)request.getAttribute("userList");
%>
=======
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
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
            font-style: bold;
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
                            <li class="breadcrumb-item active">Blog</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">스마트한 재활용방법</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="page-section">
    <ul class="menuCategory">
        <h2><span>Category</span></h2>
        <li><a href="/product/list.html?cate_no=75">가연성<span> | </span></a>
        </li>
        <li><a href="/product/list.html?cate_no=75">대형폐기물<span> | </span></a>
        </li>
        <li><a href="/product/list.html?cate_no=75">불연성<span> | </span></a>
        </li>
        <li><a href="/product/list.html?cate_no=75">비닐류<span> | </span></a>
        </li>
        <li><a href="/product/list.html?cate_no=75">기타<span>  </span></a>
        </li>
    </ul>
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
                <button class="btn btn-secondary">Filter <span class="mai-filter"></span></button>
            </div>
        </div>

        <div class="row my-5">
            <div class="col-lg-4 py-3">
                <div class="card-blog">
<<<<<<< HEAD
                    <%for(Map<String, Object> pMap :rList){  %>
=======
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
                    <div class="header">
                        <div class="post-thumb">
                            <img src="../assets/img/trash.jpg" alt="">
                        </div>
                    </div>
                    <div class="body">
<<<<<<< HEAD
                        <h5 class="post-title"><a href="blog-details.html"><%=pMap.get("DICNM")%></a></h5>
                        <div class="post-date"><%=pMap.get("SORTNM")%></div>
                    </div>
                    <%} %>
                </div>
            </div>


=======
                        <h5 class="post-title"><a href="blog-details.html">Source of Content Inspiration</a></h5>
                        <div class="post-date">Posted on <a href="#">27 Jan 2020</a></div>
                    </div>
                </div>
            </div>

>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
            <div class="col-lg-4 py-3">
                <div class="card-blog">
                    <div class="header">
                        <div class="post-thumb">
                            <img src="../assets/img/trash.jpg" alt="">
                        </div>
                    </div>
                    <div class="body">
                        <h5 class="post-title"><a href="blog-details.html">Source of Content Inspiration</a></h5>
                        <div class="post-date">Posted on <a href="#">27 Jan 2020</a></div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 py-3">
                <div class="card-blog">
                    <div class="header">
                        <div class="post-thumb">
                            <img src="../assets/img/trash.jpg" alt="">
                        </div>
                    </div>
                    <div class="body">
                        <h5 class="post-title"><a href="blog-details.html">Source of Content Inspiration</a></h5>
<<<<<<< HEAD

=======
                        <div class="post-date">Posted on <a href="#">27 Jan 2020</a></div>
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
                    </div>
                </div>
            </div>

            <div class="col-lg-4 py-3">
                <div class="card-blog">
                    <div class="header">
                        <div class="post-thumb">
                            <img src="../assets/img/trash.jpg" alt="">
                        </div>
                    </div>
                    <div class="body">
                        <h5 class="post-title"><a href="blog-details.html">Source of Content Inspiration</a></h5>
<<<<<<< HEAD

=======
                        <div class="post-date">Posted on <a href="#">27 Jan 2020</a></div>
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
                    </div>
                </div>
            </div>

            <div class="col-lg-4 py-3">
                <div class="card-blog">
                    <div class="header">
                        <div class="post-thumb">
                            <img src="../assets/img/trash.jpg" alt="">
                        </div>
                    </div>
                    <div class="body">
                        <h5 class="post-title"><a href="blog-details.html">Source of Content Inspiration</a></h5>
<<<<<<< HEAD

=======
                        <div class="post-date">Posted on <a href="#">27 Jan 2020</a></div>
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
                    </div>
                </div>
            </div>

            <div class="col-lg-4 py-3">
                <div class="card-blog">
                    <div class="header">
                        <div class="post-thumb">
                            <img src="../assets/img/trash.jpg" alt="">
                        </div>
                    </div>
                    <div class="body">
                        <h5 class="post-title"><a href="blog-details.html">Source of Content Inspiration</a></h5>
<<<<<<< HEAD

=======
                        <div class="post-date">Posted on <a href="#">27 Jan 2020</a></div>
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
                    </div>
                </div>
            </div>

        </div>
<<<<<<< HEAD
<!---pagenation-->
=======

>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867
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
<<<<<<< HEAD
        <!---pagenation-->


=======
>>>>>>> aca8c8a62c55bf03fab80311b9e0d446ec850867

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