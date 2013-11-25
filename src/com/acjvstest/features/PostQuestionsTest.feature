Feature: instructor creating a question

Scenario: Instructor posts a question 
	Given instructor is on the Homepage and he has created a course
	When instructor clicks on course name
	Then the Course Page opens
	When instructor clicks on the Create Question button
	Then the edit text boxes for creating a question is shown
	When instructor fills in the question form and clicks on the submit button
	Then question is created