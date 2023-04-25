<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="email code">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <br>
        <div style="text-align: center; font-size: 1.4em">
            <b>Edge Connect 边际互联</b>
        </div>
        <br>
        <div style="text-align: center">
            你的验证码如下，<span th:text="${timeout}"/>分钟内有效，请尽快完成验证。
        </div>
        <br>
        <div style="text-align: center; font-size: 2em">
            <b><span th:text="${code}"/></b>
        </div>
        <br><br>
        <div style="text-align: center">
            官网：<a href="https://edge.akagiyui.com" target="_blank">edge.akagiyui.com</a>
        </div>
        <br>
        <div></div>
        <br>
    </body>
</html>

