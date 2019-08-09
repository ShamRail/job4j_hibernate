<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/resources/css/profile.css"%>
    </style>
    <title>Sign up</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        <%--formId = 'signup-form';--%>
        <%--requestURL = '/automarket/signup';--%>
        <%--<%@include file="/resources/js/sample.js"%>--%>

        function showAd(event) {
            var id = event.getAttribute("id");
            window.location.replace("/automarket/get-ad?id=" + id);
        }

    </script>
</head>

<body>

<form action="${pageContext.request.contextPath}/ads" method="get" id="f"></form>

<header>
    <form id="aouth-btns">
        <c:if test="${id == -1}">
            <button type='submit' id='signin-btn' formmethod='get' formaction='/automarket/signup'>Sign up</button>
            <button type='submit' id='signup-btn' formmethod='get' formaction='/automarket/signin'>Sign in</button>
        </c:if>
        <c:if test="${id != -1}">
            <input type="hidden" name="id" value="${id}"/>
            <button type='submit' id='signin-btn' formmethod='get' formaction="/automarket/edit-user">Profile</button>
            <button type='submit' id='signup-btn' formmethod='post' formaction='/automarket/signout'>Sign out</button>
        </c:if>
    </form>
</header>
<main>

    <div class="sidebar">

    </div>
    <div class="content">

        <div id="create-form">

            <div id="img-box">
                <c:if test="${user.photoPath != null}">
                    <img src="${pageContext.request.contextPath}/get-image?path=${user.photoPath}" alt="car"/>
                </c:if>
                <c:if test="${user.photoPath == null}">
                    <img src="${pageContext.request.contextPath}/resources/images/nophoto.png" alt="car"/>
                </c:if>
            </div>

            <div id="user-info">

                <form id="signup-form" action="${pageContext.request.contextPath}/signup" method="post" enctype="multipart/form-data">

                    <label for="name">Name:</label>
                    <input id="name" name="name" type="text" placeholder="Please, enter your name" value="${user.name}" readonly/>

                    <label for="email">Email:</label>
                    <input id="email" name="email" type="text" placeholder="Please, enter your email" value="${user.email}" readonly/>

                    <label for="telnumber">Telnumber:</label>
                    <input id="telnumber" name="telNumber" type="text" placeholder="Please, enter your telnumber" value="${user.telNumber}" readonly/>

                </form>

            </div>

        </div>

        <div id="list">

            <div id="list-title">
                <h1 style="font-size: 30px;text-align: center;margin-bottom: 20px;">Users advertisements:</h1>
            </div>

            <c:forEach var="ad" items="${ads}">
                <div id="${ad.id}" class="item" onclick="showAd(this);">
                    <div class="img-box">
                        <c:if test="${ad.photoPath != null}">
                            <img src="${pageContext.request.contextPath}/get-image?path=${ad.photoPath}" alt="car"/>
                        </c:if>
                        <c:if test="${ad.photoPath == null}">
                            <img src="${pageContext.request.contextPath}/resources/images/nophoto.png" alt="car"/>
                        </c:if>
                    </div>
                    <div class="item-info">
                        <div class="dv-name">
                            <h3>${ad.title}</h3>
                        </div>
                        <div class="dv-description">
                            <div class="field">
                                <p class="inf-key">Date: </p>
                                <p class="inf-value">${ad.date}</p>
                            </div>
                            <div class="field">
                                <p class="inf-key">Mark</p>
                                <p class="inf-value">${ad.car.mark}</p>
                            </div>
                            <div class="field">
                                <p class="inf-key">Model</p>
                                <p class="inf-value">${ad.car.model}</p>
                            </div>
                            <div class="field">
                                <p class="inf-key">Produced</p>
                                <p class="inf-value">${ad.car.manufactureYear}</p>
                            </div>
                        </div>
                        <div class="dv-status">
                            <c:if test="${ad.status == true}">
                                <p>
                                    <br>
                                        ${ad.price} $
                                </p>
                            </c:if>

                            <c:if test="${ad.status == false}">
                                <p style="color: red;">
                                    <br>
                                    SOLD
                                </p>
                            </c:if>

                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>

        <div class="btns">
            <input type="submit" value="Back" id="btn-cncl" form="f"/>
        </div>

    </div>
    <div class="sidebar">

    </div>

</main>
<footer>

</footer>

</body>
</html>

