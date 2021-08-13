/**
 *  This is a Authentication-Gateway HomeController class, where the class has manage all pages.
 */
package com.authenticationgateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jeetendra
 * @version 1.0
 * @since 13/08/2021
 */

@Controller
@RequestMapping("/")
public class HomeController {

	/**
	 * @return login page
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	/**
	 * @return index page
	 */
	@RequestMapping(value = { "/index" })
	public String getHome() {
		return "index";
	}

	/**
	 * @return Authentication page
	 */
	@RequestMapping("/Authentication")
	public String authentication() {
		return "Authentication";
	}

	/**
	 * @return Electronic Know Your Customer (Ekyc) page
	 */
	@RequestMapping("/ekyc")
	public String ekyc() {
		return "Ekyc";
	}
}
