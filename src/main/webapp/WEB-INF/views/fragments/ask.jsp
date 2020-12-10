<%--
  Created by IntelliJ IDEA.
  User: Elodie
  Date: 25.09.2020
  Time: 08:52
  To change this template use File | Settings | File Templates.
--%>

<c:forEach var="question" items="${questions.questions}">
    <p >${question.text}</p>
</c:forEach>
