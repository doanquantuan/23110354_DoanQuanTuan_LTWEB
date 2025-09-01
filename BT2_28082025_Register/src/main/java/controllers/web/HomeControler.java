package controllers.web;

import java.io.IOException;

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

@WebServlet(urlPatterns = {"/home"})
public class HomeControler extends HttpServlet {

    private static final long serialVersionUID = 1L;

    IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().toString();

        if (url.contains("register")) {
            getRegister(req, resp);
        } else if (url.contains("login")) {
            getLogin(req, resp);
        } else if (url.contains("home")) {
            getHome(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().toString();

        if (url.contains("register")) {
            postRegister(req, resp);
        } else if (url.contains("login")) {
            postLogin(req, resp);
        }
    }

    // ==================== REGISTER ==================== //
    private void postRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");

        String alertMsg = "";

        if (userService.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("error", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);

        } else if (userService.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("error", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);

        } else {
            Email sm = new Email();
            String code = sm.getRandom();

            // tạo user tạm (chưa có password)
            UserModel user = new UserModel(username, email, fullname, code);

            boolean test = sm.sendEmail(user);

            if (test) {
                HttpSession session = req.getSession();
                session.setAttribute("account", user);

                boolean isSuccess = userService.register(email, password, username, fullname, code);

                if (isSuccess) {
                    resp.sendRedirect(req.getContextPath() + "/VerifyCode");
                } else {
                    alertMsg = "Lỗi hệ thống!";
                    req.setAttribute("error", alertMsg);
                    req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Lỗi khi gửi email xác thực!");
                req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            }
        }
    }

    protected void getRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    // ==================== LOGIN ==================== //
    private void postLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: xử lý login
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserModel user = userService.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", user);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    protected void getLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    // ==================== HOME ==================== //
    protected void getHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
    }

}
