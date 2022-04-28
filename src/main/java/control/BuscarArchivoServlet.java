
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ArchivoDB;

/**
 *
 * @author ZeR3
 */
public class BuscarArchivoServlet extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String orden = request.getParameter("orden");
        
        
        String url = "";
        
        // store the user in the session
            HttpSession session = request.getSession();
            
            session.setAttribute("orden", orden);
            url = "/MainPage.jsp";
            
            RequestDispatcher rs = request.getRequestDispatcher(url);
            rs.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String busqueda = request.getParameter("busqueda");
        
        String url = "";
        if (ArchivoDB.buscarArchivoNombre(busqueda).isEmpty()) {
            
            out.println("<script>alert('No se han encontrado coincidencias'); </script");
            url = "/MainPage.jsp";
                
        } else {
            
            // store the user in the session
            HttpSession session = request.getSession();
            
            session.setAttribute("variable", busqueda);
            url = "/MainPage.jsp";
            
            }
            RequestDispatcher rs = request.getRequestDispatcher(url);
                rs.include(request, response);
            
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
