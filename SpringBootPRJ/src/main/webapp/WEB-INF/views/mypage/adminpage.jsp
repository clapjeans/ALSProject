<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.UserInfoDTO" %>
<%@ page import="kopo.poly.util.EncryptUtil" %>

<%
    List<UserInfoDTO> rList =(List<UserInfoDTO>)request.getAttribute("rList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <meta name="copyright" content="MACode ID, https://macodeid.com/">

    <title>SeoGram - SEO Agency Template</title>

    <link rel="stylesheet" href="../assets/css/maicons.css">

    <link rel="stylesheet" href="../assets/css/bootstrap.css">

    <link rel="stylesheet" href="../assets/vendor/animate/animate.css">

    <link rel="stylesheet" href="../assets/css/theme.css">
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
    <nav class="navbar navbar-expand-lg navbar-light bg-white sticky" data-offset="300">
        <div class="container">
            <a href="#" class="navbar-brand">Seo<span class="text-primary">Gram.</span></a>

            <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapsed" id="navbarContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="about.html">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="service.html">Services</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="blog.html">Blog</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="contact.html">Contact</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary ml-lg-2" href="#">Free Analytics</a>
                    </li>
                </ul>
            </div>

        </div>
    </nav>

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





        </div>
        <!---사진올려저장하기-->
        <hr>
        <% for(UserInfoDTO rDTO:rList){ %>
        <div style="margin-top: 30px;" class="blog-item">
            <a class="post-thumb" href="">
                <img src=<%=rDTO.getSave_thumfile_path()%> alt="">
            </a>
            <div class="content">
                <h6 class="post-title"><a href="#"><%=EncryptUtil.decAES128CBC(CmmUtil.nvl(rDTO.getUser_id()))%></a></h6>
                <div class="meta">
                    <span class="mai-person"></span> <%=CmmUtil.nvl(rDTO.getUser_name())%>
                </div>
            </div>
        </div>
        <% }%>





        <button class="btn btn-secondary" style="float:right;">글쓰기</button>
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
</div>

<footer class="page-footer bg-image" style="background-image: url(../assets/img/world_pattern.svg);">

</footer>

<script src="../assets/js/jquery-3.5.1.min.js"></script>

<script src="../assets/js/bootstrap.bundle.min.js"></script>

<script src="../assets/js/google-maps.js"></script>

<script src="../assets/vendor/wow/wow.min.js"></script>

<script src="../assets/js/theme.js"></script>

</body>
</html>