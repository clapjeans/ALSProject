<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    String user_name = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME"));

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
                    <h1 class="text-center">마이페이지</h1>
                </div>
            </div>
        </div>
    </div>
</header>


<div class="container"  style="margin-top:50px;">
    <h3>Profile</h3>
    <hr>

    <div class="p">




        <div id='profile-upload' style="background-image:url(../../../assets/img/person/User.png)">
            <div class="hvr-profile-img">

            </div>

        </div>



        <div class="tr">



            <label class="label"><h3><%=user_name%>님</h3></label>



        </div>

        <br><br><br><br><br><br><br><br>


        <div style="float: right;">

           &nbsp;&nbsp;<button class="btn btn-secondary" onclick="location.href='/updatePwPage'">비밀번호변경</button>&nbsp;&nbsp;<button class="btn btn-secondary" onclick="location.href='/deleteUser'">회원탈퇴</button>
        </div>

        <br><br><br><br><br>



    </div>


</div>



<%@include file="../../views/inc/footbar.jsp"%>

<script src="../assets/js/jquery-3.5.1.min.js"></script>


<script src="../assets/js/bootstrap.bundle.min.js"></script>


<script src="../assets/js/google-maps.js"></script>

<script src="../assets/vendor/wow/wow.min.js"></script>

<script src="../assets/js/theme.js"></script>



</body>
</html>