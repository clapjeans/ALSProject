<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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


        .map{ width: 800px; height: 600px;}
        iframe { width: 100%; height: 100%; border:0; }
        .divider1 {
            display: block;
            margin-top: 16px;
            margin-bottom: 20px;
            width: 100%;
            height: 4px;
            border-radius: 40px;
            background: #283c86;
            background: -webkit-linear-gradient(to right, #45a247, #283c86);
            background: linear-gradient(to right, #45a247, #283c86);
        }

    </style>

</head>

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
                            <li class="breadcrumb-item active">Contact</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">Contact Us</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="container" >

    <div style="margin:0 auto; text-align:center">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d25306.72469827348!2d126.87882765!3d37.54703874999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b4552b993615f%3A0xa96d9d254b05ae18!2z7ZWY64qY6rO17JuQ!5e0!3m2!1sko!2skr!4v1645795193343!5m2!1sko!2skr" width="700" height="500" style="border:0;" allowfullscreen="false" loading="lazy" class="map" ></iframe>
    </div>
</div>

<!--검색창-->
<div class="container" style="margin-bottom: 30px;">
    <h2 class="title-section" style="margin-top: 50px;">지역검색</h2>
    <div class="divider1"></div>
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

</div>

     <%@include file="../../views/inc/footbar.jsp"%>
<script src="./assets/js/jquery-3.5.1.min.js"></script>

<script src="./assets/js/bootstrap.bundle.min.js"></script>

<script src="./assets/js/google-maps.js"></script>

<script src="./assets/vendor/wow/wow.min.js"></script>

<script src="./assets/js/theme.js"></script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAIA_zqjFMsJM_sxP9-6Pde5vVCTyJmUHM&callback=initMap"></script>

</body>
</html>