<%@page import="modelo.UsuarioDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.ArchivoDB"%>
<%@page import="modelo.Archivo"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biblioteca Alejandría</title>

    <!--CUSTOM CSS-->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
  
  <div class="container">
    <nav class="navMain">    
      <div id="sideNavMenu" class="sideNav" onmouseleave="closeBiblioteca()">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="InicioSesion.html">Mi biblioteca</a>
        <a href="InicioSesion.html">Subir Archivos</a>
        <a href="#">Ajustes</a>
      </div>
      <span onclick="openNav()"><img src="img/menuLogo.png"  class="menu-sidebar"></span>     
        <ul class="navBanner">
          <a href="MainPage.jsp"><img src="img/Logo.png" class= "bannerLogo" alt="Logo Imagen"></a>
        </ul>
        <ul class="navLogIn">
            <div class="dropdown">
                <button class="dropbtn"><img src="img/logoInicioSesion.png" width="50px"></button>
                <div class="dropdown-content">
                  <a href="InicioSesion.html">Iniciar sesión</a>
                  <a href="Registro.html">Registrarse</a>
                </div>
              </div>
        </ul>    
    </nav>
    <div class="filtros">
            <div title="Filtrar"><img src="img/filtro.png" alt="filtro icono" id="iconoFiltro"></div>
            <div class="box">
                <select>
                  <option>Universidad de Valladolid</option>
                  <option>Universidad de Valencia</option>
                  <option>Universidad de Barcelona</option>
                </select>
              </div>
            <div class="box">
                <select>
                  <option>Ingeniería Informática</option>
                  <option>Educación Primaría</option>
                  <option>Ingeniería Industrial</option>
                  
                </select>
              </div>
            <div class="box">
                <select>
                  <option>Primer Curso</option>
                  <option>Segundo Curso</option>
                  <option>Tercer Curso</option>
                  <option>Cuarto Curso</option>
                </select>
              </div>
            <div class="box">
                <select>
                  <option>Primer Cuatrimestre</option>
                  <option>Segundo Cuatrimestre</option>
                </select>
              </div>
            <div class="box">
                <select>
                  <option>Servicios y Sistemas Web</option>
                  <option>Física</option>
                  <option>Fundamentos de Matemáticas</option>
                </select>
              </div>
              <div class="box">
                <select>
                  <option>PDF</option>
                  <option>Word</option>
                  <option>Imagen</option>
                  <option>Presentación</option>
                </select>
              </div>
        
    </div>
    <div class="buscarOrdenar">
          <!-- Search -->
          <div class="wrap">
            <form action="BuscarArchivoServlet" method="post">
            <div class="search" >
                
              <input type="text" name="busqueda" class="searchTerm" placeholder="¿Qué estás buscando?">
              <button type="submit" class="searchButton">
                <img src="img/buscar.png" id="icono">
              </button>
               
            </div>
            </form>
        </div>
        <div class="ordenar">
          <img title="Ordenar" src="img/ordenar.png" alt="ordenar icono" id="icono">
          <div class="box">
            <select>
              <option>Mejor valorados</option>
              <option>Más descargados</option>
              <option>Más vistos</option>
              <option>Más recientes</option>
              <option>Alfabéticamente</option>
            </select>
          </div>
        </div>
        
    </div>
      
    <div class="resultados">
        
        <% 
          ArrayList<Archivo> archivos = ArchivoDB.getAllArchivos();  
          
          
          %>
      <table class="tabla-resultados">
          <% 
            for(int i=0; i < archivos.size(); i++){
          %>
            <tr onclick="window.location.href='VisualizarArchivo.html';">
                <td><%= archivos.get(i).getNombre()%></td>
                <td><img src="img/view.png" alt="visto icono" id="icono"><%= archivos.get(i).getNumVistas()%> vistas</td>
                <td><img src="img/descarga.png" alt="descarga icono" id="icono"><%= archivos.get(i).getNumDescargas()%> descargas</td>
                <td><img src="img/estrella.png" alt="estrella icono" id="icono"><%= archivos.get(i).getValoracionMedia()%>/5 valoración</td>
                <td><img src="img/logoInicioSesion.png" alt="persona icono" id="icono"><%= UsuarioDB.selectUserById(archivos.get(i).getPropietario()).getUsername()%></td>
            </tr>        
          <%
            }
          %>  
        
      </table>
    </div>
  </div>
  <!--CUSTOM JS-->
  <script>
    /*Funcionalidad de SideBar */
  function openNav() {
    document.getElementById("sideNavMenu").style.width = "250px";
  }

  /*Pone el width = 0 del sidebar*/
  function closeNav() {
    document.getElementById("sideNavMenu").style.width = "0";
  }

  function openBiblioteca() {
    document.getElementById("itemBiblio1").style.display = "block";
    document.getElementById("itemBiblio2").style.display = "block";
    document.getElementById("itemBiblio3").style.display = "block";
  }
  function closeBiblioteca() {
    document.getElementById("itemBiblio1").style.display = "none";
    document.getElementById("itemBiblio2").style.display = "none";
    document.getElementById("itemBiblio3").style.display = "none";
  }
  </script>
</body>
</html>