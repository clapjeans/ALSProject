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


    <link rel="stylesheet" href="./assets/css/mapinfo.css">

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7a41e2e2fe078d964dd06a6ada8cd641"></script>
    <script src="./assets/js/jquery-3.6.0.min.js"></script>

</head>

<!-- Back to top button -->
<div class="back-to-top"></div>

<header>
    <%@include file="../../views/inc/navbar.jsp" %>
    <div class="container">
        <div class="page-banner">
            <div class="row justify-content-center align-items-center h-100">
                <div class="col-md-6">
                    <nav aria-label="Breadcrumb">
                        <ul class="breadcrumb justify-content-center py-0 bg-transparent">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active">/logcation</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">구별 배출방법 검색</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="container">
    <!-- 지도를 표시할 div 입니다 -->
    <div id="map" style="width:100%;height:550px;"></div>

</div>


<!--검색창-->
<div class="container" style="margin-bottom: 30px;">
 <h2 class="title-section" style="margin-top: 50px;">지역검색&nbsp;&nbsp;&nbsp;<a  style="font-size: 20px;"href="javascript:void(0);">현재위치 Click me!</a></h2> </h2>
    <div class="divider1"></div>
    <div class="row">
        <div class="col-sm-10">
            <form action="#" class="form-search-blog">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <select id="categories" class="custom-select bg-light" name="category">
                            <option value="GU">GU</option>
                            <option value="dong">Dong</option>

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

<%@include file="../../views/inc/footbar.jsp" %>
<%@include file="maphead.jsp"%>

<script src="./assets/js/bootstrap.bundle.min.js"></script>





</body>
</html>