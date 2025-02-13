package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Register() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String datanascita = request.getParameter("date");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("numero");
        String redirectedPage = "/login.jsp";

        try {
            // Converti la data da String a java.sql.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date sqlDate = new Date(dateFormat.parse(datanascita).getTime());

            Connection con = DriverManagerConnectionPool.getConnection();
            String sql = "INSERT INTO UserAccount(Nome, Cognome, Data_nascita, Email, password, Telefono) VALUES (?, ?, ?, ?, MD5(?), ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setDate(3, sqlDate);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, telefono);
            
            ps.executeUpdate();
            DriverManagerConnectionPool.releaseConnection(con);


        } catch (ParseException e) {
            // Gestisci errori di parsing della data
            request.getSession().setAttribute("register-error", "Formato data non valido.");
            redirectedPage = "/registrazione.jsp";
            System.out.println("Errore nel parsing della data.");
        } catch (SQLException e) {
            // Gestisci errori SQL
            request.getSession().setAttribute("register-error", "Errore durante la registrazione. Riprova.");
            redirectedPage = "/registrazione.jsp";
            System.out.println("Errore SQL durante la registrazione: " + e.getMessage());

        }
        response.sendRedirect(request.getContextPath() + redirectedPage);
    }
}
