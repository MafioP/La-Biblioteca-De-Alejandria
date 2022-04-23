
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Archivo;
import modelo.ArchivoDB;

/**
 *
 * @author ZeR3
 */
public class SubirArchivoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SubirArchivoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubirArchivoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        
        // get parameters from the request
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
     
        String nombre = request.getParameter("nombre");
        String universidad = request.getParameter("universidad");
        String grado = request.getParameter("grado");
        int curso = Integer.parseInt(request.getParameter("curso"));
        int cuatrimestre = Integer.parseInt(request.getParameter("cuatrimestre"));
        String asignatura = request.getParameter("asignatura");
        String descripcion = request.getParameter("descripcion");
        
        String url = "";
        
        Archivo archivo = new Archivo();
        archivo.setNombre(nombre);
        archivo.setUniversidad(universidad);
        archivo.setGrado(grado);
        archivo.setCurso(curso);
        archivo.setCuatrimestre(cuatrimestre);
        archivo.setAsignatura(asignatura);
        archivo.setDescripcion(descripcion);
        
        int id = ArchivoDB.insert(archivo);
        archivo.setId(id);
        url = "/MainPage.html";
        
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
