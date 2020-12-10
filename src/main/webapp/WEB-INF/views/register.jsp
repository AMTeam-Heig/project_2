<%--
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="fr">
<head>
    <title>Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="assets/img/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-form-title">
                <img src="assets/img/goat.png" width="150px">
                <span class="login100-form-title-1">
						Register
					</span>
            </div>
            <form action="./register.do" method="POST" accept-charset="utf-8">
                <div class="form-group row">
                    <div class="col-md-5">
                        <label for="firstname" class="col-form-label">Prénom</label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" name="firstname" id="firstname" class="form-control" placeholder="Olivier">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-5">
                        <%--@declare id="lastname"--%><label for="lastname" class="col-form-label">Nom</label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" name="lastname" id="nom" class="form-control" placeholder="Liechti">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-5">
                        <label for="email" class="col-form-label">E-mail</label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" name="email" id="email" class="form-control" placeholder="prenom.nom@exemple.com">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-5">
                        <%--@declare id="username"--%><label for="username" class="col-form-label">Identifiant</label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" name="username" id="login" class="form-control" placeholder="Identifiant">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-5">
                        <%--@declare id="cleartextpassword"--%><label for="clearTextPassword" class="col-form-label">Mot de passe</label>
                    </div>
                    <div class="col-md-6">
                        <input type="password" name="clearTextPassword" id="password" class="form-control" placeholder="Mot de passe">
                    </div>
                </div>

                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn">
                            Validate
                        </button>
                </div>

            </form>
            <div style="text-align: center;">
                <a href="./login">Retour au login</a>
            </div>
        </div>
    </div>
</div>

<!--===============================================================================================-->
<script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/bootstrap/js/popper.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/daterangepicker/moment.min.js"></script>
<script src="assets/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="assets/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="assets/js/main.js"></script>


</body>
</html>
