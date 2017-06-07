<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 6/2/17
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 5/27/17
  Time: 12:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css' />
        <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap-theme.min.css' />
        <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
    <div class="panel panel-primary">
        <div class="panel-heading">User Login</div>

        <div class="panel-body">

            <form action="/login" method="POST">

                <label for="username_login_input">Username</label>
                <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            </span>
                    <input type="text" name="username" class="form-control" aria-describedby="basic-addon1" id="username_login_input">
                </div>

                <br />

                <label for="password_login_input">Password</label>
                <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                            </span>
                    <input type="password" name="password" class="form-control" aria-describedby="basic-addon1" id="password_login_input">
                </div>

                <br />

                <div class="input-group">
                    <div class="btn-group" role="group" aria-label="...">
                        <button type="button" class="btn btn-default">Sign Up</button>
                        <button type="submit" class="btn btn-default">Sign In</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </body>
</html>

