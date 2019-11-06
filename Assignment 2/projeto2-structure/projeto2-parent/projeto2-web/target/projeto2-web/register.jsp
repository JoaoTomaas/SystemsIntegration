<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>


<div style="text-align: center">
    <h1>Register</h1>
    <br>
</div>

<div style="text-align: right; margin-right: 45%">

    <form method="post" action="Register" >
        Email:<input type="email" name="email" /><br/><br/>
        Password:<input type="password" name="password1"/><br/><br/>
        Confirmar Password:<input type="password" name="password2"/><br/><br/>
        Nome :<input type="text" name="nome"/><br/><br/>
        Pais :<input type="text" name="pais"/><br/><br/>
        Idade :<input type="number" name="idade"/><br/><br/>
        <input type="submit" value="register"/>
    </form>
</div>
</body>
</html>
