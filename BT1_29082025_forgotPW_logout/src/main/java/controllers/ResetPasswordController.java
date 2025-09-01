package controllers;

import java.io.IOException;
import dao.impl.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDaoImpl userDao = new UserDaoImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Có thể redirect về form forgot-password nếu chưa có session email
        HttpSession session = request.getSession();
        if (session.getAttribute("otpEmail") == null) {
            response.sendRedirect("forgot-password");
        } else {
            // Nếu đã xác thực OTP, hiển thị form đổi mật khẩu
            request.getRequestDispatcher("/views/resetPassword.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("otpEmail");

        if (email == null) {
            request.setAttribute("alert", "Phiên làm việc hết hạn. Vui lòng thử lại!");
            request.getRequestDispatcher("/views/forgotPassword.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("alert", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("/views/resetPassword.jsp").forward(request, response);
            return;
        }

        // Cập nhật mật khẩu trong DB
        if (userDao.updatePassword(email, newPassword)) {
            request.setAttribute("alert", "Đổi mật khẩu thành công!");
            session.removeAttribute("otp");
            session.removeAttribute("otpEmail");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("alert", "Có lỗi khi đổi mật khẩu. Vui lòng thử lại!");
            request.getRequestDispatcher("/views/resetPassword.jsp").forward(request, response);
        }
    }
}
