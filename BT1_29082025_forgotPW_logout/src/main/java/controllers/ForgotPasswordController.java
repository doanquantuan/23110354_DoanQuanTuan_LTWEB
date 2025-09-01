package controllers;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import dao.impl.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;
import services.IUserService;
import services.Impl.UserService;
import utils.Email;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/forgotPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserModel user = userDao.findByEmail(email);
        System.out.println("Email: " + email);

        if (user == null) {
            request.setAttribute("alert", "Email không tồn tại trong hệ thống!");
            request.getRequestDispatcher("/views/forgotPassword.jsp").forward(request, response);
            return;
        }

        // Sinh OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        // Lưu OTP vào session
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("otpEmail", email);
        session.setMaxInactiveInterval(5 * 60); // 5 phút

        // Cập nhật OTP vào database
        if (!userDao.updateCode(email, otp)) {
            request.setAttribute("alert", "Có lỗi khi tạo OTP. Vui lòng thử lại!");
            request.getRequestDispatcher("/views/forgotPassword.jsp").forward(request, response);
            return;
        }

        // Gửi email
        try {
        	user.setCode(otp);
            Email.sendEmail(user); // User chứa email và code
            request.setAttribute("alert", "OTP đã được gửi đến email của bạn!");
            response.sendRedirect(request.getContextPath() + "/verify-otp");
            System.out.println("otp: " + otp);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alert", "Không thể gửi email. Vui lòng thử lại!");
            request.getRequestDispatcher("/views/forgotPassword.jsp").forward(request, response);
        }
    }
}
