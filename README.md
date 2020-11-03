# Instructions
----
First, an quickly overview of app, if a simple api searching for TV shows, the main objetive is a little form with a search box, when the button search is pressed redirect to another page with result.

**Important:** You need to create a fork of this project to solve the exercise
### Run app

`npm i`

`npm run dev`

Access to http://localhost:3000/shows

## Steps to-do in test
1- Configure Jest in application and make unit test for:

* Search box is empty and button search is pressed
* Search without query not work well
* Search results show a list of items
* Validate that image is not showed when image values is null

2- Configure Selenium or Cypress and make the following workflow:

* Open browser in http://localhost:3000/shows
* Enter a text in search box with text **batman**
* Press button search
* Navigate to the url that is show in second card of results
* Navigate back using browser features
* Change css background color to **#4a148c** to card with title **Batman Unlimited**
* Press back button
* Make sure that input for search is empty
