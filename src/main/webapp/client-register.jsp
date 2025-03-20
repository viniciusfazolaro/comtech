<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%><!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Cadastro de clientes</title>
    <meta name="description" content="">
    <meta name="keywords" content="">

    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Jost:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/aos/aos.css" rel="stylesheet">
    <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
    <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Main CSS File -->
    <link href="assets/css/main.css" rel="stylesheet">
    <link href="assets/css/navbar.css" rel="stylesheet">

    <!-- =======================================================
    * Template Name: Arsha
    * Template URL: https://bootstrapmade.com/arsha-free-bootstrap-html-template-corporate/
    * Updated: Aug 07 2024 with Bootstrap v5.3.3
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body class="starter-page-page">

<header id="header" class="header d-flex align-items-center sticky-top">
    <div class="container-fluid container-xl position-relative d-flex align-items-center">

        <a href="home.jsp" class="logo d-flex align-items-center me-auto">
            <!-- Uncomment the line below if you also wish to use an image logo -->
            <!-- <img src="assets/img/logo.png" alt=""> -->
            <h1 class="sitename">COMTECH</h1>
        </a>

        <nav id="navmenu" class="navmenu">
            <ul>
                <li><a href="home.jsp">Home</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Clientes
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="client-register.jsp">Cadastrar cliente</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="clientList">Listar clientes</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        OS
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <a class="dropdown-item" href="serviceOrderRegister">Cadastrar OS</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="serviceOrderList">Listar OS</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Formas de Pagamento
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                        <a class="dropdown-item" href="payment-method-register.jsp">Cadastrar Forma de pagamento</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="paymentMethodList">Listar Formas de pagamento</a>
                    </div>
                </li>
                <li><a href="logout">Logout</a></li>
            </ul>
            <i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
        </nav>
    </div>
</header>

<main class="main">
    <!-- Page Title -->
    <div class="page-title" data-aos="fade">
        <div class="container">
            <nav class="breadcrumbs">
                <ol>
                    <li><a href="home.jsp">Home</a></li>
                    <c:choose>
                        <c:when test="${client == null}">
                            <li class="current">Cadastrar Clientes</li>
                        </c:when>
                        <c:when test="${client != null}">
                            <li class="current">Atualizar Clientes</li>
                        </c:when>
                    </c:choose>
                </ol>
            </nav>
            <c:choose>
                <c:when test="${client == null}">
                    <h1>Cadastrar Clientes</h1>
                </c:when>
                <c:when test="${client != null}">
                    <h1>Atualizar Clientes</h1>
                </c:when>
            </c:choose>
        </div>
    </div><!-- End Page Title -->
    <div class="col-lg-6 offset-lg-3 col-sm-12 my-5">
        <c:if test="${result == 'notRegistered'}">
            <div class="alert alert-danger alert-dismissible fade show"
                 role="alert">
                CPF já cadastrado.
                <button type="button" class="btn-close" data-bs-dismiss="alert"
                        aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${result == 'updated'}">
            <div class="alert alert-primary alert-dismissible fade show"
                 role="alert">
                Usuário atualizado com sucesso.
                <button type="button" class="btn-close" data-bs-dismiss="alert"
                        aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${result == 'registered'}">
            <div class="alert alert-primary alert-dismissible fade show"
                 role="alert">
                Usuário salvo com sucesso.
                <button type="button" class="btn-close" data-bs-dismiss="alert"
                        aria-label="Close"></button>
            </div>
        </c:if>
    </div>
            <section id="starter-section" class="starter-section section d-flex align-items-center" style="min-height: 100vh;">
        <div class="container d-flex justify-content-center">
            <div class="col-lg-6 col-sm-12">
                <form action="clientRegister" method="post" class="p-4 border rounded bg-light shadow">
                    <c:choose>
                        <c:when test="${client == null}">
                            <input type="hidden" name="id" value="0">
                        </c:when>
                        <c:when test="${client != null}">
                            <input type="hidden" name="id" value="${client.id}">
                        </c:when>
                    </c:choose>


                    <div class="mb-2">
                        <label for="name">Nome:</label>
                        <input type="text" name="name" id="name" class="form-control"
                               required maxlength="50" value="${client.name}">
                    </div>

                    <div class="mb-2">
                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email" class="form-control"
                               required maxlength="50" value="${client.email}">
                    </div>

                    <div class="mb-2">
                        <label for="phone">Telefone:</label>
                        <input type="text" name="phone" id="phone" class="form-control"
                               required maxlength="50" value="${client.phone}">
                    </div>

                    <div class="mb-2">
                        <label for="cpf">CPF:</label>
                        <input type="text" name="cpf" id="cpf" class="form-control"
                               required maxlength="50" value="${client.cpf}">
                    </div>

                    <h5 class="mt-4">Endereço</h5>

                    <div class="mb-2">
                        <label for="street">Rua:</label>
                        <input type="text" name="street" id="street" class="form-control"
                               required maxlength="50" value="${client.address.street}">
                    </div>

                    <div class="mb-2">
                        <label for="number">Número:</label>
                        <input type="text" name="number" id="number" class="form-control"
                               required maxlength="10" value="${client.address.number}">
                    </div>

                    <div class="mb-2">
                        <label for="complement">Complemento:</label>
                        <input type="text" name="complement" id="complement" class="form-control"
                               maxlength="10" value="${client.address.complement}">
                    </div>

                    <div class="mb-2">
                        <label for="neighborhood">Bairro:</label>
                        <input type="text" name="neighborhood" id="neighborhood" class="form-control"
                               required maxlength="50" value="${client.address.neighborhood}">
                    </div>

                    <div class="mb-2">
                        <label for="cep">CEP:</label>
                        <input type="text" name="cep" id="cep" class="form-control"
                               required maxlength="20" value="${client.address.cep}">
                    </div>

                    <div class="mb-2">
                        <label for="city">Cidade:</label>
                        <input type="text" name="city" id="city" class="form-control"
                               required maxlength="50" value="${client.address.city}">
                    </div>

                    <div class="mb-2">
                        <label for="state">Estado:</label>
                        <input type="text" name="state" id="state" class="form-control"
                               required maxlength="50" value="${client.address.state}">
                    </div>

                    <div class="text-center mt-4">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                    </div>
                </form>
            </div>
        </div>
    </section>

</main>

<footer id="footer" class="footer">
    <div class="container copyright text-center mt-4">
        <p><strong class="px-1 sitename">COMTECH</strong> <span>Assistência técnica</span></p>
    </div>
</footer>

<!-- Scroll Top -->
<a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Preloader -->
<div id="preloader"></div>

<!-- Vendor JS Files -->
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
<script src="assets/vendor/aos/aos.js"></script>
<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
<script src="assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
<script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>

<!-- Main JS File -->
<script src="assets/js/main.js"></script>

</body>

</html>