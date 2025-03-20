<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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

    <section class="vh-100">
        <div class="container py-5 h-100">
            <div class="col-lg-6 offset-lg-3 col-sm-12">
                <c:choose>
                    <c:when test="${result == 'registered'}">
                        <div class="alert alert-success alert-dismissible fade show"
                             role="alert">
                            Usuário cadastrado com sucesso. Faça o login.
                            <button type="button" class="btn-close" data-bs-dismiss="alert"
                                    aria-label="Close"></button>
                        </div>
                    </c:when>
                    <c:when test="${result == 'loginError'}">
                        <div class="alert alert-danger alert-dismissible fade show"
                             role="alert">
                            E-mail e/ou senha inválidos.
                            <button type="button" class="btn-close" data-bs-dismiss="alert"
                                    aria-label="Close"></button>
                        </div>
                    </c:when>
                </c:choose>
            </div>
            <div class="row d-flex align-items-center justify-content-center h-100 login-form">
                <div class="col-md-8 col-lg-7 col-xl-6">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
                         class="img-fluid" alt="Phone image">
                </div>
                <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                    <h2>Log-in</h2>
                    <form action="login" method="post">
                        <!-- Email input -->
                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="email">Email</label>
                            <input type="email" id="email" name="email" class="form-control form-control-lg" required/>
                        </div>

                        <!-- Password input -->
                        <div data-mdb-input-init class="form-outline mb-4">
                            <label class="form-label" for="password">Senha</label>
                            <input type="password" id="password" name="password" class="form-control form-control-lg" required/>
                        </div>

                        <!-- Submit button -->
                        <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block">Entrar</button>
                    </form>
                    <p class="small fw-bold mt-2 pt-1 mb-0">Não tem uma conta? <a href="user-register.jsp"
                                                                                  class="link-primary">Registre-se</a></p>
                </div>
            </div>
        </div>
    </section>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>