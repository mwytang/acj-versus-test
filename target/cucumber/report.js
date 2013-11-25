$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("AddCourseTest.feature");
formatter.feature({
  "id": "course-creation",
  "description": "",
  "name": "Course Creation",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "course-creation;instructor-creates-a-course",
  "description": "",
  "name": "Instructor creates a course",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "instructor is on Home page",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "instructor clicks on the Create Course button",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "course name text box is shown",
  "keyword": "Then ",
  "line": 6
});
formatter.step({
  "name": "instructor types the name of the course and click on Create button",
  "keyword": "When ",
  "line": 7
});
formatter.step({
  "name": "the course is created",
  "keyword": "Then ",
  "line": 8
});
formatter.match({
  "location": "AddCourseTest.instructor_is_on_Home_page()"
});
formatter.result({
  "duration": 10489302000,
  "status": "passed"
});
formatter.match({
  "location": "AddCourseTest.instructor_clicks_on_the_Create_Course_button()"
});
formatter.result({
  "duration": 81003000,
  "status": "passed"
});
formatter.match({
  "location": "AddCourseTest.course_name_text_box_is_shown()"
});
formatter.result({
  "duration": 69660000,
  "status": "passed"
});
formatter.match({
  "location": "AddCourseTest.instructor_types_the_name_of_the_course_and_click_on_Create_button()"
});
formatter.result({
  "duration": 161698000,
  "status": "passed"
});
formatter.match({
  "location": "AddCourseTest.the_course_is_created()"
});
formatter.result({
  "duration": 805270000,
  "status": "passed"
});
formatter.uri("AnswerQuestionsTest.feature");
formatter.feature({
  "id": "student-answering-a-question",
  "description": "",
  "name": "student answering a question",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "student-answering-a-question;student-answers-a-question",
  "description": "",
  "name": "student answers a question",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "student is on the Home page and he is enrolled in a course",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "student clicks on the course name",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "the course page opens",
  "keyword": "Then ",
  "line": 6
});
formatter.step({
  "name": "student clicks on the Answer button for the question they want to answer",
  "keyword": "When ",
  "line": 7
});
formatter.step({
  "name": "the Answer Page opens",
  "keyword": "Then ",
  "line": 8
});
formatter.step({
  "name": "student clicks on the Submit Answer button",
  "keyword": "When ",
  "line": 9
});
formatter.step({
  "name": "the answer form appears",
  "keyword": "Then ",
  "line": 10
});
formatter.step({
  "name": "student fills in the answer and clicks Submit",
  "keyword": "When ",
  "line": 11
});
formatter.step({
  "name": "the answer is submitted",
  "keyword": "Then ",
  "line": 12
});
formatter.match({
  "location": "AddAnswerTest.student_is_on_the_Home_page_and_he_is_enrolled_in_a_course()"
});
formatter.result({
  "duration": 3976204000,
  "status": "passed"
});
formatter.match({
  "location": "AddAnswerTest.student_clicks_on_the_course_name()"
});
formatter.result({
  "duration": 486159000,
  "status": "passed"
});
formatter.match({
  "location": "AddAnswerTest.the_course_page_opens()"
});
formatter.result({
  "duration": 24621000,
  "status": "passed"
});
formatter.match({
  "location": "AddAnswerTest.student_clicks_on_the_Answer_button_for_the_question_they_want_to_answer()"
});
formatter.result({
  "duration": 231704000,
  "status": "passed"
});
formatter.match({
  "location": "AddAnswerTest.the_Answer_Page_opens()"
});
formatter.result({
  "duration": 289057000,
  "status": "passed"
});
formatter.match({
  "location": "AddAnswerTest.student_clicks_on_the_Submit_Answer_button()"
});
formatter.result({
  "duration": 80710000,
  "status": "passed"
});
formatter.match({
  "location": "AddAnswerTest.the_answer_form_appears()"
});
formatter.result({
  "duration": 7383000,
  "status": "passed"
});
formatter.match({
  "location": "AddAnswerTest.student_fills_in_the_answer_and_clicks_Submit()"
});
formatter.result({
  "duration": 273225000,
  "status": "passed"
});
formatter.match({
  "location": "AddAnswerTest.the_answer_is_submitted()"
});
formatter.result({
  "duration": 963817000,
  "status": "passed"
});
formatter.uri("EnrolStudentsTest.feature");
formatter.feature({
  "id": "enroling-an-individual-student",
  "description": "",
  "name": "Enroling an individual student",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "enroling-an-individual-student;instructor-enrols-students",
  "description": "",
  "name": "Instructor enrols students",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "instructor is on the homepage with a course",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "instructor clicks on the Enrol Users",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "enrolment page is shown",
  "keyword": "Then ",
  "line": 6
});
formatter.step({
  "name": "instructor types a student name in the Search Student text box",
  "keyword": "When ",
  "line": 7
});
formatter.step({
  "name": "the information about that student is shown",
  "keyword": "Then ",
  "line": 8
});
formatter.step({
  "name": "instructor clicks on status button  next to student\u0027s name",
  "keyword": "When ",
  "line": 9
});
formatter.step({
  "name": "the button turns green and student\u0027s status changes to enrolled",
  "keyword": "Then ",
  "line": 10
});
formatter.match({
  "location": "EnrolTest.instructor_is_on_the_homepage_with_a_course()"
});
formatter.result({
  "duration": 4389069000,
  "status": "passed"
});
formatter.match({
  "location": "EnrolTest.instructor_clicks_on_the_Enrol_Users()"
});
formatter.result({
  "duration": 169260000,
  "status": "passed"
});
formatter.match({
  "location": "EnrolTest.enrolment_page_is_shown()"
});
formatter.result({
  "duration": 180623000,
  "status": "passed"
});
formatter.match({
  "location": "EnrolTest.instructor_types_a_student_name_in_the_Search_Student_text_box()"
});
formatter.result({
  "duration": 496101000,
  "status": "passed"
});
formatter.match({
  "location": "EnrolTest.the_information_about_that_student_is_shown()"
});
formatter.result({
  "duration": 23622000,
  "status": "passed"
});
formatter.match({
  "location": "EnrolTest.instructor_clicks_on_status_button_next_to_student_s_name()"
});
formatter.result({
  "duration": 68410000,
  "status": "passed"
});
formatter.match({
  "location": "EnrolTest.the_button_turns_green_and_student_s_status_changes_to_enrolled()"
});
formatter.result({
  "duration": 366679000,
  "status": "passed"
});
formatter.uri("PostQuestionsTest.feature");
formatter.feature({
  "id": "instructor-creating-a-question",
  "description": "",
  "name": "instructor creating a question",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "instructor-creating-a-question;instructor-posts-a-question",
  "description": "",
  "name": "Instructor posts a question",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "instructor is on the Homepage and he has created a course",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "instructor clicks on course name",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "the Course Page opens",
  "keyword": "Then ",
  "line": 6
});
formatter.step({
  "name": "instructor clicks on the Create Question button",
  "keyword": "When ",
  "line": 7
});
formatter.step({
  "name": "the edit text boxes for creating a question is shown",
  "keyword": "Then ",
  "line": 8
});
formatter.step({
  "name": "instructor fills in the question form and clicks on the submit button",
  "keyword": "When ",
  "line": 9
});
formatter.step({
  "name": "question is created",
  "keyword": "Then ",
  "line": 10
});
formatter.match({
  "location": "AddQuestionTest.instructor_is_on_the_Homepage_and_he_has_created_a_course()"
});
formatter.result({
  "duration": 4704924000,
  "status": "passed"
});
formatter.match({
  "location": "AddQuestionTest.instructor_clicks_on_course_name()"
});
formatter.result({
  "duration": 1037058000,
  "status": "passed"
});
formatter.match({
  "location": "AddQuestionTest.the_Course_Page_opens()"
});
formatter.result({
  "duration": 25297000,
  "status": "passed"
});
formatter.match({
  "location": "AddQuestionTest.instructor_clicks_on_the_Create_Question_button()"
});
formatter.result({
  "duration": 63836000,
  "status": "passed"
});
formatter.match({
  "location": "AddQuestionTest.the_edit_text_boxes_for_creating_a_question_is_shown()"
});
formatter.result({
  "duration": 6528000,
  "status": "passed"
});
formatter.match({
  "location": "AddQuestionTest.instructor_fills_in_the_question_form_and_clicks_on_the_submit_button()"
});
formatter.result({
  "duration": 521253000,
  "status": "passed"
});
formatter.match({
  "location": "AddQuestionTest.question_is_created()"
});
formatter.result({
  "duration": 951146000,
  "status": "passed"
});
});