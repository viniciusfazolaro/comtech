<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>COMTECH</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="assets/css/login.css" rel="stylesheet">
    <link href="assets/img/favicon.png" rel="icon">
</head>

<body>

<a class="back" href="login.jsp">
    <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="white" class="bi bi-arrow-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8"/>
    </svg>
    Retornar ao login
</a>
<section class="vh-100">
    <div class="container py-5 h-100">
        <div class="col-lg-6 offset-lg-3 col-sm-12">
            <c:if test="${result == 'notRegistered'}">
                <div class="alert alert-danger alert-dismissible fade show"
                     role="alert">
                    E-mail já cadastrado. Tente novamente.
                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                            aria-label="Close"></button>
                </div>
            </c:if>

            <div class="row d-flex align-items-center justify-content-center h-100 login-form">
                <div class="col-md-8 col-lg-7 col-xl-6">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
                         class="img-fluid" alt="Phone image">
                </div>
                <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                    <h2>Registre-se</h2>
                    <form action="userRegister" method="post" id="form1">
                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="name">Nome completo*</label>
                            <input type="text" id="name" name="name" class="form-control form-control-lg" minlength="3" maxlength="50" required />
                            <span id="0"></span>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="email">E-mail*</label>
                            <input type="email" id="email" name="email" class="form-control form-control-lg" required />
                            <span id="1"></span>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="password">Senha*</label>
                            <input type="password" id="password" name="password" class="form-control form-control-lg" minlength="6" maxlength="12" required />
                            <span id="2"></span>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="confirmPassword">Confirmação de Senha*</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control form-control-lg" minlength="6" maxlength="12" required />
                            <span id="3"></span>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="dateOfBirth">Data de Nascimento*</label>
                            <input type="date" id="dateOfBirth" name="dateOfBirth" class="form-control form-control-lg" max="2012-12-31" required />
                            <span id="4"></span>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="gender">Gênero*</label>
                            <select id="gender" name="gender" class="form-select form-control-lg" required>
                                <option value="" selected>Selecione</option>
                                <option value="MASCULINO">Masculino</option>
                                <option value="FEMININO">Feminino</option>
                                <option value="OUTRO">Outro</option>
                                <option value="PREFIRO_NAO_DIZER">Prefiro não dizer</option>
                            </select>
                            <span id="5"></span>
                        </div>

                        <div class="mb-4">
                            <button type="submit" class="btn btn-primary btn-lg">Salvar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="assets/js/userRegister.js"></script>
</body>
</html>