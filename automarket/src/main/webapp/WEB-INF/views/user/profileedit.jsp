<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/resources/css/profile.css"%>

        .btn {
            flex: 1;
        }

        #add-btn-div {
            display: flex;
            justify-content: center;
            width: 100%;
            height: 50px;
        }

        #add-btn {
            flex: 1;
            background-color: #FFA500;
            border: none;
            color: white;
        }
        #add-btn:hover {
            background-color: #FF6F00;
        }

        .edit-btn {
            background-color: lightgreen;
            border: none;
            height: 50px;
        }

        .edit-btn:hover {
            background-color: greenyellow;
            border: none;
        }

        .del-btn {
            background-color: lightcoral;
            border: none;
            height: 50px;
        }
        .del-btn:hover {
            background-color: coral;
            border: none;
        }


    </style>
    <title>Edit user</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>

        formId = 'signup-form';
        requestURL = '/automarket/edit-user';

        <%@include file="/resources/js/sample.js"%>

        function showAd(event) {
            var id = event.getAttribute("id");
            window.location.replace("/automarket/get-ad?id=" + id);
        }

        function removeAd(event) {
            var adId = event.getAttribute("id").split("-")[2];
            $("#item-" + adId).remove();
            $.post(
                '/automarket/remove-ad',
                {id: adId}
            );
        }

        function routeToEditPage(event) {
            var adId = event.getAttribute("id").split("-")[2];
            window.location.replace("/automarket/edit-ad?id=" + adId);
        }

        function wentBack() {
            window.location.replace("/automarket/ads");
        }


        function removeAccount() {
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/automarket/remove-user", true);
            xhttp.send();
        }

        function addAd() {
            window.location.replace("/automarket/add-ad");
        }

    </script>
</head>

<body>

<header>

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

                <form id="signup-form" action="${pageContext.request.contextPath}/edit-user" method="post" enctype="multipart/form-data">

                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="hidden" name="photoPath" value="${user.photoPath}"/>

                    <label for="name">Name:</label>
                    <input id="name" name="name" type="text" placeholder="Please, enter your name" value="${user.name}"/>

                    <label for="password">Password:</label>
                    <input id="password" name="password" type="password" placeholder="Please, enter your password" value="${user.password}"/>

                    <label for="email">Email:</label>
                    <input id="email" name="email" type="text" placeholder="Please, enter your email" value="${user.email}"/>

                    <label for="telnumber">Telnumber:</label>
                    <input id="telnumber" name="telNumber" type="text" placeholder="Please, enter your telnumber" value="${user.telNumber}"/>

                    <label for="photo">Photo:</label>
                    <input id="photo" type="file" name="photo" placeholder="Please, choose photo for your profile" onchange="previewFile()"/>

                </form>

            </div>

        </div>

        <div id="list">

            <div id="list-title">
                <h1 style="font-size: 30px;text-align: center;margin-bottom: 20px;">My advertisements:</h1>
            </div>

            <c:forEach var="ad" items="${ads}">
                <div id="item-${ad.id}" class="item">
                    <div class="img-box" onclick="showAd(this);">
                        <c:if test="${ad.photoPath != null}">
                            <img src="${pageContext.request.contextPath}/get-image?path=${ad.photoPath}" alt="car"/>
                        </c:if>
                        <c:if test="${ad.photoPath == null}">
                            <img src="${pageContext.request.contextPath}/resources/images/nophoto.png" alt="car"/>
                        </c:if>
                    </div>
                    <div class="item-info">
                        <div class="dv-name" onclick="showAd(this);">
                            <h3>${ad.title}</h3>
                        </div>
                        <div class="dv-description" onclick="showAd(this);">
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
                        <div class="dv-status" onclick="showAd(this);">
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
                        <div class="btns">
                            <button type="button" class="btn del-btn" id="del-btn-${ad.id}" onclick="removeAd(this)">Delete</button>
                            <button type="button" class="btn edit-btn" id="edit-btn-${ad.id}" onclick="routeToEditPage(this)">Edit</button>
                        </div>

                    </div>
                </div>
            </c:forEach>

            <div id="add-btn-div">
                <button type="button" id="add-btn" onclick="addAd()">Add</button>
            </div>

        </div>

        <div class="btns">
            <button type="button" class="del-btn" id="btn-cncl" onclick="wentBack()">Back</button>
            <button type="submit" class="edit-btn" id="btn-sbmt" form="signup-form">Submit</button>
        </div>

        <div style="display: flex; margin-top: 20px;" id="rmv-btn-div">
            <button style="flex: 1; background-color: red" class="del-btn" onclick="removeAccount()">Remove account</button>
        </div>

    </div>
    <div class="sidebar">

    </div>

</main>
<footer>

</footer>

</body>
</html>

