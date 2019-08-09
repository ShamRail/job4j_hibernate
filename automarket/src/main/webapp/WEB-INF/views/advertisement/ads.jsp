<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.models.annotatedmodels.Advertisement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>Ads</title>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
    <script>

        function showAd(event) {
            var id = event.getAttribute("id");
            window.location.replace("/automarket/get-ad?id=" + id);
        }

        function changeState(event) {
            var prevState = $("#mark").prop("disabled");
            $("#mark").prop("disabled", !prevState);
        }

    </script>
</head>
<body>

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
    <div id="sidebar">
        <form action="${pageContext.request.contextPath}/filter" method="get">
            <fieldset>
                <legend>Filters:</legend>

                <input type="checkbox" name="withPhoto" value="true"> With photo<br>
                <input type="checkbox" name="free" value="true"> Free<br>
                <input type="checkbox" name="sort" value="true"> Sort by date<br>
                <input type="checkbox" name="withMark" value="true" onchange="changeState(this)"> By mark<br>

                <label for="mark">Choose a mark:</label><br>
                <select id="mark" name="mark" disabled>
                    <option value="BMW">BMW</option>
                    <option value="Audi">Audi</option>
                    <option value="Bently">Bently</option>
                </select>
            </fieldset>
            <input id="appl-btn" type="submit" value="Apply"/>
        </form>
    </div>

    <div id="list">
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

        <form id="f" method="get" action="${pageContext.request.contextPath}/add-ad"></form>
        <c:if test="${id != null && id != -1}">
            <div id='add-btn-div'>
                <input type='submit' id='add-btn' form='f' value='Add'/>
            </div>
        </c:if>
    </div>

    <div id="sidebar2"></div>
</main>


<footer>

</footer>
</body>
</html>
