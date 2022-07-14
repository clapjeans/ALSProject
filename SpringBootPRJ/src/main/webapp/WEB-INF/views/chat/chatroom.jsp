<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kopo.poly.dto.ChatDTO" %>
<%
    ChatDTO rDTO = (ChatDTO) request.getAttribute("pDTO"); //사용자이름 룸키 담아옴
    String ss_user_name = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME"));
    List<ChatDTO> rList = (List<ChatDTO>) request.getAttribute("rList");

//게시판 조회 결과 보여주기
    if (rList == null) {
        rList = new ArrayList<ChatDTO>();

    }
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
</head>


<style>
    :root {
        --body-bg: #BDD2B6;
        --msger-bg: #fff;
        --border: 2px solid #ddd;
        --left-msg-bg: #ececec;
        --right-msg-bg: #798777;
    }

    html {
        box-sizing: border-box;
    }

    *,
    *:before,
    *:after {
        margin: 0;
        padding: 0;
        box-sizing: inherit;
    }

    body {

        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #E9E5D6;
        font-family: Helvetica, sans-serif;
    }

    .msger {
        display: flex;
        flex-flow: column wrap;
        justify-content: space-between;
        width: 100%;
        max-width: 867px;
        margin: 25px 10px;
        height: calc(100% - 50px);
        border: var(--border);
        border-radius: 30px;
        background: var(--msger-bg);
        box-shadow: 0 15px 15px -5px rgba(0, 0, 0, 0.2);
    }

    .msger-header {
        display: flex;
        justify-content: space-between;
        padding: 10px;
        border-bottom: var(--border);
        background: #eee;
        color: #666;
        border-top-left-radius: 30px;
        border-top-right-radius: 30px;
    }

    .msger-chat {
        flex: 1;
        overflow-y: auto;
        padding: 10px;
    }

    .msger-chat::-webkit-scrollbar {
        width: 6px;
    }

    .msger-chat::-webkit-scrollbar-track {
        background: #ddd;
    }

    .msger-chat::-webkit-scrollbar-thumb {
        background: #bdbdbd;
    }

    .msg {
        display: flex;
        align-items: flex-end;
        margin-bottom: 10px;
    }

    .msg:last-of-type {
        margin: 0;
    }

    .msg-img {
        width: 50px;
        height: 50px;
        margin-right: 10px;
        background: #ddd;
        background-repeat: no-repeat;
        background-position: center;
        background-size: cover;
        border-radius: 50%;
    }

    .msg-bubble {
        max-width: 450px;
        padding: 15px;
        border-radius: 30px;
        background: var(--left-msg-bg);
    }

    .msg-info {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }

    .msg-info-name {
        margin-right: 10px;
        font-weight: bold;
    }

    .msg-info-time {
        font-size: 0.85em;
    }

    .left-msg .msg-bubble {
        border-bottom-left-radius: 0;
    }

    .right-msg {
        flex-direction: row-reverse;
    }

    .right-msg .msg-bubble {
        background: var(--right-msg-bg);
        color: #fff;
        border-bottom-right-radius: 0;
    }

    .right-msg .msg-img {
        margin: 0 0 0 10px;
    }

    .msger-inputarea {
        display: flex;
        padding: 10px;
        border-top: var(--border);
        background: #eee;
        border-bottom-left-radius: 30px;
        border-bottom-right-radius: 30px;

    }

    .msger-inputarea * {
        padding: 10px;
        border: none;
        border-radius: 3px;
        font-size: 1em;
    }

    .msger-input {
        flex: 1;
        background: #ddd;
    }

    .msger-send-btn {
        margin-left: 10px;
        background: #798777;
        color: #fff;
        font-weight: bold;
        cursor: pointer;

    }

    .msger-send-btn:hover {
        background: #F8EDE3;
    }


</style>
<script>
    //이전페이지로 돌아가기
    function back() {
        history.go(-1);
    }
</script>
<body>

<header>


    <%@include file="../../views/inc/navbar.jsp" %>

    <div class="container">

        <div class="row justify-content-center align-items-center ">
            <div class="col-md-6" style="margin-top: 20px;">
                <nav aria-label="Breadcrumb">
                    <ul class="breadcrumb justify-content-center py-0 bg-transparent">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item active">About</li>
                    </ul>
                </nav>
                <h1 class="text-center" style="margin-bottom: 10%;">채팅</h1>

            </div>
        </div>

    </div>
</header>


<div class="container" style="height:800px">
    <section class="msger" style="margin: 0 auto;">
        <header class="msger-header">
            <div class="msger-header-title">
                <div class="msg-img"
                     style="background-image: url(https://image.flaticon.com/icons/svg/327/327779.svg)"></div>
                <h2><%=rDTO.getUser_other()%>님과 대화</h2><a href="javascript:void(0);" onclick="back()">대화방나가기</a>

            </div>
            <div class="msger-header-options">
                <span><i class="fas fa-cog"></i></span>
            </div>
        </header>

        <main class="msger-chat">

            <div id="talk">
                <%for ( ChatDTO pDTO : rList) { %>
                <%       if (!pDTO.getUser_name().equals(ss_user_name)) { %>
                <div class="msg left-msg">
                    <div
                            class="msg-img"
                            style="background-image: url(https://image.flaticon.com/icons/svg/327/327779.svg)"
                    ></div>

                    <div class="msg-bubble">
                        <div class="msg-info">
                            <div class="msg-info-name"><%=pDTO.getUser_name()%></div>
                            <div class="msg-info-time"><%=pDTO.getDateTime()%></div>
                        </div>

                        <div class="msg-text">
                          <%=pDTO.getMsg()%>
                        </div>
                    </div>
                </div>
                <% }else {%>
                <div class="msg right-msg">
                    <div
                            class="msg-img"
                            style="background-image: url(https://image.flaticon.com/icons/svg/145/145867.svg)"
                    ></div>

                    <div class="msg-bubble">
                        <div class="msg-info">
                            <div class="msg-info-name"><%=pDTO.getUser_name()%></div>
                            <div class="msg-info-time"><%=pDTO.getDateTime()%></div>

                        </div>

                        <div class="msg-text">
                        <%=pDTO.getMsg()%>
                        </div>
                    </div>
                </div>
                <% } %> <!--else -->
                <% } %> <!--for-->
            </div>

        </main>

        <form class="msger-inputarea">
            <input type="text" class="msger-input" placeholder="Enter your message..." id="msg">
            <button type="button" class="msger-send-btn" id='btnSend' >Send</button>
        </form>
    </section>


</div> <!-- .container -->

<%@include file="../../views/inc/footbar.jsp" %>

<script src="../assets/js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">
    function getId(id) {

        console.log(id)
        return document.getElementById(id);
    }

    var data = {};//전송 데이터(JSON)

    var ws;
    var btnSend = getId('btnSend');
    var msg = getId('msg');
    var talk = getId('talk');



    $(document).ready(function () {

        //웹소켓 객체를 생성하는중
        console.log("openSocket");
        if (ws !== undefined && ws.readyState !== WebSocket.CLOSED) {
            console.log("WebSocket is already opened.");
            return;
        }

       ws = new WebSocket("ws://13.208.147.39:11000/chatt");  //aws 소켓
       //ws = new WebSocket("ws://"+location.host+"/chatt"); //local 소켓
      //  ws=new WebSocket("ws://savegreen.shop/chatt")

        ws.onopen = function (event) {
            if (event.data === undefined)
                return;

            console.log(event.data)
        };

        //웹소캣으로부터 메세지를 받음
        ws.onmessage = function (msg) {

            var data = JSON.parse(msg.data);
            console.log("여기는 onmessage zone 입니다");
            console.log(data);
            console.log(data.msg);
            console.log(data.date);

            var other = data.name; //상대방이름
            var message = data.msg; //메세지
            var date = data.date; //날짜

            var ss_name = '<%=(String)session.getAttribute("SS_USER_NAME")%>';


            console.log(data.name); //보낸사람
            console.log(ss_name); //세션에 저장된 아이디

            if (other == ss_name) {

                console.log("나다"); //보낸사람이랑랑 세션이름일아같으면 오른쪽에 출력

                var html = '';
                html += '<div class="msg right-msg" id="rightmsg"> <div class="msg-img" style="background-image: url(https://image.flaticon.com/icons/svg/145/145867.svg)" ></div>'
                html += '<div class="msg-bubble"> <div class="msg-info"> <div class="msg-info-name">' + ss_name + '</div> <div class="msg-info-time">' + date + '</div> </div>'
                html += '<div class="msg-text">' + message + ' </div> </div> </div>';

                talk.innerHTML += html;
                //스크롤 최 하단으로 내리기
                var chatscroll = document.querySelector(".msger-chat");
                chatscroll.scrollTop = chatscroll.scrollHeight;


            } else {

                console.log("내가아님")  //세션이랑 보낸사람이랑 다르면 왼쪾에 출력

                var html = '';
                html += '<div class="msg left-msg" id="leftmsg"> <div class="msg-img" style="background-image: url(https://image.flaticon.com/icons/svg/327/327779.svg)" ></div>';
                html += ' <div class="msg-bubble"> <div class="msg-info"> <div class="msg-info-name">' + other + '</div>';
                html += '<div class="msg-info-time">' + date + '</div> </div>';
                html += '<div class="msg-text">' + message + ' </div> </div> </div>';

                talk.innerHTML += html;
                var chatscroll = document.querySelector(".msger-chat");
                chatscroll.scrollTop = chatscroll.scrollHeight;

            }


        }
    });

    // Get the input field
    var input = document.getElementById("msg");

    // Execute a function when the user presses a key on the keyboard
    input.addEventListener("keypress", function(event) {
        // If the user presses the "Enter" key on the keyboard
        if (event.key === "Enter") {
            // Cancel the default action, if needed
            event.preventDefault();
            // Trigger the button element with a click
            document.getElementById("btnSend").click();
        }
    });

    btnSend.onclick = function () {
        send();
    }

    function send() {
        if (msg.value.trim() != '') {
            data.name = '<%=(String)session.getAttribute("SS_USER_NAME")%>'; //세션에 저장된이름
            data.msg = msg.value;
            data.date = new Date().toLocaleString();
            data.roomKey ='<%=rDTO.getRoomKey()%>';
            var temp = JSON.stringify(data);
            console.log("send보내는 곳입니다.");
            console.log(temp);
            ws.send(temp);


          $.ajax({
                url: "/chat/msg",
               type:"post",
                dataType:"JSON",
              async:true,
              timeout:5000,
              contentType: "application/json;charset=utf-8",
              data:temp,
              sucess:function (data){
                    console.log(data);
                  console.log("데이터보내기 성공");
              }
          })
        }
        msg.value = '';

    };

</script>

</body>
</html>