package Controller;


import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.HttpStatus;

import DAO.MaterialDAO;
import Model.Material;
import Model.User;
import dbUtil.DBConnect;
import dbUtil.HibernateSF;

@Controller
public class MaterialController {
	
	//global database connection
	Connection conn = DBConnect.openConnection();
	
	
	//Student functions
	
	//display material in student dashboard
	@RequestMapping(value = "/material")
	public String display(HttpServletRequest request, HttpSession sess, ModelMap model) {
		
		//get session
		User student = (User)sess.getAttribute("user");
		
		//display data
		List<Material> m = MaterialDAO.display();
		
		//add to page
		model.addAttribute("m",m);
		model.addAttribute("sid",student);
		
		return "studentDashboard";
	}
	
	@RequestMapping(value = "/materialAdmin")
	public String displaym(HttpServletRequest request, ModelMap model) {
		
		List<Material> m = MaterialDAO.display();
		model.addAttribute("m",m);
		model.addAttribute("ma","admin");
		
		
		return "listBook";
		
	}

	
	@GetMapping("/updateBookForm") // Use path variable for the material id
    public String displayUpdateBook(@PathVariable("materialId") int matid, ModelMap model) {
        Material m = MaterialDAO.displayById(matid);
        model.addAttribute("m", m);
        return "updateBook";
    }

    // PUT method to update the book
	 @PostMapping("/updateBook/{materialid}")
	 public String updateBook(@PathVariable("materialid") int materialid, @ModelAttribute Material updatedMaterial, Model model) {
	     System.out.println("Received update request for materialId: " + materialid);
	     Material m = MaterialDAO.displayById(materialid);
	     if (m != null) {
	         System.out.println("Updating material: " + m.getTitle());
	         // Update the material with the new data
	         m.setIsbn(updatedMaterial.getIsbn());
	         m.setTitle(updatedMaterial.getTitle());
	         m.setAuthor(updatedMaterial.getAuthor());
	         m.setPublisher(updatedMaterial.getPublisher());
	         m.setVersion(updatedMaterial.getVersion());
	         m.setDescription(updatedMaterial.getDescription());
	         // Update other properties as needed
	         MaterialDAO.updateMaterial(
	             m.getMaterialid(),
	             m.getIsbn(),
	             m.getTitle(),
	             m.getAuthor(),
	             m.getPublisher(),
	             m.getVersion(),
	             m.getDescription()
	         );
	     }
	     model.addAttribute("material", m);
	     return "redirect:/materialAdmin"; // Redirect to the updated material view
	}

	//add Book
	//@RequestMapping(value = "/addBook")
	 @PostMapping("/addBook")
	 public String addBook(@ModelAttribute Material newMaterial, Model model, HttpServletRequest request) {
	     System.out.println("Received add request for material: " + newMaterial.getTitle());
	     
	     Session session= HibernateSF.getCurrentSession().openSession();
	     Transaction tx = session.beginTransaction();
	     
	     // Check if the material with the given ISBN already exists
	     List<Material> existingMaterials = MaterialDAO.display();
	     boolean exists = existingMaterials.stream().anyMatch(m -> m.getIsbn() == newMaterial.getIsbn());

	     if (exists) {
	         String message = "Material Already Exists";
	         request.setAttribute("message", message);
	         return "redirect:/addBook";
	     } else {
	         // Set properties of the new material
	         Material b = new Material();
	         b.setIsbn(newMaterial.getIsbn());
	         b.setTitle(newMaterial.getTitle());
	         b.setAuthor(newMaterial.getAuthor());
	         b.setPublisher(newMaterial.getPublisher());
	         b.setVersion(newMaterial.getVersion());
	         b.setDescription(newMaterial.getDescription());
	         b.setMaterialLink(newMaterial.getMaterialLink());
	         b.setType(newMaterial.getType());
	         
	         // Save the new material
	         session.save(b);
	         
	         model.addAttribute("material", b);
	         tx.commit();
			 session.close();
	         return "redirect:/materialAdmin"; // Redirect to the updated material view
	     }
	 }



	
	//displays details of material clicked by student
	@RequestMapping(value = "/materialDetails")
	public String displayById(HttpServletRequest request, HttpSession sess, ModelMap model) {
		
		//get session
		User student = (User)sess.getAttribute("user");
		model.addAttribute("sid",student);
		
		//perform display
		int matid = Integer.parseInt(request.getParameter("materialid"));
		Material m = MaterialDAO.displayById(matid);
		model.addAttribute("m",m);
		
		if(m.getType().equals("Book")) {
			
			model.addAttribute("btn","Apply Book");
			
		}else {
			model.addAttribute("btn","Download");
		}
		
		
		return "requestBook";
	}
	
	
	@RequestMapping(value = "/materialAdminDetails")
	public String displayByIdd(HttpServletRequest request, HttpSession sess, ModelMap model) {
		
		//get session
		User student = (User)sess.getAttribute("user");
		model.addAttribute("sid",student);
		
		//perform display
		int matid = Integer.parseInt(request.getParameter("materialid"));
		Material m = MaterialDAO.displayById(matid);
		model.addAttribute("m",m);
		
		if(m.getType().equals("Book")) {
			
			model.addAttribute("btn","Update Book");
			
		}else {
			model.addAttribute("btn","Download");
		}
		
		
		return "updateBook";
	}
	
	
	//displays details of material clicked by student
		@RequestMapping(value = "/matReq")
		public String requestMaterial(HttpServletRequest request, HttpSession sess, ModelMap model) {
			
			//get session
			User student = (User)sess.getAttribute("user");
			model.addAttribute("sid",student);
			
			//get material that is requested
			int matid = Integer.parseInt(request.getParameter("materialid"));
			Material m = MaterialDAO.displayById(matid);
			model.addAttribute("m",m);
			
			//get current date
			Date d = new Date();
			model.addAttribute("date",d);
			
			
			return "requestForm";
		}
		
		
		//search by Title
		@RequestMapping(value = "/search")
		public String search(HttpServletRequest request, HttpSession sess, ModelMap model) {
			
	
			//search details
			String searched = request.getParameter("title");
			List<Material> m = MaterialDAO.search(searched);
			
			//add to page
			model.addAttribute("m",m);
			
			return "studentDashboard";
		}
		
		@RequestMapping(value = "/materialeBook")
		public String displayeBook(HttpServletRequest request, ModelMap model) {
			
			List<Material> m = MaterialDAO.display();
			model.addAttribute("m",m);
			model.addAttribute("ma","admin");
			
			
			return "uploadMaterial";
			
		}
		
		@RequestMapping(value = "/materialPDF")
		public String displayPDF(HttpServletRequest request, ModelMap model) {
			
			List<Material> m = MaterialDAO.display();
			model.addAttribute("m",m);
			model.addAttribute("ma","admin");
			
			
			return "uploadOtherMaterial";
			
		}
		
		@RequestMapping(value = "/addBookForm")
		public String addBookForm(HttpServletRequest request, ModelMap model) {
			
			List<Material> m = MaterialDAO.display();
			model.addAttribute("m",m);
			model.addAttribute("ma","admin");
			
			
			return "addBook";
			
		}

		
		//delete Material
		@RequestMapping("/deletedMaterial")
		
		public String deletedMaterial(HttpServletRequest request, ModelMap model,HttpSession sess) {

			int materialid = Integer.parseInt(request.getParameter("materialid"));
			Material listReq = MaterialDAO.deleteMaterial(materialid);

			return "redirect:/materialAdmin";
		}
		
}

