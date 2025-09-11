package vn.iotstar.controllers.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryService;

@WebServlet(urlPatterns = "/user/home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ICategoryService cateService = new CategoryService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		
		List<Category> list = cateService.findAll();
		req.setAttribute("listcate", list);
		req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
	}
}
