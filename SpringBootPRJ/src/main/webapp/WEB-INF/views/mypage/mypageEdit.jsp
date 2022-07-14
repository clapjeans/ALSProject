<%@ page import="kopo.poly.dto.UserInfoDTO" %>
<%@ page import="kopo.poly.dto.OcrDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.util.EncryptUtil" %>

<%
    UserInfoDTO rDTO = (UserInfoDTO) request.getAttribute("rDTO");

//공지글 정보를 못불러왔다면, 객체 생성
    if (rDTO == null) {
        rDTO = new UserInfoDTO();

    }
   OcrDTO fDTO = (OcrDTO) request.getAttribute("fDTO");
    if (fDTO == null) {
        fDTO = new OcrDTO();

    }
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
    <link rel="stylesheet" href="../../../assets/css/mypage.css">

    <style>


        hr{
            border: 2px solid #798777;
        }
    </style>
    <script>
        //이전페이지로 돌아가기
        function back(){
            history.go(-1);
        }

        //파일보내기
        function uploadFile() {
            var imageInput  = $("#getval")[0];

            console.log("imageInput: ", imageInput .files)
            console.log( "sfilenaem"+ document.getElementById("save_sfile_name").value )

            if(imageInput.files.length === 0){
                alert("사진을 선택해주세요");
                return;
            }

            var form = $('#uploadForm')[0];
            const data = new FormData(form);


                $.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: "/imageupload",
                    data:data,
                    processData: false,
                    contentType: false,
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        console.log(data);
                        document.getElementById("save_file_name").value=data.saveFileName;
                        document.getElementById("save_sfile_name").value=data.save_sfile_name;
                        alert('등록이 성공하였습니다.');
                    },
                    error: function () {

                        alert('등록이 실패하였습니다.');
                    }
                });
            }


    </script>

</head>

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
                    <li class="nav-item">
                        <a class="nav-link" href="blog.html">Blog</a>
                    </li>
                    <li class="nav-item active">
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
                            <li class="breadcrumb-item active">Contact</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">마이페이지</h1>
                </div>
            </div>
        </div>
    </div>
</header>


<div class="container"  style="margin-top:50px;">
    <h2>마이페이지</h2>
    <hr>

    <div class="p">



            &nbsp;<button class="btn btn-secondary"   onclick="uploadFile()">사진등록</button>
            <div id='profile-upload' style="background-image:url(<%=fDTO.getSave_file_path()%>)">
                <div class="hvr-profile-img">

                    <form id="uploadForm" enctype="multipart/form-data"  >
                    <input type="file"  id='getval'  class="upload" name="fileUplod"  >
                    <input type="hidden"  id="save_file_name" name="save_file_name" value="<%=CmmUtil.nvl(fDTO.getSave_file_name())%>">
                    <input type="hidden"  id="save_sfile_name" name="save_sile_name" value="<%=CmmUtil.nvl(fDTO.getSave_sfile_name())%>">
                    </form>
                    <div class="icon">
                        <div class="camera4"><span></span></div>
                    </div>
                </div>

            </div>



        <form action="/UpdateMyPage" method="post" id="key" >

        <div class="tr">




            <label class="label" for="input">NAME</label>
            <input class="input" type="text" id="input"  required="required" value="<%=CmmUtil.nvl(rDTO.getUser_name())%>" name="user_name">


            <label class="label" for="input">EMAIL</label>
            <input class="input" type="text" id="input"   required="required" value="<%=EncryptUtil.decAES128CBC(CmmUtil.nvl(rDTO.getEmail()))%>" name="user_email">

        </div>

        <br><br><br><br>

        <label class="label" for="input">addr1</label>
        <input class="input e" type="text" id="input"   required="required" value="<%=CmmUtil.nvl(rDTO.getAddr1())%>" name="addr1" >

        <label class="label" for="input">addr2</label>
        <input class="input e" type="text" id="input"  required="required" value="<%=CmmUtil.nvl(rDTO.getAddr2())%>" name="addr2">

        </form>

        <div style="float: right;">

            &nbsp;<button class="btn btn-secondary" onclick="back()" >목록</button>&nbsp;&nbsp;<button  type="submit" form="key" class="btn btn-secondary">변경</button>
        </div>

        <br><br><br><br>

    </div>


</div>



<footer class="page-footer bg-image" style="background-image: url(../../../assets/img/world_pattern.svg);">
    <div class="container">

    </div>
</footer>




<script src="../assets/js/jquery-3.5.1.min.js"></script>

<script src="../assets/js/bootstrap.bundle.min.js"></script>
<script src="../../../assets/js/mypage.js"></script>

<script src="../assets/js/google-maps.js"></script>

<script src="../assets/vendor/wow/wow.min.js"></script>

<script src="../assets/js/theme.js"></script>


</body>
</html>