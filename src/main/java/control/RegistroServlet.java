
package control;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.Usuario;
import modelo.UsuarioDB;

/**
 *
 * Servlet para gestionar el regsitro de sesion
 */
public class RegistroServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        // get parameters from the request
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
     
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String url = "";
        
        if(!password1.equals(password2)){
            
            url = "/Registro.html";
            RequestDispatcher dispatcher =
            getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
            out.println("<script>alert('Las contraseñas no coinciden.'); </script>");
        }else{
                // creamos un nuevo usuario con los parametros del form
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setUsername(username);
            usuario.setPassword(password1);
            
            
            
            // comprueba si el correo ya esta en el sistema
            /*if (UsuarioDB.emailExists(usuario.getEmail())) {
                out.println("<script>alert('Ya estás registrado en el sistema.'); </script>");
                
                url = "/InicioSesion.html";
            } else {*/
                int id = UsuarioDB.insert(usuario);
                usuario.setId(id);
                url = "/MainPage.html";
                // store the user in the session
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
            //}
            
            RequestDispatcher dispatcher =
            getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);

            
        }
                
        
        
    }
}

