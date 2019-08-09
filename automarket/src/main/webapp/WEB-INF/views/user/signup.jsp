<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/resources/css/signup.css"%>
    </style>
    <title>Sign up</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        formId = 'signup-form';
        requestURL = '/automarket/signup';
        <%@include file="/resources/js/sample.js"%>
    </script>
</head>

<body>

<form action="${pageContext.request.contextPath}/ads" method="get" id="f"></form>

<header>

</header>
<main>

    <div class="sidebar">

    </div>
    <div class="content">

        <div class="empty-block"></div>
        <div id="create-form">
            <div id="img-box">
                <img src="${pageContext.request.contextPath}/resources/images/nophoto.png" alt="no" width="100%" height="100%"/>
            </div>
            <div id="user-info">

                <form id="signup-form" action="${pageContext.request.contextPath}/signup" method="post" enctype="multipart/form-data">

                    <label for="name">Name:</label>
                    <input id="name" name="name" type="text" placeholder="Please, enter your name"/>

                    <label for="password">Passoword:</label>
                    <input id="password" name="password" type="password" placeholder="Please, enter your password"/>

                    <label for="email">Email:</label>
                    <input id="email" name="email" type="text" placeholder="Please, enter your email"/>

                    <label for="telnumber">Telnumber:</label>
                    <input id="telnumber" name="telNumber" type="text" placeholder="Please, enter your telnumber"/>

                    <label for="photo">Photo:</label>
                    <input id="photo" type="file" name="photo" placeholder="Please, choose photo for your profile" onchange="previewFile()"/>

                    <div id="btns">
                        <input type="submit" value="Cancel" id="btn-cncl" form="f"/>
                        <input type="submit" value="Submit" id="btn-sbmt"/>
                    </div>
                </form>
            </div>

        </div>
        <div class="empty-block"></div>

    </div>
    <div class="sidebar">

    </div>

</main>
<footer>

</footer>

</body>
</html>

