<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    //강제부여
    session.setAttribute("SS_USER_NAME", "박수진");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="../assets/js/jquery-3.6.0.min.js"></script>
    <title>Insert title here</title>

    <style type="text/css">


        *{
            box-sizing: border-box;
        }

        #chatt{
            width: 800px;
            margin: 20px auto;
        }

        #chatt #talk{
            width: 800px;
            height: 400px;
            overflow: scroll;
            border : 1px solid #aaa;
        }
        #chatt #msg{
            width: 740px;
            height:100px;
            display: inline-block;
        }

        #chatt #sendZone > *{
            vertical-align: top;

        }
        #chatt #btnSend{
            width: 54px;
            height: 100px;
        }

        #chatt #talk div{
            width: 70%;
            display: inline-block;
            padding: 6px;
            border-radius:10px;

        }

        #chatt .me{
            background-color : #ffc;
            margin : 1px 0px 2px 30%;
        }

        #chatt .other{
            background-color : #eee;
            margin : 2px;
        }
    </style>

</head>
<body>
<div id='chatt'>
    <h1>WebSocket Chatting</h1>
    <input type='text' id='mid' value='홍길동'>
    <input type='button' value='로그인' id='btnLogin'>
    <br/>
    <div id='talk'></div>
    <div id='sendZone'>
        <textarea id='msg' value='hi...' ></textarea>
        <input type='button' value='전송' id='btnSend'>
    </div>
</div>

</body>
<script type="text/javascript">
    function getId(id){

        console.log(id)
        return document.getElementById(id);
    }

    var data = {};//전송 데이터(JSON)

    var ws ;
    var mid = getId('mid');
    var btnLogin = getId('btnLogin');
    var btnSend = getId('btnSend');
    var talk = getId('talk');
    var msg = getId('msg');

    $(document).ready(function() {



        //웹소켓 객체를 생성하는중
        console.log("openSocket");
        if (ws !== undefined && ws.readyState !== WebSocket.CLOSED) {
            console.log("WebSocket is already opened.");
            return;
        }

        ws = new WebSocket("ws://" + location.host + "/chatt");
        ws.onopen = function (event) {
            if (event.data === undefined)
                return;

            console.log(event.data)
        };
        ws.onmessage = function(msg){

            var data = JSON.parse(msg.data);
            console.log("여기는 onmessage zone 입니다"+data);
            console.log(data.mid);
            console.log(data.msg);
            console.log(data.date);

            var mid = data.mid;
            var message =data.msg;
            var date = data.date;
            var ss_name= '<%=(String)session.getAttribute("SS_USER_NAME")%>';
            var css;

            console.log(data.mid);
            console.log(ss_name);

            if(data.mid == ss_name){
                css = 'class=me';
                console.log("나다");
            }else{
                css = 'class=other';
                console.log("내가아님");
            }

            var item = '<div><span><b>'+mid+'</b></span> '+date +'<br/> <span>'+message+'</span> </div>';

            talk.innerHTML += item;
            talk.scrollTop=talk.scrollHeight;//스크롤바 하단으로 이동
        }
    });

    msg.onkeyup = function(ev){
        if(ev.keyCode == 13){
            send();
        }
    }

    btnSend.onclick = function(){
        send();
    }

    function send(){
        if(msg.value.trim() != ''){
            data.mid = getId('mid').value;
            data.msg = msg.value;
            data.date = new Date().toLocaleString();
            var temp = JSON.stringify(data);
            ws.send(temp);
        }
        msg.value ='';

    }
</script>
</html>