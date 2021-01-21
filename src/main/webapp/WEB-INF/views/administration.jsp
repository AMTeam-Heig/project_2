<!-- Administration -->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link
            href="https://fonts.googleapis.com/css2?family=Roboto&display=swap"
            rel="stylesheet"
    />
    <link
            rel="stylesheet"
            href="./assets/vendor/@fortawesome/fontawesome-free/css/all.min.css"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.min.css"
    />
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!--===============================================================================================-->
    <link
            rel="icon"
            type="image/png"
            href="assets/img/images/icons/favicon.ico"
    />
    <!--===============================================================================================-->
    <link
            rel="stylesheet"
            type="text/css"
            href="assets/vendor/bootstrap/css/bootstrap.min.css"
    />
    <!--===============================================================================================-->
    <link
            rel="stylesheet"
            type="text/css"
            href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css"
    />
    <!--===============================================================================================-->
    <link
            rel="stylesheet"
            type="text/css"
            href="assets/fonts/Linearicons-Free-v1.0.0/icon-font.min.css"
    />
    <!--===============================================================================================-->
    <link
            rel="stylesheet"
            type="text/css"
            href="assets/vendor/animate/animate.css"
    />
    <!--===============================================================================================-->
    <link
            rel="stylesheet"
            type="text/css"
            href="assets/vendor/css-hamburgers/hamburgers.min.css"
    />
    <!--===============================================================================================-->
    <link
            rel="stylesheet"
            type="text/css"
            href="assets/vendor/animsition/css/animsition.min.css"
    />
    <!--===============================================================================================-->
    <link
            rel="stylesheet"
            type="text/css"
            href="assets/vendor/select2/select2.min.css"
    />
    <!--===============================================================================================-->
    <link
            rel="stylesheet"
            type="text/css"
            href="assets/vendor/daterangepicker/daterangepicker.css"
    />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/css/util.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/main.css" />
    <!--===============================================================================================-->

    <link
            href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css"
            rel="stylesheet"
    />
    <script
            src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
            defer
    ></script>
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
    <section class="relative block" style="height: 500px">
        <div
                class="absolute top-0 w-full h-full bg-center bg-cover"
                style="background-image: url('./assets/img/header-profil.jpg')"
        ></div>
    </section>
    <section class="relative py-16 bg-gray-300">
        <div class="container mx-auto px-4">
            <div
                    class="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-xl rounded-lg -mt-64"
            >
                <!-- debut carte -->
                <label for="usersLabel">Choisir un utilisateur : </label>
                <div class="px-1">
                    <select id="usersLabel">
                        <c:forEach var="user" items="${users}">
                            <option value="${user}">${user}</option>
                        </c:forEach>
                        <!--
                        <option value="volvo">Volvo</option>
                        <option value="saab">Saab</option>
                        <option value="vw">VW</option>
                        -->
                    </select>
                </div>
                <div class="text-center px-6">
                    <!-- Form -->
                    <form action="./administration.do" method="POST" accept-charset="utf-8">
                        <div class="form-group row">
                            <div class="col-md-5">
                                <label for="role" class="col-form-label">Rôle</label>
                            </div>
                            <select id="role">
                                <option value="0">Administrateur</option>
                                <option value="2">Utilisateur</option>
                            </select>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-5">
                                <label for="firstname" class="col-form-label">Prénom</label>
                            </div>
                            <div class="col-md-6">
                                <input
                                        type="text"
                                        name="firstname"
                                        id="firstname"
                                        class="form-control"
                                        placeholder="Olivier"
                                />
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-5">
                                <label for="lastname" class="col-form-label">Nom</label>
                            </div>
                            <div class="col-md-6">
                                <input
                                        type="text"
                                        name="lastname"
                                        id="nom"
                                        class="form-control"
                                        placeholder="Liechti"
                                />
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-5">
                                <label for="email" class="col-form-label">E-mail</label>
                            </div>
                            <div class="col-md-6">
                                <input
                                        type="text"
                                        name="email"
                                        id="email"
                                        class="form-control"
                                        placeholder="prenom.nom@exemple.com"
                                />
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-5">
                                <label for="username" class="col-form-label"
                                >Identifiant</label
                                >
                            </div>
                            <div class="col-md-6">
                                <input
                                        type="text"
                                        name="username"
                                        id="login"
                                        class="form-control"
                                        placeholder="Identifiant"
                                />
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-5">
                                <label for="clearTextPassword" class="col-form-label"
                                >Mot de passe</label
                                >
                            </div>
                            <div class="col-md-6">
                                <input
                                        type="password"
                                        name="clearTextPassword"
                                        id="password"
                                        class="form-control"
                                        placeholder="Mot de passe"
                                />
                            </div>
                        </div>

                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn">Validate</button>
                        </div>
                    </form>
                    <!-- fin carte -->
                </div>
            </div>
        </div>
    </section>
</main>
</body>
</html>
