<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manga Maniacs</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <h1>Benvenuti su Manga Maniacs</h1>
    </header>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="Login.jsp">Login</a></li>
            <li><a href="registrazione.jsp">Registrazione</a></li>
            <li><a href="ListaManga.jsp">Lista Manga</a></li>
        </ul>
    </nav>
    <main>
        <section>
            <h2>Ultime Uscite Manga</h2>
            <%
                // Simulazione di una lista di ultime uscite manga
                List<String> ultimeUscite = Arrays.asList("Naruto", "One Piece", "Attack on Titan");
                for (String manga : ultimeUscite) {
                    out.println("<p>" + manga + "</p>");
                }
            %>
        </section>
        <section>
            <h2>Unisciti alla Comunità</h2>
            <p>Registrati per unirti alla comunità di Manga Maniacs e connetterti con altri appassionati di manga.</p>
            <form action="registrazione.jsp" method="get">
                <input type="submit" value="Registrati">
            </form>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Manga Maniacs. Tutti i diritti riservati.</p>
    </footer>
</body>
</html>