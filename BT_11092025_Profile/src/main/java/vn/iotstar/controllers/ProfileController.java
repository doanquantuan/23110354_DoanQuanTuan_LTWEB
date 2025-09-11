package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserService;

@WebServlet(urlPatterns = {"/profile", "/profile/update"})
public class ProfileController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public IUserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("update")) {
			req.getRequestDispatcher("/views/profile-update.jsp").forward(req, resp);
		} else {
			HttpSession session = req.getSession(false);
		    if (session == null || session.getAttribute("account") == null) {
		        resp.sendRedirect(req.getContextPath() + "/login");
		        return;
		    }

		    User user = (User) session.getAttribute("account");
		    req.setAttribute("user", user);  // Truyền user sang JSP

			req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("update")) {
			// Lấy thông tin từ form
		    String fullname = req.getParameter("fullname");
		    String phone = req.getParameter("phone");
		    String images = req.getParameter("images");

		    // Lấy user từ session
		    HttpSession session = req.getSession(false);
		    if (session == null || session.getAttribute("account") == null) {
		        resp.sendRedirect(req.getContextPath() + "/login");
		        return;
		    }

		    User user = (User) session.getAttribute("account");
		    int userId = user.getUserId();

		    // Cập nhật thông tin mới
		    user.setFullName(fullname);
		    user.setPhone(phone);
		    user.setImages(images);

		    // Gọi service để lưu vào DB
		    userService.updateprofile(userId, fullname, phone, images);

		    // Cập nhật lại session với dữ liệu mới
		    session.setAttribute("account", user);

		    // Quay về trang profile
		    resp.sendRedirect(req.getContextPath() + "/profile");
		} else {
			//truyền fullname, phone, images 
			req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
		}
	}

}
