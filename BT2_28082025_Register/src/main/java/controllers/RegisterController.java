package controllers;


import java.io.IOException;
import java.util.UUID;

import dao.impl.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserModel;
import services.IUserService;
import services.Impl.UserService;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

    IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("uname");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("psw");
        
        System.out.println("---- Dữ liệu form ----");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email: " + email);
        System.out.println("Fullname: " + fullname);
        System.out.println("----------------------");

        String alertMsg = "";

        // Kiểm tra dữ liệu rỗng
        if (username.isEmpty() || fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            alertMsg = "Vui lòng nhập đầy đủ thông tin!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra tài khoản đã tồn tại
        if (service.checkExistUsername(username)) {
            alertMsg = "Tên đăng nhập đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã được sử dụng!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Tạo user mới
        UserModel user = new UserModel(username, fullname, email, password);

        UserDaoImpl userDao = new UserDaoImpl();
        boolean isSuccess = service.register(email, password, username, fullname);
        if (isSuccess) {
            // Đăng ký thành công → chuyển sang login
        	System.out.print(userDao.findByUserName(username));
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "Tên đăng nhập hoặc email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
