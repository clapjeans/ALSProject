<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    List<Map<String, String>> rList = (List<Map<String, String>>) request.getAttribute("getInfoList");
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
    <style>
        .box{
            position: relative;
            margin: 10px 0;
            padding: 35px;
            border: solid 1px #979fa8;

        }
        .box .box_title {
            margin-bottom: 10px;
            font-size: 25px;
            font-weight: 500;
            color: #1d1d1d;
            word-break: keep-all;
            text-align: center;
        }

        .box.icon .box_wrap:before {
            display: block;
            position: absolute;
            top: 0;
            left: 0;
            width: 100px;
            height: 100px;

            position: static;
            margin: 0 auto 30px;
            background: no-repeat url("../assets/img/trash_icon.png");
        }
        .box.icon .box_wrap:before {
            position: static;
            margin: 0 auto 30px;
        }
        :after, :before {
            display: none;
            content: '';
        }

        .table tr th{
            background-color: #E9E5D6;
            border: #a6a6a6 solid 1px;
        }
        .table td{
            border: #a6a6a6 solid 1px;
        }
        h3, body{
            font-size: 17px;
        }
        .title-section{
            font-size: 25px;
        }
        p{
            text-align: center;
            margin-bottom: 0px;
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
                            <li class="breadcrumb-item active">location</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">구별 배출방법</h1>

                </div>
            </div>
        </div>
    </div>
</header>

<!---페이지 하단-->


    <div class="page-section">

        <div class="container">

            <div class="box icon">
                <div class="box_wrap">
                    <%for (Map<String, String> pMap : rList) { %>
                    <div class="box_title">서울시 <%=CmmUtil.nvl(pMap.get("GU_NAME"))%>&nbsp;<%=CmmUtil.nvl(pMap.get("GU_NUM"))%>쓰레기 배출요령</div>
                    <p style="text-align: center;">관리구역대상지 <%=pMap.get("GU_PLACE")%></p>
                    <p style="text-align: center;">아래와같이 배출요령에따라 배출해주시면됩니다.</p>
                </div>
            </div>

            <h2 class="title-section" style="margin-top: 50px;">배출장소</h2>
            <div class="divider"></div>
            <ul>
                <li><h3 class=""><%=pMap.get("PLACE")%> 배출 부탁드립니다.</h3></li>
                <li><h3>미수거일은 <%=pMap.get("DAYOFF")%> 입니다.</h3></li>
                <li> <h3>문의사항은 관리구역부서 <%=pMap.get("PHONM")%>연락부탁드립니다.</h3></li>

            </ul>



            <h2 class="title-section" style="margin-top: 50px;">생활쓰레기</h2>
            <div class="divider"></div>
            <table class="table responsive">
                <colgroup>
                    <col style="width:8%">
                    <col style="width:20%">

                </colgroup>


                <tbody class="text_center">
                <tr>
                    <th scope="row" data-content="업체명">배출방법</th>
                    <td data-content="소재지"><%=pMap.get("LIFE_WAY")%></td>


                </tr>
                <tr>
                    <th scope="row" data-content="업체명">배출요일</th>
                    <td data-content="소재지"><%=pMap.get("LIFE_DY")%></td>

                </tr>
                <tr>
                    <th scope="row" data-content="업체명">배출시간</th>
                    <td data-content="소재지"><%=pMap.get("LIFE_TM1")%>&nbsp;~&nbsp;<%=pMap.get("LIFE_TM2")%></td>

                </tr>


                </tbody>
            </table>


            <h2 class="title-section" style="margin-top: 50px;">재활용</h2>
            <div class="divider"></div>
            <table class="table responsive">
                <colgroup>
                    <col style="width:8%">
                    <col style="width:20%">

                </colgroup>


                <tbody class="text_center">
                <tr>
                    <th scope="row" data-content="업체명">배출방법</th>
                    <td data-content="소재지"><%=pMap.get("REC_WAY")%></td>


                </tr>
                <tr>
                    <th scope="row" data-content="업체명">배출요일</th>
                    <td data-content="소재지"><%=pMap.get("REC_DY")%></td>

                </tr>
                <tr>
                    <th scope="row" data-content="업체명">배출시간</th>
                    <td data-content="소재지"><%=pMap.get("REC_TM1")%>&nbsp;~&nbsp;<%=pMap.get("REC_TM2")%></td>

                </tr>


                </tbody>
            </table>

            <h2 class="title-section" style="margin-top: 50px;">음식물쓰레기</h2>
            <div class="divider"></div>
            <table class="table responsive">
                <colgroup>
                    <col style="width:8%">
                    <col style="width:20%">

                </colgroup>


                <tbody class="text_center">
                <tr>
                    <th scope="row" data-content="업체명">배출방법</th>
                    <td data-content="소재지"><%=pMap.get("FOOD_WAY")%></td>


                </tr>
                <tr>
                    <th scope="row" data-content="업체명">배출요일</th>
                    <td data-content="소재지"><%=pMap.get("FOOD_DY")%></td>

                </tr>
                <tr>
                    <th scope="row" data-content="업체명">배출시간</th>
                    <td data-content="소재지"><%=pMap.get("FOOD_TM1")%>&nbsp;~&nbsp;<%=pMap.get("FOOD_TM2")%></td>

                </tr>


                </tbody>
            </table>
            <% } %>
        </div> <!-- .container -->



<%@include file="../../views/inc/footbar.jsp"%>

<script src="./assets/js/jquery-3.5.1.min.js"></script>

<script src="./assets/js/bootstrap.bundle.min.js"></script>

<script src="./assets/vendor/wow/wow.min.js"></script>

<script src="./assets/js/theme.js"></script>


</body>
</html>