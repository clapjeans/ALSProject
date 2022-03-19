<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String user_email = CmmUtil.nvl((String) session.getAttribute("SS_USER_EMAIL"));

%>

<script>
    function deletUser() {
        user_email = '<%=user_email%>';
        if (user_email == "") {
            alert("로그인하고 오삼");
        } else {
            answer = confirm("회원을 탈퇴 하시겠습니까?");
            if (answer == true) {
                location.href = "/deleteUser"
            }

        }
    }

    function deletSort() {
        //searchlist 목록지우기 ㅠㅠ 모르겠음 일단 지우기
        // session 비움
        SORTNM = '<%=(String)session.getAttribute("SORTNM")%>'; //작은따옴표로 감싸야하는 이유찾기
        session.removeAttribute("SORTNM"); //세션 하나만 지울때사용

        //모든 세션 초기화
        //session.invalidate(SORTNM);
        font.color="green";
    }

</script>
<nav class="navbar navbar-expand-lg navbar-light bg-white sticky" data-offset="500">
    <div class="container">
        <a href="/home" class="navbar-brand">Save<span class="text-primary">Green.</span></a>

        <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="navbar-collapse collapse" id="navbarContent">
            <ul class="navbar-nav ml-auto" >
                <li class="nav-item" >
                    <a class="nav-link" href="/home" onclick="deletSort()">Home</a>
                </li>
                <li class="nav-item" onclick="deletSort();renameClass()">
                    <a class="nav-link" href="/picSearch" onclick="deletSort()">Image</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/search" onclick="deletSort()">Method</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/mapSearch" onclick="deletSort()">location</a>
                </li>


                <li class="nav-item">
                    <a class="nav-link" onclick="deletUser()">Sign-out</a>
                </li>
                <% if (user_email.equals("")) { %>
                <li class="nav-item">
                    <a class="btn btn-primary ml-lg-2" href="/loginPage">Login</a>
                    <% } else {%>
                    <a class="btn btn-primary ml-lg-2" href="/logout">Log-Out</a>
                </li>
                <% } %>
            </ul>
        </div>

    </div>
</nav>
