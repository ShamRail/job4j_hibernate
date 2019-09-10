<!DOCTYPE html>
<html>
<head>

    <title>Sign in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>

        <%@include file="/resources/css/login.css"%>
        <%@include file="/resources/css/signin.css"%>

    </style>

</head>

<body>

<form action="${pageContext.request.contextPath}/ads" method="get" id="f"></form>

<header>

</header>
<main>

    <div class="sidebar">

    </div>
    <div id="content">
        <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <div class="container">

            <label for="login"><b>Name</b></label>
            <input type="text" placeholder="Enter Username" name="login" id="login">

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="password">

            <div>

                <button type="submit" id="signinbutton">Sign in</button>
                <button formaction="${pageContext.request.contextPath}/ads" id="signup-button" formmethod="get">Cancel</button>

            </div>

        </div>
        </form>
    </div>
    <div class="sidebar">

    </div>

</main>
<footer>

</footer>

</body>
</html>
