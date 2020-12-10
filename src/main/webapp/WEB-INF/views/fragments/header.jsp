<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="./assets/css/bootstraps/bootstrap.css" rel="stylesheet">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <img src="./assets/img/goat-title.png" height="45px" class="navbar-brand"/>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="./home">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./profile">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./statistics">Stats</a>
            </li>
            <c:if test="${currentUser == null}">
                <a class="nav-link" href="./login">Login</a>
            </c:if>
            <c:if test="${currentUser != null}">
                <form id="logoutForm" method="POST" action="logout.do">
                    <button class="nav-link" type="submit">
                        Logout
                    </button>
                </form>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="GET" action="/home">
            <input name="search"  class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
