<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <style>
        <%@include file="/resources/css/adstyle.css"%>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        formId = 'create-form';
        requestURL = '/automarket/add-ad';
        <%@include file="/resources/js/sample.js"%>

        // $(document).ready(function(){
        //     $("#date").attr({"value": new Date().getTime()});
        //     $("#date-text").text(new Date());
        // });

    </script>
    <title>Show ad</title>
</head>
<body>

<form id="f" method="get" action="${pageContext.request.contextPath}/ads"></form>

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

    <div class="sidebar"></div>
    <div class="content">

        <form action="${pageContext.request.contextPath}/add-ad" method="post" id="create-form" enctype="multipart/form-data">

            <div id="ad-info">

                <div id="img-box">

                    <c:if test="${ad.photoPath != null}">
                        <img src="${pageContext.request.contextPath}/get-image?path=${ad.photoPath}" alt="car"/>
                    </c:if>
                    <c:if test="${ad.photoPath == null}">
                        <img src="${pageContext.request.contextPath}/resources/images/nophoto.png" alt="car"/>
                    </c:if>

                </div>
                <div id="ad-attr">

                    <label for="title">Title:</label>
                    <input id="title" name="title" type="text" value="${ad.title}" readonly/>

                    <label for="description">Description:</label>
                    <input id="description" name="description" type="text" value="${ad.description}" readonly/>

                    <label for="date">Date:</label>
                    <p id="date-text">${ad.date}</p>
                    <input id="date" name="date" type="hidden" value=""/>

                    <label for="user">User:</label>
                    <a id="user" href="/automarket/show-user?id=${ad.user.id}">${ad.user.name}</a>

                </div>

            </div>
            <div id="car-info">

                <label for="mark">Mark:</label>
                <input id="mark" name="mark" type="text" value="${ad.car.mark}" readonly/>

                <label for="model">Model:</label>
                <input id="model" name="model" type="text" value="${ad.car.model}" readonly/>

                <label for="engine-type">Engine Type:</label>
                <input id="engine-type" name="engine-type" type="text" value="${ad.car.engineType}" readonly/>

                <label for="engine-volume">Engine Volume:</label>
                <input id="engine-volume" name="engine-volume" type="text" value="${ad.car.engineVolume}" readonly/>

                <label for="used">Used:</label>
                <c:if test="${ad.car.used == true}">
                    <input id="used" name="used" value="Yes" type="text" readonly/>
                </c:if>
                <c:if test="${ad.car.used != true}">
                    <input id="used" name="used" value="No" type="text" readonly/>
                </c:if>

                <label for="manufacture-year">Manufacture Year:</label>
                <input id="manufacture-year" name="manufacture-year" type="text" value="${ad.car.manufactureYear}" readonly/>

                <label for="transmission">Transmission:</label>
                <input id="transmission" name="transmission" value="${ad.car.transmission}" type="text" readonly/>


                <label for="body">Body:</label>
                <input id="body" name="body" value="${ad.car.body}" type="text" readonly/>

                <c:if test="${ad.status == true}">
                    <label for="price">Price($$$):</label>
                    <input style="color: green" id="price" name="price" type="text" value="${ad.price}" readonly/>
                </c:if>
                <c:if test="${ad.status != true}">
                    <label for="price">Price($$$):</label>
                    <input style="color: red;" id="price" name="price" type="text" value="SOLD" readonly/>
                </c:if>

            </div>
            <div id="btns">

                <input type="submit" value="Return back" form="f"/>

            </div>

        </form>

    </div>
    <div class="sidebar"></div>

</main>
<footer></footer>

</body>

</html>