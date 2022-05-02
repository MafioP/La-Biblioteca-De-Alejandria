
package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Archivo;
import modelo.ArchivoDB;
import modelo.Usuario;


@MultipartConfig
public class SubirArchivoServlet extends HttpServlet {

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
        Part contenido = request.getPart("contenido");
        long millis = System.currentTimeMillis();
        java.sql.Date fechaSubida = new java.sql.Date(millis);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        String url = "";
        
        Archivo archivo = new Archivo();
        archivo.setPropietario(usuario.getId());
        archivo.setNombre(nombre);
        archivo.setDescripcion(descripcion);
        archivo.setUniversidad(universidad);
        archivo.setGrado(grado);
        archivo.setCurso(curso);
        archivo.setCuatrimestre(cuatrimestre);
        archivo.setAsignatura(asignatura);
        archivo.setFechaSubida(fechaSubida);
        archivo.setContenido(contenido);
        
        int id = ArchivoDB.insert(archivo);
        archivo.setIdArchivo(id);
        url = "/MainPage.jsp";
        
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
