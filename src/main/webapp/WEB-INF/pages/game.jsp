<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Kalaha</title>

        <link href='http://fonts.googleapis.com/css?family=Lekton' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Mouse+Memoirs' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/board.css" />" />
	</head>
	
	<body>
        <h1 class="title">KALAHA</h1>
        <div class="container">
            <div class="message">
                ${message}
            </div>
            <table class="gameBoard" border="1">
                <thead>
                    <td>KALAHA 2</td>
                    <td>PIT</td>
                    <td>PIT</td>
                    <td>PIT</td>
                    <td>PIT</td>
                    <td>PIT</td>
                    <td>PIT</td>
                    <td>KALAHA 1</td>
                </thead>
                <tr>
                    <c:choose>
                        <c:when test="${game.player2.active}">
                            <td rowspan="2">
                                ${game.board.pits[totalPits - 1].noOfseeds}
                            </td>
                            <c:forEach var="i" begin="1" end="${rowLength - 1}" step="1" varStatus ="status">
                                <td>
                                    <a href="/move.do?pitId=${game.board.pits[totalPits - 1 - i].id}">${game.board.pits[totalPits - 1 - i].noOfseeds}</a>
                                </td>
                            </c:forEach>
                            <td rowspan="2">
                                ${game.board.pits[totalPits - 1 - rowLength].noOfseeds}
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td rowspan="2">
                                ${game.board.pits[totalPits - 1].noOfseeds}
                            </td>
                            <c:forEach var="i" begin="1" end="${rowLength - 1}" step="1" varStatus ="status">
                                <td>
                                    ${game.board.pits[totalPits - 1 - i].noOfseeds}
                                </td>
                            </c:forEach>
                            <td rowspan="2">
                                ${game.board.pits[totalPits - 1 - rowLength].noOfseeds}
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <c:choose>
                        <c:when test="${game.player1.active}">
                            <c:forEach var="i" begin="0" end="${rowLength - 2}" step="1" varStatus ="status">
                                <td>
                                    <a href="/move.do?pitId=${game.board.pits[i].id}">${game.board.pits[i].noOfseeds}</a>
                                </td>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="0" end="${rowLength - 2}" step="1" varStatus ="status">
                                <td>
                                        ${game.board.pits[i].noOfseeds}
                                </td>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </table>
        </div>
	</body>
</html>
