<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/resources/css/signin.css"%>
    </style>
    <title>Sign in</title>
</head>

<body>

<form action="${pageContext.request.contextPath}/ads" method="get" id="f"></form>

<header>

</header>
<main>

    <div class="sidebar">

    </div>
    <div class="content">

        <div id="user-info">
            <form id="sign-form" action="${pageContext.request.contextPath}/signin" method="post">

                <label for="name">Name:</label>
                <input id="name" name="name" type="text" placeholder="Please, enter your name"/>

                <label for="password">Password:</label>
                <input id="password" name="password" type="password" placeholder="Please, enter your password"/>

                <div id="btns">
                    <input type="submit" value="Cancel" id="btn-cncl" form="f"/>
                    <input type="submit" value="Submit" id="btn-sbmt"/>
                </div>

            </form>
        </div>

    </div>
    <div class="sidebar">

    </div>

</main>
<footer>

</footer>

</body>
</html>
