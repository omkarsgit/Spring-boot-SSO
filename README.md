# Spring-boot-SSO
The purpose of this application is to demonstrate Github integration with OpenID Connect to create a single Sign-On applcaition. The application will require a github account to access it.
I am using OAuth2 to authenticate the user to the website using Github account and display the users information. 
Spring boot is the main framework used to build this web application. The application is using few dependencies like **spring-boot-starter-oauth2-client** , **spring-cloud-starter-security** . 

The index page consists of a login button. This will redirect to a Gitub login page where the user will be authenticated using Github account credentials. This will allow the web app to acess the token from Github to view user resource. This will create a session id in the browser. If a user open a new tab, they do not have to login again. 

Web Application --> User Authorization request --> Github Authorization Server --> Access Token --> Web Application 

When a user is logged in, the page is refreshed to display user information. Principal in Spring boot is used to get login information through a security context. I am using @GetMapping to access this information and use Map to create a new object with key and value pairs. This data is accessed using JQuery and displayed using HTML page.  
Error handling is implemented using HttpSecurity. The errors are displayed on a seperate web page. 

The project is deployed to Heroku cloud platform. It can be accesed using this link. https://spring-boot-github.herokuapp.com/
 
