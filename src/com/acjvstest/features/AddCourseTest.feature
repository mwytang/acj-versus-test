Feature: Course Creation

Scenario: Instructor creates a course
	Given instructor is on Home page
	When instructor clicks on the Create Course button
	Then course name text box is shown
	When instructor types the name of the course and click on Create button
	Then the course is created