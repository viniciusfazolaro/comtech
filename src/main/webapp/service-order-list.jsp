<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Cadastro de OS</title>
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
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Clientes
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="client-register.jsp">Cadastrar cliente</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="clientList">Listar clientes</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                    <li class="current">Listar Ordem de Serviços</li>
                </ol>
            </nav>
            <h1>Listar Ordem de Serviços</h1>
        </div>
    </div><!-- End Page Title -->
    <section id="starter-section" class="starter-section section d-flex" style="min-height: 100vh;">
        <div class="container justify-content-center">
            <c:if test="${empty orders}">
                <h2>Nenhuma ordem de serviço foi cadastrada.</h2>
            </c:if>
            <c:if test="${not empty orders}">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Descrição</th>
                        <th>Data de emissão</th>
                        <th>Data de finalização</th>
                        <th>Valor</th>
                        <th>Observação</th>
                        <th>Status</th>
                        <th>Método de pagamento</th>
                        <th>Cliente</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.description}</td>
                            <td>
                                <fmt:parseDate value="${order.emissionDate}"
                                       pattern="yyyy-MM-dd" var="parsedDate"
                                       type="date" />
                                <fmt:formatDate value="${parsedDate}"
                                                var="formattedDate" type="date"
                                        pattern="dd/MM/yyyy"/>
                                    ${formattedDate}
                            </td>
                            <td>
                                <fmt:parseDate value="${order.finishDate}"
                                               pattern="yyyy-MM-dd" var="parsedDate"
                                               type="date" />
                                <fmt:formatDate value="${parsedDate}"
                                                var="formattedDate" type="date"
                                                pattern="dd/MM/yyyy"/>
                                    ${formattedDate}
                            </td>
                            <td>R$ ${order.value}</td>
                            <td>${order.observation}</td>
                            <td>
                                <c:if test="${order.status == 'EM_APROVACAO'}">Em aprovação</c:if>
                                <c:if test="${order.status == 'APROVADA'}">Aprovada</c:if>
                                <c:if test="${order.status == 'EM_ANDAMENTO'}">Em andamento</c:if>
                                <c:if test="${order.status == 'FINALIZADA'}">Finalizada</c:if>
                            </td>
                            <td>${order.paymentMethod.name}</td>
                            <td>${order.client.name}</td>
                            <td>
                                <span data-bs-toggle="tooltip" data-bs-placement="top" title="Editar">
                                    <a class="btn" href="serviceOrderUpdate?action=update&service-id=${order.id}">
                                        <img src="assets/img/pencil-square.svg" alt="Editar">
                                    </a>
                                </span>
                                <span data-bs-toggle="tooltip" data-bs-placement="top" title="Excluir">
       								<a type="button" class="btn" data-bs-toggle="modal" data-bs-target="#myModal"
                                       data-bs-id="${order.id}">
        								<img src="assets/img/trash.svg" alt="Excluir">
        							</a>
       							</span>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
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

<div class="modal" tabindex="-1" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Exclusão</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Tem certeza que deseja excluir a ordem de serviço?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" id="delete" class="btn btn-danger">Excluir</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="assets/js/serviceOrder.js"></script>
</body>

</html>