package project.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

		return "/login";
		
	}
	
	/*@RequestMapping(value = "/resturl", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @Transactional(value = "jpaTransactionManager")
    public @ResponseBody List<DomainObject> findByResourceID(@PathParam("resourceID") String resourceID) {*/
	
	@RequestMapping(value="/")
	public String redirectToLogin(){
		//System.out.print("1001");
		return "redirect:/login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String displayLogInPage(Model model, HttpSession session) throws SQLException{
		this.DBconnector = new DBconnector();
		/*ModelAndView jsp = new ModelAndView("login1");
		System.out.print("hallo");*/
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
		
		String gamestate = this.util.getGameState( this.DBconnector, session );
		model.addAttribute("gamestate", gamestate);

		session.getAttribute("loggedInUser");
		model.addAttribute("skilabod", session.getAttribute("loggedInUser")+ " er skradur inn");
		
		return "menu";
	}
	
	@RequestMapping(value = "/addFriend", method = RequestMethod.GET)
	public String addFirend(Model model, HttpSession session){
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String gamestate = this.util.getGameState( this.DBconnector, session );
		model.addAttribute("gamestate", gamestate);
		
		return "addFriend";
	}
	
	@RequestMapping(value = "/addFriend", method = RequestMethod.POST)
	public String addFirends(@ModelAttribute("UserName") String user, Model model, HttpSession session)throws SQLException{
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String gamestate = this.util.getGameState( this.DBconnector, session );
		model.addAttribute("gamestate", gamestate);
		
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
		
		String gamestate = this.util.getGameState( this.DBconnector, session );
		model.addAttribute("gamestate", gamestate);
		
		if(this.DBconnector.registrationSuccess(notandi.getUserName(), notandi.getPW())){
			this.DBconnector.createNewUser(notandi.getUserName(), notandi.getPW());
			
			session.setAttribute("loggedInUser", notandi.getUserName());
			return "redirect:/menu";
		}else{
			model.addAttribute("skilabod", "already taken choose another username");
			return "register";
		}
		
	}
	
	
	@RequestMapping(value="/settings", method = RequestMethod.GET)
	public String settings( Model model, HttpSession session) throws SQLException{
		
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		int settings = this.DBconnector.getSettings((String) session.getAttribute("loggedInUser"));

		String Message = "inn i settings";
		model.addAttribute("value", settings);
		model.addAttribute("skilabod", Message);
		return "settings";
	}
	
	@RequestMapping(value="/settings", method = RequestMethod.POST)
	public String settingspost(@ModelAttribute("action") String value,@ModelAttribute("audio-slider") int volume,  Model model, HttpSession session) throws SQLException{
		
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		this.DBconnector.setSettings(session.getAttribute("loggedInUser").toString(), volume);
		
		if(value.equals("save")){
			return "redirect:/menu";
		}else if(value.equals("default")){
			this.DBconnector.setSettings(session.getAttribute("loggedInUser").toString(), 20);
			return "redirect:/settings";
		}
		return "settings";

	}
	
	
	@RequestMapping(value="/highScores", method = RequestMethod.POST)
	public String highScores(@ModelAttribute("select") int value, Model model, HttpSession session) throws SQLException{
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String gamestate = this.util.getGameState( this.DBconnector, session );
		model.addAttribute("gamestate", gamestate);
		
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
		LinkedHashMap<String, Integer> userScores = new LinkedHashMap<String, Integer>();
		
		for(int i = 0; i<users.length;i++){
			userScores.put(users[i],scores[i]);
			System.out.println("user = " + users[i]+" score = "+scores[i]);
		}
		
		
		
		model.addAttribute("data", userScores);
		String Message = "inn i highScores";
		model.addAttribute("skilabod", Message);
		return "highScores";
	}
	
	@RequestMapping(value="/highScores", method = RequestMethod.GET)
	public String highScores( Model model, HttpSession session){
		
		
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String gamestate = this.util.getGameState( this.DBconnector, session );
		model.addAttribute("gamestate", gamestate);
		
		String Message = "inn i highScores";
		model.addAttribute("skilabod", Message);
		return "highScores";
	}
	
	
	@RequestMapping(value="/viewFriends", method = RequestMethod.GET)
	public String viewFriends( Model model, HttpSession session) throws SQLException{
		
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String gamestate = this.util.getGameState( this.DBconnector, session );
		model.addAttribute("gamestate", gamestate);
		
		String friendList = this.DBconnector.findFriendList((String)session.getAttribute("loggedInUser"));
		String[] list = this.util.parseFriendsList(friendList);
		
		model.addAttribute("users", list);
		for(int i = 0; i<list.length;i++){
			model.addAttribute("user"+(i+1), list[i]);
			System.out.println(list[i]);
		}
		
		return "viewFriends";
	}
	
	/*@RequestMapping(value="/viewFriends", method = RequestMethod.POST)
	public String chooseFriend(@ModelAttribute("who") String chosen, Model model, HttpSession session) throws SQLException{
		
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String gamestate = this.DBconnector.getGameState(chosen);
		model.addAttribute("user",chosen);
		model.addAttribute("userData", gamestate);
		model.addAttribute("isFriend", true);
		
		return "play";
	}
	*/

	
	
	
	@RequestMapping(value="/refresh", method = RequestMethod.POST)
	public String goggafaggi(
				@ModelAttribute("submitString3") String submitString, 
				@ModelAttribute("score3") String score, Model model,
				HttpSession session) throws SQLException{

		System.out.println("halo er ekki thad godur leikur");
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String UN = session.getAttribute("loggedInUser").toString();
		    
		this.DBconnector.setGameState(UN, submitString, score);
		
		return "redirect:/play";
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
	
	@RequestMapping(value="/exit", method = RequestMethod.POST)
	public String exitGame(@ModelAttribute("submitString") String submitString, @ModelAttribute("score") String score, @ModelAttribute("checkFriend") boolean checkFriend, Model model, HttpSession session) throws SQLException{
		
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		
		String UN = session.getAttribute("loggedInUser").toString();
		if (checkFriend) {
		    
		    this.DBconnector.setGameState(UN, submitString, score);
		    	return "redirect:/menu";
		  } else {
			  return "redirect:/viewFriends";
		  }
		}
	}
