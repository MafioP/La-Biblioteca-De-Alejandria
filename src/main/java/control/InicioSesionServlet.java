/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.UsuarioDB;
import modelo.Usuario;

/**
 *
 * @author Mario
 */
public class InicioSesionServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // get parameters from the request
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
     
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String url = "";
            //comprobar si el usuario esta registrado en la base de datos y sino llevarle a la pagina de registro
            if (!UsuarioDB.userExists(username)) {
                out.println("<script>{alert('No estás registrado en el sistema.'); return false;} } </script>");
                out.println("</html>");
                url = "/Registro.html";
            } else {
                Usuario usuario = UsuarioDB.selectUserByName(username);     //sacar el usuario de la base de datos
                url = "/MainPage.html";
                // store the user in the session
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
            }
    }
    
}
