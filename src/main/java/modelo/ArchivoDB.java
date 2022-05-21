
package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.servlet.http.Part;


public class ArchivoDB {
    
    private static ArrayList<Archivo> listaArchivos = new ArrayList<>();
    
    /**
     * Insertar un nuevo archivo en la base de datos
     * @param archivo
     * @return 
     */
    public static int insert(Archivo archivo) throws IOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO ARCHIVO (PROPIETARIO, NOMBRE, DESCRIPCION, UNIVERSIDAD, GRADO, CURSO, CUATRIMESTRE, ASIGNATURA, NUMVISTAS, FECHASUBIDA, NUMDESCARGAS, VALORACIONMEDIA, CONTENIDO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, archivo.getPropietario());
            ps.setString(2, archivo.getNombre());
            ps.setString(3, archivo.getDescripcion());
            ps.setString(4, archivo.getUniversidad());
            ps.setString(5, archivo.getGrado());
            ps.setInt(6, archivo.getCurso());
            ps.setInt(7, archivo.getCuatrimestre());
            ps.setString(8, archivo.getAsignatura());
            ps.setInt(9, archivo.getNumVistas());
            ps.setDate(10, archivo.getFechaSubida());
            ps.setInt(11, archivo.getNumDescargas());
            ps.setDouble(12, archivo.getValoracionMedia());
            ps.setBlob(13, archivo.getContenido().getInputStream());
            
            
            
            int res = 0;
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                res = rs.getInt(1);
            }
            ps.close();
            pool.freeConnection(connection);
            return res;
            
            } catch (SQLException e) {
            e.printStackTrace();
            return 0;
            }
    }
    
    /**
     * Seleccionar un usuario de la base de datos a partir de su nombre de usuario
     * @return listaArchivos
     */
    public static ArrayList<Archivo> getAllArchivos() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Archivo";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Archivo archivo = null;
            listaArchivos.clear();

            while (rs.next()) {
                archivo = new Archivo(rs.getString("nombre"), rs.getInt("idArchivo"),
                    rs.getInt("propietario"), rs.getString("descripcion"), rs.getString("universidad"),
                    rs.getString("grado"), rs.getInt("curso"), rs.getInt("cuatrimestre"),
                    rs.getString("asignatura"), rs.getInt("numVistas"), rs.getDate("fechaSubida"),
                    rs.getInt("numDescargas"), rs.getDouble("valoracionMedia"), null);
                
                listaArchivos.add(archivo);
                
            }
            
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaArchivos;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
          }       
    }
    
    /**
     * Busca un archivo en la base de datos en funcion del nombre
     * 
     * @param name
     * @return listaArchivos
     */
    public static ArrayList<Archivo> buscarArchivoNombre(String name) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Archivo WHERE nombre LIKE ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%"+name+"%");
            rs = ps.executeQuery();
            Archivo archivo = null;
            listaArchivos.clear();
            
            while (rs.next()) {
                archivo = new Archivo();
                archivo.setIdArchivo(rs.getInt("idArchivo"));
                archivo.setNombre(rs.getString("Nombre"));
                archivo.setPropietario(rs.getInt("Propietario"));
                archivo.setDescripcion(rs.getString("Descripcion"));
                archivo.setUniversidad(rs.getString("Universidad"));
                archivo.setGrado(rs.getString("Grado"));
                archivo.setCurso(rs.getInt("Curso"));
                archivo.setCuatrimestre(rs.getInt("Cuatrimestre"));
                archivo.setAsignatura(rs.getString("Asignatura"));
                archivo.setNumVistas(rs.getInt("numVistas"));
                archivo.setFechaSubida(rs.getDate("FechaSubida"));
                archivo.setNumDescargas(rs.getInt("numDescargas"));
                archivo.setValoracionMedia(rs.getInt("ValoracionMedia"));
                
                
                listaArchivos.add(archivo);
                
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return listaArchivos;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
          }       
    }
    
    
    /**
     * Ordena los archivos en funcion del parametro orden
     * 
     * @param orden
     * @return listaArchivos
     */
    public static ArrayList<Archivo> ordenarArchivos(String orden){
        
        switch(orden){
            case "0":
                Collections.sort(listaArchivos, new Comparator<Archivo>(){
                    public int compare(Archivo a1, Archivo a2){
                        if(a1.getValoracionMedia() == a2.getValoracionMedia())
                            return 0;
                        return a2.getValoracionMedia() < a1.getValoracionMedia() ? -1 : 1;
                    }
                });
                
                break;
                
                
            case "1":
                
                Collections.sort(listaArchivos, new Comparator<Archivo>(){
                    public int compare(Archivo a1, Archivo a2){
                        if(a1.getNumDescargas() == a2.getNumDescargas())
                            return 0;
                        return a2.getNumDescargas() < a1.getNumDescargas() ? -1 : 1;
                    }
                });
                
                break;
                
                
            case "2":
                Collections.sort(listaArchivos, new Comparator<Archivo>(){
                    public int compare(Archivo a1, Archivo a2){
                        if(a1.getNumVistas() == a2.getNumVistas())
                            return 0;
                        return a2.getNumVistas() < a1.getNumVistas() ? -1 : 1;
                    }
                });
                
                break;
                
            case "3":
                Collections.sort(listaArchivos, new Comparator<Archivo>() {
                    public int compare(Archivo a1, Archivo a2) {
                        return a2.getFechaSubida().compareTo(a1.getFechaSubida());
                    }
                });
                
                break;
                
            case "4":
                Collections.sort(listaArchivos, new Comparator<Archivo>() {
                    public int compare(Archivo a1, Archivo a2) {
                        return a1.getNombre().compareTo(a2.getNombre());
                    }
                });
                
                break;
                
            default:
             
        }
        
        return listaArchivos;
    }
    
    
    /**
     * Seleccionar un archivo de la base de datos a partir de su id de archivo
     * @param id
     * @return 
     */
    public static Archivo selectFileById(int idArchivo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM Archivo WHERE idArchivo = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idArchivo);
            rs = ps.executeQuery();
            Archivo archivo = null;

            if (rs.next()) {
                archivo = new Archivo();
                archivo.setIdArchivo(rs.getInt("idArchivo"));
                archivo.setPropietario(rs.getInt("propietario"));
                archivo.setNombre(rs.getString("nombre"));
                archivo.setDescripcion(rs.getString("descripcion"));
                archivo.setUniversidad(rs.getString("universidad"));
                archivo.setGrado(rs.getString("grado"));
                archivo.setCurso(rs.getInt("curso"));
                archivo.setCuatrimestre(rs.getInt("cuatrimestre"));
                archivo.setAsignatura(rs.getString("asignatura"));
                archivo.setNumVistas(rs.getInt("numVistas"));
                archivo.setFechaSubida(rs.getDate("fechaSubida"));
                archivo.setNumDescargas(rs.getInt("numDescargas"));
                archivo.setValoracionMedia(rs.getDouble("valoracionMedia"));
                
                
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return archivo;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
          }       
    }
    
    /**
     * Obtener un archivo de la base de datos a partir de su id de archivo
     * @param id
     * @param respuesta
     */
    public static void getFile(int idArchivo, OutputStream respuesta) throws IOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT contenido FROM Archivo WHERE idArchivo = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idArchivo);
            rs = ps.executeQuery();
            

            if (rs.next()) {
                Blob blob = rs.getBlob("contenido");
                if(!rs.wasNull() && blob.length() > 1){
                    InputStream archivo = blob.getBinaryStream();
                    byte[] buffer = new byte[1000];
                    int len = archivo.read(buffer);
                    while(len != -1){
                        respuesta.write(buffer, 0, len);
                        len = archivo.read(buffer);
                    }
                    archivo.close();
                }
            }
            
            pool.freeConnection(connection);
     
        } catch (SQLException e) {
            e.printStackTrace();
          }       
    }
    
    /**
     * Actualiza el numero de vistas de un archivo en la base de datos
     * @param archivo
     * 
     */
    public static void updateNumVistas(Archivo archivo) throws IOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE ARCHIVO SET NumVistas = ? WHERE idArchivo = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, archivo.getNumVistas());
            ps.setInt(2, archivo.getIdArchivo());
            
            ps.executeUpdate();
            

            ps.close();
            pool.freeConnection(connection);
            
            
            } catch (SQLException e) {
            e.printStackTrace();
            
            }
    }
    
    /**
     * Actualiza el numero de descargas de un archivo en la base de datos
     * @param archivo
     * 
     */
    public static void updateNumDescargas(Archivo archivo) throws IOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE ARCHIVO SET NumDescargas = ? WHERE idArchivo = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, archivo.getNumDescargas());
            ps.setInt(2, archivo.getIdArchivo());
            
            ps.executeUpdate();
            

            ps.close();
            pool.freeConnection(connection);
            
            
            } catch (SQLException e) {
            e.printStackTrace();
            
            }
    }
    
    /**
     * Actualiza la valoracion media de un archivo en la base de datos
     * @param archivo
     * 
     */
    public static void updateValoracionMedia(Archivo archivo) throws IOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE ARCHIVO SET valoracionMedia = ? WHERE idArchivo = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, archivo.getValoracionMedia());
            ps.setInt(2, archivo.getIdArchivo());
            
            ps.executeUpdate();
            

            ps.close();
            pool.freeConnection(connection);
            
            
            } catch (SQLException e) {
            e.printStackTrace();
            
            }
    }
    
}
