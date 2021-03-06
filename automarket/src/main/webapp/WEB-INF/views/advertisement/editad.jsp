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

        $(document).ready(function(){
            setCurrentDateTime();
            setSelectedUsedOption();
            setSelectedTransmissionOption();
            setSelectedBodyOption();
            setSelectedStatusOption();
        });

        function setCurrentDateTime() {
            $("#date").attr({"value": new Date().getTime()});
            $("#date-text").text(new Date());
        }

        function setSelectedUsedOption() {
            $("#used").val('${ad.car.used}');
        }

        function setSelectedTransmissionOption() {
            $("#transmission").val('${ad.car.transmission}');
        }

        function setSelectedBodyOption() {
            $("#body").val("${ad.car.body}");
        }

        function setSelectedStatusOption() {
            $("#body").val('${ad.status}');
        }

    </script>
    <title>Create ad</title>
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

        <form action="${pageContext.request.contextPath}/edit-ad" method="post" id="create-form" enctype="multipart/form-data">

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

                    <input type="hidden" name="id" value="${ad.id}"/>
                    <input type="hidden" name="photoPath" value="${photoPath}"/>

                    <label for="title">Title:</label>
                    <input id="title" name="title" type="text" value="${ad.title}"/>

                    <label for="description">Description:</label>
                    <input id="description" name="description" type="text" value="${ad.description}"/>

                    <label for="status">Status:</label>
                    <select id="status" name="status">
                        <option value="true">Free</option>
                        <option value="false">Sold</option>
                    </select>


                    <label for="date">Date:</label>
                    <p id="date-text"></p>
                    <input id="date" name="date" type="hidden" value=""/>

                    <label for="photo" style="margin-top: 5px;">Photo:</label>
                    <input id="photo" name="photo" type="file" onchange="previewFile()">

                </div>

            </div>
            <div id="car-info">

                <label for="mark">Mark:</label>
                <input id="mark" name="mark" type="text" value="${ad.car.mark}"/>

                <label for="model">Model:</label>
                <input id="model" name="model" type="text" value="${ad.car.model}"/>

                <label for="engine-type">Engine Type:</label>
                <input id="engine-type" name="engine-type" type="text" value="${ad.car.engineType}"/>

                <label for="engine-volume">Engine Volume:</label>
                <input id="engine-volume" name="engine-volume" type="text" value="${ad.car.engineVolume}"/>

                <label for="used">Used:</label>
                <select id="used" name="used">
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                </select>

                <label for="manufacture-year">Manufacture Year:</label>
                <input id="manufacture-year" name="manufacture-year" type="text" value="${ad.car.manufactureYear}"/>

                <label for="transmission">Transmission:</label>
                <select id="transmission" name="transmission">
                    <option value="Auto">Auto</option>
                    <option value="Manual">Manual</option>
                </select>


                <label for="body">Body:</label>
                <select id="body" name="body">
                    <option value="Hatchback">Hatchback</option>
                    <option value="Sedan">Sedan</option>
                    <option value="MPV">MPV</option>
                    <option value="SUV">SUV</option>
                    <option value="Crossover">Crossover</option>
                    <option value="Coupe">Coupe</option>
                    <option value="Convertible">Convertible</option>
                </select>

                <label for="price">Price:</label>
                <input id="price" name="price" type="text" value="${ad.price}"/>

            </div>
            <div id="btns">

                <input type="submit" value="Cancel" form="f"/>
                <input type="submit" value="Submit"/>

            </div>

        </form>

    </div>
    <div class="sidebar"></div>

</main>
<footer></footer>

</body>

</html>