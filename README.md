# Logify
> Logify is an Employee Management System for a team, that helps stake holders in a team to log and maintain their work records with ease

## Features
* Application supports jwt role based authorization, categorizing the users in 'Admin' and 'Users'
* Application have a Admin account which can be managed by managers.
* Admin can assign and revoke privilages of the users
* Admin can remove users from the application
* Admin can view logs of all the users on a given selected date
*  Users can sign up in the application as a non-privilaged user with a unique username.
* Users can log their daily sign-in and sign-out time
* Admin and Users can view User's log history

## Installation
* Front end is made in Angular 9
* Back-end is made in Spring Boot
* Both back-end and the front-end will be deployed as a springboot application. So you need to install java, angular cli, npm (needs node js) and maven on your system in order to get it working

OS X & Linux:
```sh
sudo apt update
sudo apt install maven
sudo apt install nodejs
npm install npm@latest -g

```

Windows:
You can download and install all the required softwares from 
* https://maven.apache.org/download.cgi
* https://nodejs.org/en/download/
* https://www.npmjs.com/get-npm
* https://www.java.com/en/download/


## Deployment setup
Before deploying the application over to your host, make sure you update your host address (hostname and the port where backend is deployed), eg: http(s)://hostname:portname in the angular services.
After the changes are made, go to the parent directory of the application and execute, so that all all the code is built and packaged.

```sh
mvn clean build
```
If you're building both the front end and the back-end manually,
* For front-end (Angular)
```sh
ng build --prod
```
* For back-end (Spring Boot)
```sh
mvn clean build
```

    
## Upcoming Features
* Admins can clock their working time too
* Generate an excel based report on request

## Release History
* 0.1.0
    * The first proper release
    * Added support for jwt role based authorization and spring security.
* 0.0.1
    * Work in progress


## Meta
You can reach out to me or see other projects I've been working on 
Naman Shrimali – [Twitter](https://twitter.com/namanshrimali) – [Gmail](namanshrimali@gmail.com)-[Github](https://github.com/namanshrimali/)

## Contributing

1. Fork it (<https://github.com/namanshrimali/logify/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
