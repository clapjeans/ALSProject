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
    
   <%@include file="inc/navbar.jsp" %>
    
    <div class="container">
        <div class="page-banner home-banner">
            <div class="row align-items-center flex-wrap-reverse h-100">
                <div class="col-md-6 py-5 wow fadeInLeft">
                    <h1 class="mb-4">Let's Learn how to recycle.</h1>
                    <p class="text-lg text-grey mb-5"></p>
                    <div class="btn btn-primary btn-split">여기에 날씨 넣을꺼다 위치 미세먼지다!!</div>
                </div>
                <div class="col-md-6 py-5 wow zoomIn">
                    <div class="img-fluid text-center">
                        <img src="../assets/img/banner_image_1.png" alt="">
                    </div>
                </div>
            </div>

        </div>
    </div>
</header>

<div class="page-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <div class="card-service wow fadeInUp">
                    <div class="header">
                        <img src="../assets/img/services/service-1.png" alt="">
                    </div>
                    <div class="body">
                        <h5 class="text-secondary">키워드검색 재활용분류방법</h5>
                        <p>검색을 통해 손쉽게 분리배출방법을 검색 할 수 있습니다.</p>
                        <a href="/search" class="btn btn-primary"> Search </a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="card-service wow fadeInUp">
                    <div class="header">
                        <img src="../assets/img/services/service-2.png" alt="">
                    </div>
                    <div class="body">
                        <h5 class="text-secondary">사진검색 재활용분류방법</h5>
                        <p>사진검색을통해 분리배출방법을 검색 할 수 있습니다.</p>
                        <a href="/picSearch" class="btn btn-primary">Image Search</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="card-service wow fadeInUp">
                    <div class="header">
                        <img src="../assets/img/services/service-3.png" alt="">
                    </div>
                    <div class="body">
                        <h5 class="text-secondary">우리동네 배출방법</h5>
                        <p>동네별로 분리배출 방법을 자세히 확인 할 수 있습니다.</p>
                        <a href="mapSearch" class="btn btn-primary">Gu-Search</a>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- .container -->
</div> <!-- .page-section -->



<%@include file="inc/footbar.jsp"%>

</body>
</html>