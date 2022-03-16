<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
 String user_email =  CmmUtil.nvl((String)session.getAttribute("SS_USER_EMAIL"));
%>

<nav class="navbar navbar-expand-lg navbar-light bg-white sticky" data-offset="500">
        <div class="container">
            <a href="#" class="navbar-brand">Save<span class="text-primary">Green.</span></a>

            <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapse" id="navbarContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/picSearch">Image</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/search">Method</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/mapSearch">location</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Sign-out</a>
                    </li>
                    <li class="nav-item">
                       <% if(user_email.equals("")){ %>
                        <a class="btn btn-primary ml-lg-2" href="/loginPage">Login</a>
                        <% } else {%>
                        <a class="btn btn-primary ml-lg-2" href="/logout">Log-Out</a>
                        <% } %>


                    </li>
                </ul>
            </div>

        </div>
    </nav>
