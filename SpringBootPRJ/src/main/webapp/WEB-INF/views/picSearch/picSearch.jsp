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
    <link rel="stylesheet" href="../assets/css/style.css">
    <script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>

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
                            <li class="breadcrumb-item active">Image</li>
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
        <div style="margin-left: 24px;">
            <h3>사진검색방법</h3>
            <h6>1. start 버튼을 먼저 눌러주세요 (처음 한번만 눌러주시면 됩니다)</h6>
            <h6>2. 검색할 사진을 업로드해주세요</h6>
            <h6>3. predict버튼을 눌러주세요</h6>
        </div>
        <div class="file-upload">


            <div class="image-upload-wrap">
                <input class="file-upload-input" type="file" onchange="readURL(this);" accept="image/*">
                <div class="drag-text">
                    <h3> 사진을 업로드 해주세요</h3>
                </div>
            </div>
            <div class="file-upload-content">
                <img class="file-upload-image" id="uploadimage" src="#" alt="your image">
                <div class="image-title-wrap">
                    <button type="button" onclick="removeUpload()" class="remove-image">Remove <span
                            class="image-title">Uploaded Image</span></button>
                </div>
            </div>
        </div>
        <div style="float: right;">
            <input type="button" value="Start" class="btn btn-primary" onclick="init()">
            <input type="button" value="Predict" class="btn btn-primary" onclick="predict()">
        </div>
        <div class="search">
            <div id="label-container"></div>
        </div>
    </div> <!-- .container -->
</div> <!-- .page-section -->


<%@include file="../../views/inc/footbar.jsp" %>

<!---file upload javascript-->
<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.image-upload-wrap').hide();
                $('.file-upload-image').attr('src', e.target.result);
                $('.file-upload-content').show();
                $('.image-title').html(input.files[0].name);
            };

            reader.readAsDataURL(input.files[0]);

        } else {
            removeUpload();
        }
    }

    function removeUpload() {
        $('.file-upload-input').replaceWith($('.file-upload-input').clone());
        $('.file-upload-content').hide();
        $('.image-upload-wrap').show();
    }

    $('.image-upload-wrap').bind('dragover', function () {
        $('.image-upload-wrap').addClass('image-dropping');
    });
    $('.image-upload-wrap').bind('dragleave', function () {
        $('.image-upload-wrap').removeClass('image-dropping');
    });
</script>
<!-- 티처블 머신 js 가져오는 부분 -->
<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/image@0.8/dist/teachablemachine-image.min.js"></script>
<script type="text/javascript" src="../assets/js/teach.js"></script>

<script src="./assets/js/bootstrap.bundle.min.js"></script>

<script src="./assets/vendor/wow/wow.min.js"></script>

<script src="./assets/js/theme.js"></script>

</body>
</html>