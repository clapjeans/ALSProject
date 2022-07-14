<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="kopo.poly.dto.DicDTO" %>
<%
    List<DicDTO> rList = (List<DicDTO>) request.getAttribute("InfoList");

%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SeoGram - SEO Agency Template</title>
    <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
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
    <%@include file="../../views/inc/navbar.jsp" %>

    <div class="container">
        <div class="page-banner">
            <div class="row justify-content-center align-items-center h-100">
                <div class="col-md-6">
                    <nav aria-label="Breadcrumb">
                        <ul class="breadcrumb justify-content-center py-0 bg-transparent">
                            <li class="breadcrumb-item">Method</li>
                            <li class="breadcrumb-item active">info</li>
                        </ul>
                    </nav>
                    <h1 class="text-center">재활용 분리배출 사전</h1>

                </div>
            </div>
        </div>
    </div>
</header>


<div class="page-section">

    <div class="container">
        <%for (DicDTO pDTO: rList) { %>
        <div class="box icon">
            <div class="box_wrap">

                <div class="box_title"><%=CmmUtil.nvl(pDTO.getDicnm())%></div>
               <p > <%=CmmUtil.nvl(pDTO.getSortnm())%>분리수거 방법 아래와같이 해주시면됩니다.</p>
            </div>
        </div>

        <h2 class="title-section" style="margin-top: 50px;"><%=CmmUtil.nvl(pDTO.getDicnm())%>
        </h2>
        <div class="divider"></div>
        <ul class="bu">
            <li><%=CmmUtil.nvl(pDTO.getExp())%>
            </li>

        </ul>


        <h2 class="title-section" style="margin-top: 50px;">배출 방법</h2>
        <div class="divider"></div>
        <ul class="bu" >
            <li ><%=CmmUtil.nvl(pDTO.getMethod())%></li>
        </ul>


        <h2 class="title-section" style="margin-top: 50px;"> 주의사항</h2>
        <div class="divider"></div>
        <% if(!CmmUtil.nvl(pDTO.getCare()).equals("")){ %>
        <ul class="bu">
            <li><%=CmmUtil.nvl(pDTO.getCare())%>
            </li>
        </ul>
        <%}%>
        <input type="hidden" value="<%=pDTO.getDicnm()%>" id="dicnm">
        <input type="hidden" value="<%=pDTO.getMethod()%>" id="method">
        <% } %>

        <button class="btn btn-secondary"  style="float: right" id="create-kakaotalk-sharing-btn">공유하기</button>
    </div>

</div> <!-- .container -->


<%@include file="../../views/inc/footbar.jsp" %>

<script src="./assets/js/jquery-3.5.1.min.js"></script>

<script src="./assets/js/bootstrap.bundle.min.js"></script>

<script src="./assets/js/google-maps.js"></script>

<script src="./assets/vendor/wow/wow.min.js"></script>

<script src="./assets/js/theme.js"></script>
<script type='text/javascript'>

    var link = document.location.href;
    var method = document.getElementById('method').value;
    var DicName = document.getElementById('dicnm').value;
    console.log(method);
    console.log(DicName);

    Kakao.init('7a41e2e2fe078d964dd06a6ada8cd641');

    Kakao.Link.createDefaultButton({
        container: '#create-kakaotalk-sharing-btn',
        objectType: 'feed',
        content: {
            title: DicName,
            description: method,
            imageUrl:
                'https://post-phinf.pstatic.net/MjAyMDA0MDhfMjA5/MDAxNTg2MzA4NzE2MDk4.o-6qInDi3g5CTRDPTQYyI8QZxbqVavoIFPn4-GYpZ8Ig.Nokb0Oe4p8A_iD2dcatNBzrqqDZiGEwN8v21FeGE04Eg.PNG/IDlcv2ZQl7nYgJtZSRAftgrWLspo.jpg?type=w400',
            link: {
                mobileWebUrl: link,
                webUrl: link,
            },
        },

        buttons: [
            {
                title: '자세히 보기',
                link: {
                    mobileWebUrl: link,
                    webUrl: link,
                },
            },

        ],
    })

</script>

</body>


</html>