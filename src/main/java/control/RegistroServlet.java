
package control;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import modelo.Usuario;
import modelo.UsuarioDB;

/**
 *
 * @author Mario
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
        
        if(password1 != password2){
            out.println("<script>{alert('Las contraseñas no coinciden.'); return false;} } </script>");
            out.println("</html>");
        }else{
                // use regular Java classes
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setUsername(username);
            usuario.setPassword(password1);

            String url = "";
            if (UsuarioDB.emailExists(usuario.getEmail())) {
                out.println("<script>{alert('Ya estás registrado en el sistema.'); return false;} } </script>");
                out.println("</html>");
                url = "/InicioSesion.html";
            } else {
                int id = UsuarioDB.insert(usuario);
                usuario.setId(id);
                url = "/MainPage.html";
                // store the user in the session
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
            }

            RequestDispatcher dispatcher =
            getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
                
        
        
    }
}

