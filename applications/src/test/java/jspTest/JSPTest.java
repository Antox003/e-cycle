package carzone.com.jspTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class JSPTest {

    @Mock
    private HttpServletRequest request;
    
    @Mock
    private HttpServletResponse response;
    
    @Mock
    private HttpSession session;
    
    @Mock
    private ServletContext servletContext;
    
    @Mock
    private RequestDispatcher dispatcher;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
    }

    @Test
    void testAdminLoginJSP() throws Exception {
        when(request.getRequestDispatcher("validatelogina.jsp")).thenReturn(dispatcher);
        request.getRequestDispatcher("validatelogina.jsp").forward(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testCustomerLoginJSP() throws Exception {
        when(request.getRequestDispatcher("validateloginc.jsp")).thenReturn(dispatcher);
        request.getRequestDispatcher("validateloginc.jsp").forward(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testAddProductJSP() throws Exception {
        when(request.getRequestDispatcher("addproduct.jsp")).thenReturn(dispatcher);
        request.getRequestDispatcher("addproduct.jsp").forward(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testCartJSP() throws Exception {
        when(request.getRequestDispatcher("cart.jsp")).thenReturn(dispatcher);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testOrdersJSP() throws Exception {
        when(request.getRequestDispatcher("orders.jsp")).thenReturn(dispatcher);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testAdminHomeJSP() throws Exception {
        when(request.getRequestDispatcher("adminhome.jsp")).thenReturn(dispatcher);
        request.getRequestDispatcher("adminhome.jsp").forward(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testCustomerHomeJSP() throws Exception {
        when(request.getRequestDispatcher("customerhome.jsp")).thenReturn(dispatcher);
        request.getRequestDispatcher("customerhome.jsp").forward(request, response);
        verify(dispatcher).forward(request, response);
    }
}
