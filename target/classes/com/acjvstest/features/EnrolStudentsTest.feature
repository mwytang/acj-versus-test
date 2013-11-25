Feature: Enroling an individual student

Scenario: Instructor enrols students 
	Given instructor is on the homepage with a course
	When instructor clicks on the Enrol Users
	Then enrolment page is shown
	When instructor types a student name in the Search Student text box
	Then the information about that student is shown
	When instructor clicks on status button  next to student's name
	Then the button turns green and student's status changes to enrolled