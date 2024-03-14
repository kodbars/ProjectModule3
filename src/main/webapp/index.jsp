<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Квест-игра</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>Добро пожаловать!<br></h1>
<p>Квест-игра. Каждый следующий шаг зависит от вашего выбора. Есть только один способ выиграть. Хотите попробовать?</p>
<form action="${pageContext.request.contextPath}/game" method="post">
    <button type="submit" class="button start">Старт!</button>
</form>
</body>
</html>