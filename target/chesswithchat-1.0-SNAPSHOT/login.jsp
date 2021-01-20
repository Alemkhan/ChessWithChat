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
    </head>
    <body>

        <form method="post" action="LoginServlet">
            <input type="text" name="username" required>
            <select name="selection" id="">
                <option value="Chat">Chat</option>
                <option value="Chess">Chess</option>
            </select>
            <input type="submit" name="helloepta" value="JOIN">
        </form>

    </body>
</html>
