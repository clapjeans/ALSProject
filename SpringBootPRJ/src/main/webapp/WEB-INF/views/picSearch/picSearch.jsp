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
                            <li class="breadcrumb-item active">About</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">이미지 검색</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="page-section">
    <div class="container">

        <div class="img-fluid py-3 text-center">
            <img src="../assets/img/about_frame.png" alt="">

        </div>
        <div style="float: right;">
            <input type="submit" value="Search" class="btn btn-primary">
        </div>
    </div>

    <div class="container" style="margin-top: 30px;">
        <h2>휴지</h2>

        <h2 class="title-section" style="margin-top: 50px;">버리는방법</h2>
        <div class="divider"></div>

        <ul class="bu">
            <li>2021.12.25.부터 단독주택·상가는 매주 목요일 폐비닐·투명페트병만 배출해주세요.</li>
            <li>공동주택에서는 투명페트병을 유색페트병·플라스틱과 구분해 분리배출해주세요.</li>
        </ul>




    </div>


</div> <!-- .container -->
</div> <!-- .page-section -->


<%@include file="../../views/inc/footbar.jsp"%>

<script src="./assets/js/jquery-3.5.1.min.js"></script>

<script src="./assets/js/bootstrap.bundle.min.js"></script>

<script src="./assets/js/google-maps.js"></script>

<script src="./assets/vendor/wow/wow.min.js"></script>

<script src="./assets/js/theme.js"></script>

</body>
</html>