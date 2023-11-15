package com.baitcheckerios.pages;

public interface LoginElement {

	void login(String email, String password) throws Exception;
	
	void createCustomer() throws Exception;
	
	void logout() throws Exception;

}
