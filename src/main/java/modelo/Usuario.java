
package modelo;

import java.io.Serializable;

/**
 * 
 * Clase JavaBeans que implementa a un Usuario
 */
public class Usuario implements Serializable{
    private String email;
    private String username;
    private String password;
    private int id;
    
    public Usuario(){
        email = "";
        username = "";
        password = "";
        id = -1;
    }
    public Usuario(String email, String username, String password, int id){
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setId(id);
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
