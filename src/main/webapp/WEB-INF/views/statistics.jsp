<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link
            rel="apple-touch-icon"
            sizes="76x76"
            href="./assets/img/apple-icon.png"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.min.css"
    />
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
    <title>Welcome to StackOverGoat</title>
</head>
<body class="text-gray-800 antialiased">

<div align="center">
    <c:forEach var="error" items="${errors}">
        <div class="error">${error}</div>
    </c:forEach>
</div>

<jsp:include flush="true" page="./fragments/header.jsp"/>
<main class="profile-page">
    <section class="relative block" style="height: 500px;">
        <div
                class="absolute top-0 w-full h-full bg-center bg-cover"
                style='background-image: url("./assets/img/header-profil.jpg");'
        >
        </div>
    </section>
    <section class="relative py-16 bg-gray-300">
        <div style="text-align: center;"><h2>Here some numbers</h2></div>

        <h2>Number of questions  : </h2>${stats.nbQuestion}<br/>
        <h2>Number of users  : </h2>${stats.nbUser}<br/>
    </section>
</main>
</body>
</html>
