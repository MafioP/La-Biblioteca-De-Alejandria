/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.TagTreeDB;

/**
 *
 * @author Mario
 */
@WebServlet(name = "FiltrarBusquedaServlet", urlPatterns = {"/FiltrarBusquedaServlet"})
public class FiltrarBusquedaServlet extends HttpServlet {

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
            out.println("<title>Servlet FiltrarBusquedaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FiltrarBusquedaServlet at " + request.getContextPath() + "</h1>");
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
        String encoding = request.getCharacterEncoding();
        if(encoding==null){
            request.setCharacterEncoding("UTF-8");
        }
        response.setContentType("text/html;charset=UTF-8");
        
        String uniSelect = request.getParameter("uniSelect");
        String gradoSelect = request.getParameter("gradoSelect");
        String cursoSelect = request.getParameter("cursoSelect");
        String cuatriSelect = request.getParameter("cuatriSelect");
        String asigSelect = request.getParameter("asigSelect");
        
        
        
        ArrayList<String> unis = TagTreeDB.getOptions("root");
        System.out.println("Select: " + uniSelect + " " + gradoSelect + " " 
            + cursoSelect + " " + cuatriSelect + " " + asigSelect);
        
        HttpSession session = request.getSession();
        session.setAttribute("uniSelect", uniSelect);
        session.setAttribute("gradoSelect", gradoSelect);
        session.setAttribute("cursoSelect", cursoSelect);
        session.setAttribute("cuatriSelect", cuatriSelect);
        session.setAttribute("asigSelect", asigSelect);
        
        ArrayList<String> grados = new ArrayList<>();
        ArrayList<String> cursos = new ArrayList<>();
        ArrayList<String> cuatris = new ArrayList<>();
        ArrayList<String> asigs = new ArrayList<>();
        
        if (uniSelect != "0" && uniSelect !=null) {
            String uni = unis.get(Integer.parseInt(uniSelect)-1);
            for (int i = 0; i < TagTreeDB.getOptions(uni).size(); i++) {
                grados.add(TagTreeDB.getOptions(uni).get(i));
            }
            session.setAttribute("gradoData", grados);
            
            if (gradoSelect != "0" && gradoSelect!=null) {
                System.out.println("gradoSelect: " + gradoSelect);
                grados = (ArrayList<String>)session.getAttribute("gradoData");
                String grado = grados.get(Integer.parseInt(gradoSelect)-1);
                for (int i = 0; i < TagTreeDB.getOptions(grado).size(); i++) {
                    cursos.add(TagTreeDB.getOptions(grado).get(i));
                }
                session.setAttribute("cursoData", cursos);
            }
        }
        if (gradoSelect != "0" && gradoSelect!=null) {
            System.out.println("gradoSelect: " + gradoSelect);
            grados = (ArrayList<String>)session.getAttribute("gradoData");
            String grado = grados.get(Integer.parseInt(gradoSelect)-1);
            for (int i = 0; i < TagTreeDB.getOptions(grado).size(); i++) {
                cursos.add(TagTreeDB.getOptions(grado).get(i));
            }
            session.setAttribute("gradoData", grados);
            session.setAttribute("cursoData", cursos);
        }
        
        /*if (cursoSelect != "0" && cursoSelect!=null) {
            String curso = cursos.get(Integer.parseInt(cursoSelect)-1);
            for (int i = 0; i < TagTreeDB.getOptions(curso).size(); i++) {
                cuatris.add(TagTreeDB.getOptions(curso).get(i));
            }
            session.setAttribute("gradoSelectForm", grados);
            session.setAttribute("cursoSelectForm", cursos);
            session.setAttribute("cuatriSelectForm", cuatris);
            session.setAttribute("asigSelectForm", asigs);
        }
        
        if (cuatriSelect != "0" && cuatriSelect!=null) {
            String cuatri = cuatris.get(Integer.parseInt(cuatriSelect)-1);
            for (int i = 0; i < TagTreeDB.getOptions(cuatri).size(); i++) {
                asigs.add(TagTreeDB.getOptions(cuatri).get(i));
            }
            session.setAttribute("gradoSelectForm", grados);
            session.setAttribute("cursoSelectForm", cursos);
            session.setAttribute("cuatriSelectForm", cuatris);
            session.setAttribute("asigSelectForm", asigs);
        }*/
        
        
        
        
        

        String url = "/MainPage.jsp";

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
        processRequest(request, response);
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
