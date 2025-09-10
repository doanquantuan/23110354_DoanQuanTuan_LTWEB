package vn.iotstar.controllers.manager;

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
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryService;

@WebServlet(urlPatterns = {"/manager/home", "/manager/categories", "/manager/category/add", "/manager/category/insert", "/manager/category/edit", "/manager/category/update", "/manager/category/delete", "/manager/category/search"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("home")) {
			HttpSession session = req.getSession(false);
			if (session == null || session.getAttribute("account") == null) {
	            // Chưa login → chuyển về trang login
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }

	        User user = (User) session.getAttribute("account");
	        int userId = user.getUserId();
			List<Category> list = cateService.findByUserId(userId);
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/manager/home.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/manager/category-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findByID(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/manager/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			cateService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/manager/home");
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {			
			HttpSession session = req.getSession(false);
			if (session == null || session.getAttribute("account") == null) {
	            // Chưa login → chuyển về trang login
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        }

	        User user = (User) session.getAttribute("account");
	        int userId = user.getUserId();
			
			String categoryname = req.getParameter("categoryName");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			String images = "https://dsfs.oppo.com/archives/201809/201809290509415baf4245b44fe.png";
			
			Category category = new Category();
			category.setCategoryName(categoryname);
			category.setImages(images);
			category.setStatus(statuss);
			category.setUserId(userId);
			
			cateService.insert(category);
			
			resp.sendRedirect(req.getContextPath() + "/manager/home");
		} else if (url.contains("update")){
			int categoryId = Integer.parseInt(req.getParameter("categoryId"));
			String categoryname = req.getParameter("categoryName");
			String status = req.getParameter("status");

			int statuss = Integer.parseInt(status);
			String images = "https://dsfs.oppo.com/archives/201809/201809290509415baf4245b44fe.png";
			
			Category category = new Category();
			
			category.setCategoryId(categoryId);
			category.setCategoryName(categoryname);
			category.setImages(images);
			category.setStatus(statuss);
			
			cateService.update(category);
			
			resp.sendRedirect(req.getContextPath() + "/manager/home");
		}
	}
}
