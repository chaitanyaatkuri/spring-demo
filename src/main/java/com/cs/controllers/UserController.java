package com.cs.controllers;

import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.handlers.BaseException;
import com.cs.models.Customers;
import com.cs.models.Engineers;
import com.cs.models.GenericResponse;
import com.cs.repository.CallQueryRepository;
import com.cs.repository.CustomerRepository;
import com.cs.repository.EngineerRepository;
import com.cs.types.Role;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {

	@Autowired
	private CallQueryRepository callRepository;
	@Autowired
	private EngineerRepository engineerRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(value = "/signup-engineer", method = RequestMethod.POST)
	public ResponseEntity<GenericResponse> saveInfo(@RequestParam String userName, @RequestParam String userPassword,
			@RequestParam Role userRole, @RequestParam String userFullName, @RequestParam long userContact,
			@RequestParam(value = "pushToken", required = false, defaultValue = "null") String pushToken) {

		GenericResponse response = new GenericResponse();

		try {
			if (doesUserExists(userName) == 0) {
				Engineers engineer = new Engineers(userName, userPassword, userRole, userFullName, userContact,
						pushToken);
				engineerRepository.save(engineer);

				response.setStatus(true);
				response.setMsg("User successfully created");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setStatus(false);
				response.setMsg("User already exsists");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new BaseException("User creation failed because of incorrect entries, Pls recheck.");
		}
	}

	/**
	 * This method check if the user exists or not.
	 * 
	 * @param tag
	 * @param info
	 * @return - The number of rows
	 */
	private int doesUserExists(String userName) {
		return engineerRepository.doesUserExists(userName);
	}

	@RequestMapping(value = "/login-engineer", method = RequestMethod.POST)
	public ResponseEntity<GenericResponse> loginEngineer(@RequestParam String userName,
			@RequestParam String userPassword, @RequestParam boolean isAndroid,
			@RequestParam(value = "pushToken", required = false, defaultValue = "null") String pushToken) {

		GenericResponse response = new GenericResponse();

		try {
			if (engineerRepository.loginUser(userName, userPassword) == 0) {
				response.setStatus(false);
				response.setMsg("Please check your credentials");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				// User is validated, now if android save the pushToken.
				if (!isAndroid || pushToken == null || pushToken.equals("null")) {
					response.setStatus(true);
					response.setMsg("You are now successfully logged in!");
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					// Get previous pushTokens and append the current.
					String prevPushTokens = engineerRepository.getPushToken(userName);
					if (!prevPushTokens.equals(pushToken)) { // Update only
																// if
																// pushToken
																// is
																// different
						if (prevPushTokens != null && !prevPushTokens.equals("null")) {
							prevPushTokens = prevPushTokens + "," + pushToken;
						} else {
							prevPushTokens = pushToken;
						}
						engineerRepository.addPushToken(userName, prevPushTokens);
					}
					response.setStatus(true);
					response.setMsg("You are now successfully logged in!");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			throw new BaseException("User login failed because of incorrect entries, Pls recheck.");
		}
	}

	@RequestMapping(value = "/login-user", method = RequestMethod.POST)
	public ResponseEntity<GenericResponse> loginUser(@RequestParam long userContact, @RequestParam boolean isAndroid,
			@RequestParam(value = "pushToken", required = false, defaultValue = "null") String pushToken) {

		// Here we have two conditions, where the user can be an existing user
		// or a new user.

		GenericResponse response = new GenericResponse();

		try {

			if (customerRepository.doesUserExists(userContact) == 0) {
				// Now, the user is a new user, so ask user for his details.
				response.setStatus(false);
				response.setMsg("Success! Please provide following details to continue..");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				// User is validated, now if android save the pushToken.
				if (!isAndroid || pushToken == null || pushToken.equals("null")) {
					response.setStatus(true);
					response.setMsg("Success! Please enter the OTP sent to your registered mobile number");
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					// Get previous pushTokens and append the current.
					String prevPushTokens = customerRepository.getPushToken(userContact);
					if (!prevPushTokens.equals(pushToken)) {
						// Update only if pushToken is different
						if (prevPushTokens != null && !prevPushTokens.equals("null")) {
							prevPushTokens = prevPushTokens + "," + pushToken;
						} else {
							prevPushTokens = pushToken;
						}
						customerRepository.addPushToken(userContact, prevPushTokens);
					}
					response.setStatus(true);
					response.setMsg("Success! Please enter the OTP sent to your registered mobile number");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}
			}
		} catch (Exception e) {
			throw new BaseException("User login failed because of incorrect entries, Pls recheck.");
		}
	}

	@RequestMapping(value = "/login-user-details", method = RequestMethod.POST)
	public ResponseEntity<GenericResponse> loginUserDetails(@RequestParam String userName,
			@RequestParam long userContact, @RequestParam boolean isAndroid, @RequestParam String pushToken,
			@RequestParam String userEmail) {

		GenericResponse response = new GenericResponse();

		try {
			// Get previous pushTokens and append the current.
			String prevPushTokens = null;
			try {
				prevPushTokens = customerRepository.getPushToken(userContact);
			} catch (Exception e) {
				throw new BaseException("" + e);
			}
			try {
				if (prevPushTokens != null && !prevPushTokens.equals("null")) {
					if (!prevPushTokens.equals(pushToken)) {
						prevPushTokens = prevPushTokens + "," + pushToken;
					}
				} else {
					prevPushTokens = pushToken;
				}
			} catch (Exception e) {
				throw new BaseException("" + e);
			}
			try {
				// Now save the customer information to DB.
				Customers customer = new Customers(userName, userContact, userEmail, false, prevPushTokens);
				customerRepository.save(customer);
			} catch (Exception e) {
				throw new BaseException("" + e);
			}
			response.setStatus(true);
			response.setMsg("Success! Please enter the OTP sent to your registered mobile number");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new BaseException("User login failed because of incorrect entries, Pls recheck.");
		}
	}

	/**
	 * Saves the information entered by the user after validating its entry in
	 * the DB
	 * 
	 * @param tag
	 *            - The Tag
	 * @param information
	 *            - Info related to Tag
	 * @return - Success/Failure
	 */
	/*
	 * @RequestMapping(value = "/infosave", method = RequestMethod.GET)
	 * public @ResponseBody String saveInfo(@RequestParam("tag") String
	 * tag, @RequestParam("info") String information) { if (doesInfoExists(tag,
	 * information) == 0) { Info info = new Info(tag, information);
	 * info.setId(infoRepository.count() + 1); infoRepository.save(info); return
	 * "Success"; } else { return "Information already exsists"; } }
	 * 
	 *//**
		 * This method check if the key value pairs of tag and info exists or
		 * not.
		 * 
		 * @param tag
		 * @param info
		 * @return - The number of rows
		 */
	/*
	 * private int doesInfoExists(String tag, String info) { return
	 * infoRepository.doesInfoExists(tag, info); }
	 * 
	 *//**
		 * Returns the list of all Info entered by the user
		 * 
		 * @return - List of Information object
		 */
	/*
	 * @CrossOrigin(origins = "http://localhost:8080")
	 * 
	 * @RequestMapping(value = "/get-list", method = RequestMethod.GET)
	 * public @ResponseBody List<Info> info() { return
	 * Lists.newArrayList(infoRepository.findAll()); }
	 * 
	 *//**
		 * Returns the list of info matching the specific tag.
		 * 
		 * @param tag
		 *            - The query tag
		 * @return - List of Information object
		 */
	/*
	 * @RequestMapping(value = "/get-info-for-tag", method = RequestMethod.GET)
	 * public @ResponseBody List<Info> fetchInfoForTags(@RequestParam("tag")
	 * String tag) { return infoRepository.findByTag(tag); }
	 * 
	 *//**
		 * Returns all the Tags in the DB
		 * 
		 * @return - List of all tags.
		 *//*
		 * @RequestMapping(value = "/get-tags", method = RequestMethod.GET)
		 * public @ResponseBody List<String> fetchTags() { return
		 * infoRepository.fetchTags(); }
		 */
}
