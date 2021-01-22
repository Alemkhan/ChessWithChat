<%--
  Created by IntelliJ IDEA.
  User: alemh
  Date: 17.01.2021
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <style>
            body{
                height: 100%;
            }
            #cover {
                background: #222 url('https://unsplash.it/1920/1080/?random') center center no-repeat;
                background-size: cover;
                height: 100%;
                text-align: center;
                display: flex;
                align-items: center;
                position: relative;
            }
            form:before {
                content: '';
                height: 100%;
                left: 0;
                position: absolute;
                top: 0;
                width: 100%;
                background-color: rgba(0,0,0,0.3);
                z-index: -1;
                border-radius: 10px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    Chat and Chess
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <section id="cover">
            <div class="container mt-5 col-6">
               <div class="row text-white">
                   <form class="justify-content-center" method="post" action="LoginServlet">
                       <legend>Login to Chat or Play</legend>
                       <div class="mb-3">
                           <label for="name">Username:</label>
                           <input id="name" class="form-control" type="text" name="username" required>
                       </div>
                       <div class="mb-3">
                           <label for="mySelect">Select where you want to connect:</label>
                           <select class="form-select" name="selection" id="mySelect">
                               <option value="Chat">Chat</option>
                               <option value="Chess">Chess</option>
                           </select>
                       </div>
                       <input type="submit" class="btn btn-primary btn-lg" name="helloepta" value="JOIN">
                   </form>
               </div>
            </div>
        </section>

    </body>
</html>
