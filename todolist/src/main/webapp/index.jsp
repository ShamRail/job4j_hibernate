<!Doctype html>
<html>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <title>ToDo list</title>
    <style>
        <%@include file="/todolist/css/style.css"%>
    </style>
    <script>
        <%@include file="/todolist/js/script.js"%>
    </script>

</head>
<body>
<header style="color: white;">
    <h1 style="font-size: 30px;">Simple ToDo List</h1>
</header>
<main>

    <div class="sidebar"></div>
    <div class="content">

        <div id="add-div">
            <form>
                <input id="desc" type="text"  placeholder="Enter todo list item"/>
                <input id="add" type="button" value="Add"/>
            </form>
        </div>
        <div style="margin-bottom: 10px;">
            <input id="done" type="checkbox" onchange="hideOrShow(this)"/> Only undone
        </div>
        <div id="table-div">
            <table>

                <tr>
                    <th></th>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Date</th>
                </tr>

            </table>
        </div>

    </div>
    <div class="sidebar"></div>

</main>
<footer>
</footer>

</body>

</html>