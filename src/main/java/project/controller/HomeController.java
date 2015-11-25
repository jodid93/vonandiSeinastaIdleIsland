package project.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.service.UserInfo;
import project.service.DBconnector;
import project.service.util;

@Controller
public class HomeController {

	private DBconnector DBconnector;
	private util util = new util();
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		
		session.setAttribute("loggedInUser", null);

		return "redirect:/login";
		
	}
	
	@RequestMapping(value="/")
	public String redirectToLogin(){
		
		return "redirect:/login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String displayLogInPage(Model model, HttpSession session) throws SQLException{
		this.DBconnector = new DBconnector();
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String Authorize(@ModelAttribute("notandi") UserInfo notandi, BindingResult result, Model model, HttpSession session) throws SQLException{
		
		if(result.hasErrors()){
			return "login";
		}
		
		if(this.DBconnector.isValidUser(notandi.getUserName(), notandi.getPW())){
			String Message = notandi.getPW()+" thetta test";
			model.addAttribute("skilabod", Message);
			session.setAttribute("loggedInUser", notandi.getUserName());
			System.out.println("ege er herna nuna hvad er ad");
			return "redirect:/menu";
		}else{
			model.addAttribute("skilabod", "Invalid credentials");
			return "login";
		}
	}
	
	@RequestMapping(value="/newRegestry")
	public String registerNewUser(Model model, HttpSession session){
		
		return "register";
	}
	
	@RequestMapping(value="/menu")
	public String displayMenu(Model model, HttpSession session){
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		

		session.getAttribute("loggedInUser");
		model.addAttribute("skilabod", session.getAttribute("loggedInUser")+ " er skradur inn");
		
		return "menu";
	}
	
	@RequestMapping(value = "/addFriend", method = RequestMethod.GET)
	public String addFirend(Model model, HttpSession session){
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		return "addFriend";
	}
	
	@RequestMapping(value = "/addFriend", method = RequestMethod.POST)
	public String addFirends(@ModelAttribute("UserName") String user, Model model, HttpSession session)throws SQLException{
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String m; 
		
		if(this.DBconnector.findUser(user) != null){
			String friendList = this.DBconnector.findFriendList((String)session.getAttribute("loggedInUser"));
			String[] list = this.util.parseFriendsList(friendList);
			if(util.isUserAlreadyFriend(list, user)){
				m = "User is already your friend";
			}else{
				m = "User has been added to your friends list";
				this.DBconnector.updateFriendsList(session.getAttribute("loggedInUser").toString(), user);
				this.DBconnector.updateFriendsList(user, session.getAttribute("loggedInUser").toString());
			}
		}else{
			m = "User does not exist";
		}
		
		model.addAttribute("skilabod", m);
		
		return "addFriend";
	}
	
	@RequestMapping(value="/registered")
	public String RegisterSuccess(@ModelAttribute("notandi") UserInfo notandi, BindingResult result, Model model, HttpSession session) throws SQLException{
		
		String Message = "ny skraning tokst fyrir notenda";
		model.addAttribute("skilabod", Message);
		
		if(notandi.getUserName().contains(",")){
			model.addAttribute("skilabod", "Username contains ','");
			return "register";
		}
		
		if(this.DBconnector.registrationSuccess(notandi.getUserName(), notandi.getPW())){
			this.DBconnector.createNewUser(notandi.getUserName(), notandi.getPW());
			
			session.setAttribute("loggedInUser", notandi.getUserName());
			return "redirect:/menu";
		}else{
			model.addAttribute("skilabod", "already taken choose another username");
			return "register";
		}
		
	}
	
	
	@RequestMapping(value="/settings")
	public String settings( Model model, HttpSession session){
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String Message = "inn i settings";
		model.addAttribute("skilabod", Message);
		return "settings";
	}
	
	@RequestMapping(value="/highScores", method = RequestMethod.POST)
	public String highScores(@ModelAttribute("select") int value, Model model, HttpSession session) throws SQLException{
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String[] users = null;
		int[] scores = null;
		String data = null;
		
		if(value == 1){
			data = this.DBconnector.findHighScoreForALL();
		}else{
			String friendList = this.DBconnector.findFriendList((String)session.getAttribute("loggedInUser"));
			String[] list = this.util.parseFriendsList(friendList);
			data = this.DBconnector.findHighScoreForUser((String)session.getAttribute("loggedInUser"),list);
		}
		
		users = this.util.extractUsersFromData(data);
		scores = this.util.extractScoresFromData(data);
		
		for(int i = 0; i<users.length;i++){
			model.addAttribute("user"+(i+1), users[i]);
			model.addAttribute("score"+(i+1), scores[i]);
		}
		
		
		String Message = "inn i highScores";
		model.addAttribute("skilabod", Message);
		return "highScores";
	}
	
	@RequestMapping(value="/highScores", method = RequestMethod.GET)
	public String highScores( Model model, HttpSession session){
		
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		
		
		String Message = "inn i highScores";
		model.addAttribute("skilabod", Message);
		return "highScores";
	}
	
	
	@RequestMapping(value="/viewFriends", method = RequestMethod.GET)
	public String viewFriends( Model model, HttpSession session){
		
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String Message = "view friends";
		model.addAttribute("skilabod", Message);
		return "viewFriends";
	}
	
	@RequestMapping(value="/play")
	public String play( Model model, HttpSession session) throws SQLException{
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String UN = session.getAttribute("loggedInUser").toString();
		String gamestate;
		gamestate = this.DBconnector.getGameState(UN);


		model.addAttribute("user",UN);
		model.addAttribute("userData", gamestate);
		model.addAttribute("isFriend", false);
		return "play";
	}
	
	
}
