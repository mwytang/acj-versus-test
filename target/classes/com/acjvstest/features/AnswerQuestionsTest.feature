Feature: student answering a question

Scenario: student answers a question
	Given student is on the Home page and he is enrolled in a course
	When student clicks on the course name
	Then the course page opens
	When student clicks on the Answer button for the question they want to answer
	Then the Answer Page opens
	When student clicks on the Submit Answer button
	Then the answer form appears
	When student fills in the answer and clicks Submit
	Then the answer is submitted