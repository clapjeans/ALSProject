<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.BoardDTO" %>
<%@ page import="kopo.poly.dto.ImgDTO" %>
<%@ page import="kopo.poly.dto.CommetDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    BoardDTO rDTO = (BoardDTO) request.getAttribute("rDTO");
    ImgDTO pDTO = (ImgDTO) request.getAttribute("IDTO");
    String ss_user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL"));
    String seq = CmmUtil.nvl((String) request.getParameter("seq"));


    List<CommetDTO> rList = (List<CommetDTO>) request.getAttribute("rList");

//게시판 조회 결과 보여주기
    if (rList == null) {
        rList = new ArrayList<CommetDTO>();

    }


//본인이 작성한 글만 수정 가능하도록 하기(1:작성자 아님 / 2: 본인이 작성한 글 / 3: 로그인안함)
    int edit = 1;

//로그인 안했다면....
    if (ss_user_email.equals("")) {
        edit = 3;

//본인이 작성한 글이면 2가 되도록 변경
    } else if (ss_user_email.equals(CmmUtil.nvl(rDTO.getUser_email()))) {
        edit = 2;


    }

    System.out.println("user_email : " + CmmUtil.nvl(rDTO.getUser_email()));
    System.out.println("ss_user_email : " + ss_user_email);
    System.out.println("edit : " + edit);


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <link rel="stylesheet" href="../assets/css/chat.css">

    <style>
        hr {
            border: 2px solid #798777;
        }

        .textbox {
            margin-top: 100px;
            margin-bottom: 50px;
            clear: both;
        }

        textarea {
            width: 85%;
            height: 100px;
            margin-bottom: 20px;

        }

        .blog-item {
            clear: right;
        }

        .commentheader {
            margin-top: 5px;
            margin-bottom: 5px;
            font-weight: 900;
        }

        p {
            word-break: normal;
            font-size: 20px;
        }

        .comment {
            margin-top: 20px;
        }

        .textbox::after {
            clear: both;
            display: block;
            content: '';
        }

    </style>
    <script type="text/javascript">

        //수정하기
        function doEdit() {
            if ("<%=edit%>" == 2) {
                location.href = "/boardEditInfo?seq=<%=CmmUtil.nvl(rDTO.getSeq())%>";

            } else if ("<%=edit%>" == 3) {
                alert("로그인 하시길 바랍니다.");

            } else {
                alert("본인이 작성한 글만 수정 가능합니다.");

            }
        }


        //삭제하기
        function doDelete() {
            if ("<%=edit%>" == 2) {
                if (confirm("작성한 글을 삭제하시겠습니까?")) {
                    location.href = "/boardDelete?Seq=<%=CmmUtil.nvl(rDTO.getSeq())%>&file=<%=pDTO.getSave_file_name()%>&sfile=<%=pDTO.getSave_sfile_name()%>";

                }

            } else if ("<%=edit%>" == 3) {
                alert("로그인 하시길 바랍니다.");

            } else {
                alert("본인이 작성한 글만 삭제 가능합니다.");

            }
        }


        //이전페이지로 돌아가기
        function back() {
            history.go(-1);
        }


    </script>


</head>
<body>


<header>
    <!--nav bar-->
    <%@include file="../../views/inc/navbar.jsp" %>
    <!--nav bar-->

    <div class="container">
        <div class="page-banner">
            <div class="row justify-content-center align-items-center h-100">
                <div class="col-md-6">
                    <nav aria-label="Breadcrumb">
                        <ul class="breadcrumb justify-content-center py-0 bg-transparent">
                            <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                            <li class="breadcrumb-item active">About</li>
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


        <h1 class="post-title"><%=rDTO.getTitle()%>
        </h1></span>
        </h1></span>
        <div class="post-meta">
            <div class="post-date">
                <h6><%=rDTO.getReg_dt()%>&nbsp;&nbsp;&nbsp; <%=rDTO.getUser_name()%>님</h6><a
                    href="/chatingroom?name=<%=rDTO.getUser_name()%>&seq=<%=seq%>"><%=rDTO.getUser_name()%>님과 채팅하기</a>
            </div>

            <hr>
            <div class="post-content" style="width: 100%;height: 700px;">

                <div id="screen3">
                    <div id="img">
                        <img src=<%=pDTO.getSave_file_path()%>>
                    </div>
                    <div id="content">
                        <p><%=rDTO.getContents()%>
                        </p>
                    </div>
                </div>
            </div>
            <hr>
            <div class="comments" style="float: right;">
                <button class="btn btn-secondary" onclick="doEdit()">수정</button>&nbsp;&nbsp;
                <button class="btn btn-secondary" onclick="doDelete()">삭제</button>&nbsp;&nbsp;
                <button class="btn btn-secondary" onclick="back()">목록</button>
            </div>
            <div class="textbox">
                <h2 style="color: #798777;">Comments</h2>
                <textarea class="form-control" name="comment" id="comment" required="required"> </textarea>
                <input type="hidden" id="seq" value="<%=rDTO.getSeq()%>">
                <button onclick="insertComment()" name="comments" class="btn btn-secondary" style="float:right">댓글등록
                </button>

            </div>

            <div class="comment">
                <% for (CommetDTO cDTO : rList) { %>

                <div class="blog-item" id="comment_<%=cDTO.getCommentseq()%>">

                    <a class="post-thumb" href="javascript:void(0)" style="background-color: white;">
                        <img src="../assets/img/blog/human.png" alt="">
                    </a>
                    <div class="content" >
                        <div class="commentheader">
                            <span style="font-size: 20px;"><%=CmmUtil.nvl(cDTO.getUser_name())%></span><span>
                            <a href="javascript:deleteComment('<%=cDTO.getComments()%>','<%=cDTO.getCommentseq()%>','<%=cDTO.getUser_email()%>');"
                               style="" value="<%=CmmUtil.nvl(cDTO.getComments())%>">삭제</a> <a
                                href="javascript:editComment('<%=cDTO.getComments()%>','<%=cDTO.getUser_name()%>','<%=cDTO.getReg_dt()%>','<%=cDTO.getCommentseq()%>','<%=cDTO.getUser_email()%>');"
                                style="">수정</a> </span><br>
                            <span><h7><%=CmmUtil.nvl(cDTO.getReg_dt())%></h7> </span>
                        </div> <!---commentheader-->
                        <h6><%=CmmUtil.nvl(cDTO.getComments())%></h6>

                    </div> <!--conteont-->
                </div> <!--blogitem-->

                <% } %>
            </div> <!---comment-->

        </div><!-- .container -->


    </div> <!-- .section -->


    <!--footer-->
    <%@include file="../../views/inc/footbar.jsp" %>

    <script src="../assets/js/jquery-3.5.1.min.js"></script>

    <script src="../assets/js/bootstrap.bundle.min.js"></script>

    <script src="../assets/js/google-maps.js"></script>

    <script src="../assets/vendor/wow/wow.min.js"></script>

    <script src="../assets/js/theme.js"></script>

</body>
<script>

    function insertComment() {

        console.log(<%=edit%>)
        if ("<%=edit%>" == 3) {
            alert("로그인 하시길 바랍니다.");
        } else {
            console.log("comment" + document.getElementById("comment").value)
            let comment = document.getElementById("comment").value;
            let seq = document.getElementById("seq").value;


            $.ajax({
                url: "/insertCommentAJAX",
                type: "post",
                dataType: "JSON",
                data: {
                    "comment": comment,
                    "seq": seq

                },
                success: function () {

                    alert("댓글 등록을 하였습니다.");
                    document.getElementById("comment").value = "";//함수비워주기

                    getCommentAjax(); //비동기식으로 댓글 가져오기
                },
                error: function () {
                    alert("댓글 등록을 실패하였습니다.");
                    document.getElementById("comment").value = "";//함수비워주기

                }
            });

        }
    }

    //AJax으로 댓글 가져오기
    function getCommentAjax() {
        let seq = document.getElementById("seq").value;
        $.ajax({
            url: "/mCommentGetList",
            type: "get",
            dataType: "JSON",
            data: {
                "seq": seq

            },
            success: function (result) {
                console.log(result);
                var output = $(".comment");
                console.log("outputs : " + output);


                var html = '';
                for (let i = 0; i < result.length; i++) {
                    console.log(result[i].comments)
                    console.log(result[i].user_name)
                    var word = result[i].comments+"'"+','+"'"+result[i].user_name+"'"+','+"'"+result[i].reg_dt+"'"+','+"'"+result[i].commentseq;
                    console.log(word);

                    html += ' <div class="blog-item" id="comment_'+result[i].commentseq+'"><a class="post-thumb" href="javascript:void(0)" style="background-color: white;">' +
                        '  <img src="../assets/img/blog/human.png" alt=""></a>' +
                        '<div class="content">' +
                        ' <div class="commentheader">' +
                        '  <span style="font-size: 20px;">' + result[i].user_name + '</span>&nbsp;<span><a href="javascript:void(0)"  onclick="deleteComment(\'' + result[i].comments + '\')">삭제</a>&nbsp;<a href="javascript:void(0)" onclick="editComment(\'' + word + '\')">수정</a></span><br><span><h7>' + result[i].reg_dt + '</h7> </span> </div>' +
                        '<h6>' + result[i].comments + '</h6>  </div> </div>';


                }

                output.html(html);

            },
        });
    }


    //댓글 삭제 버튼
    function deleteComment(word) {
        console.log("CHeck")

        if ("<%=edit%>" == 3) {
            alert("로그인 하시길 바랍니다.");
        } else {
            if (confirm("삭제 하시겠습니까?")) {
                //댓글 삭제를 하기위해 댓글 번호, 글 번호, 댓글 내용, 그리고 게시글 세부 페이지로 포워딩 하기 위해 페이지 관련 값들을 변수에 저장한다.
                let comment = word;
                console.log(word)
                let seq = document.getElementById("seq").value;
                $.ajax({
                    url: "/deleteCommentAJAX",
                    type: "post",
                    dataType: "JSON",
                    data: {
                        "comment": comment,
                        "seq": seq
                    },
                    success: function (json) {

                        var checkRes = json.res;

                        if (checkRes === 1) {
                            alert("댓글이 삭제되었습니다.")
                        } else if (checkRes === 2) {
                            alert("서버오류 문제로 삭제에 실패하였습니다.")
                        }
                        getCommentAjax(); //비동기식으로 댓글 가져오기
                    }
                });


            }

        }

    }

    //댓글 수정하기
    function editComment(word,name,reg,comseq) {
        console.log("댓글을 수정하는 함수입니다.");
        console.log(word);
        console.log(name);
        console.log(reg);
        console.log(comseq);

        let put = "comment_"+comseq

        var html = '';
        html += '<a class="post-thumb" href="javascript:void(0)" style="background-color: white;">' +
            '  <img src="../assets/img/blog/human.png" alt=""></a>' +
            '<div class="content">' +
            '<div class="commentheader">' +
            '<span style="font-size: 20px;">' + name + '</span>&nbsp;<span><a href="javascript:void(0)" onclick="updateComment(\'' +comseq + '\')">수정확인</a></span><br><span><h7>' + reg + '</h7> </span> </div>'+
            '<input type=text id="updateComment"value="' +word +'"></div>';

        $( '#'+put ).html( html);




    }
  function updateComment(comseq){
      let updateComment = document.getElementById("updateComment").value;
      console.log(updateComment);
      console.log(comseq);

      $.ajax({
          url: "/updateCommentAJAX",
          type: "post",
          dataType: "JSON",
          data: {
              "updateComment": updateComment,
              "comseq": comseq

          },
          success: function (json) {
                console.log("수정 왜안되죠?");
                console.log(json);
              if (json === 1) {
                  alert("댓글이 수정되었습니다.")
                  getCommentAjax(); //비동기식으로 댓글 가져오기
              } else if (json === 2) {
                  alert("서버오류 문제로 수정에 실패하였습니다.")
                  getCommentAjax(); //비동기식으로 댓글 가져오기
              }

          },
          error: function (){
              alert("댓글 수정 실패");
          }

      });

  }


</script>
</html>