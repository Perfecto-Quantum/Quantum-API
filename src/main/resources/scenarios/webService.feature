@WebService 
Feature: Demo for Web Service example 



	@getPerfectoUsers
Scenario Outline: Add new order 
	Given user request with json data '<get.users.list>' 
	And I check response code
	Then response should have status 'OK' 
	Then response should have value contains "kulins@perfectomobile.com" at "$..username" 
	
	
	Examples: {'datafile' : 'src/main/resources/data/sampleServicesData.json'}
	

	
	
