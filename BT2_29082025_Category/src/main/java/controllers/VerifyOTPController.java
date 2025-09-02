package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verify-otp")
public class VerifyOTPController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String otpInput = request.getParameter("otp");
        HttpSession session = request.getSession();
        String otpSession = (String) session.getAttribute("otp");
        String email = (String) session.getAttribute("otpEmail");

        if (otpSession == null || email == null) {
            request.setAttribute("alert", "OTP đã hết hạn. Vui lòng thử lại!");
            request.getRequestDispatcher("/views/forgotPassword.jsp").forward(request, response);
            return;
        }

        if (otpInput.equals(otpSession)) {
            // Xác thực thành công → chuyển đến đổi mật khẩu
        	response.sendRedirect(request.getContextPath() + "/reset-password");
        } else {
            request.setAttribute("alert", "OTP không hợp lệ. Vui lòng nhập lại!");
            request.getRequestDispatcher("/views/verifyOtp.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/verifyOtp.jsp").forward(request, response);
    }
}
