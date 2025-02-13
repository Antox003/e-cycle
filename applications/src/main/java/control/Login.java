package control;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;

@WebServlet("/Login")
public class Login extends HttpServlet {

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String redirectedPage = "/ProductList";
        List<String> errors = new ArrayList<>();
        RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");
        Boolean control = false;

        if(email == null || email.trim().isEmpty()) {
            errors.add("Il campo email non può essere vuoto!");
        }
        if(password == null || password.trim().isEmpty()) {
            errors.add("Il campo password non può essere vuoto!");
        }
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            dispatcherToLoginPage.forward(request, response);
            return; 
        }
        
        email = email.trim();
        password = password.trim();
        
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String sql = "SELECT ID_ACCOUNT ,Nome, Cognome, Data_nascita, Email, password, Telefono, ruolo FROM UserAccount";
            
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            
            while (rs.next()) {
                if (email.compareTo(rs.getString(5)) == 0) {
                    String psw = checkPsw(password);
                    if (psw.compareTo(rs.getString(6)) == 0) {
                        control = true;
                        UserBean registeredUser = new UserBean();
                        registeredUser.setCode(rs.getInt(1));
                        registeredUser.setNome(rs.getString(2));
                        registeredUser.setCognome(rs.getString(3));
                        registeredUser.setData_nascita(rs.getString(4));
                        registeredUser.setEmail(rs.getString(5));
                        registeredUser.setTelefono(rs.getString(7));
                        registeredUser.setRole(rs.getString(8));
                        request.getSession().setAttribute("registeredUser", registeredUser);
                        request.getSession().setAttribute("role", registeredUser.getRole());
                        request.getSession().setAttribute("email", rs.getString(5));
                        request.getSession().setAttribute("nome", rs.getString(2));
                        request.getSession().setAttribute("ID_ACCOUNT", rs.getInt(1));
                        
                        if ("admin".equals(registeredUser.getRole())) {
                            request.getSession().setAttribute("isAdmin", Boolean.TRUE);
                        } else {
                            request.getSession().setAttribute("isAdmin", Boolean.FALSE);
                        }
                        
                        redirectedPage = "/home";
                        DriverManagerConnectionPool.releaseConnection(con);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("Si è verificato un errore durante l'accesso. Per favore riprova.");
            request.setAttribute("errors", errors);
            dispatcherToLoginPage.forward(request, response);
            return;
        }
        
        if (!control) {
            errors.add("Username o password non validi!");
            request.setAttribute("errors", errors);
            dispatcherToLoginPage.forward(request, response);
            return; // Important to stop execution here and not continue with the redirect
        }
        
        response.sendRedirect(request.getContextPath() + redirectedPage);
    }
        
    private String checkPsw(String psw) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] messageDigest = md.digest(psw.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        
        return hashtext;
    }
}
