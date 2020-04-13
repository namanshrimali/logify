# Logify
> Logify is an Attendance Management System for a team, that helps stake holders in a team to log and maintain their work records while working both in office or remote

## Features
> Application supports jwt role based authorization, categorizing the users in 'Admin' and 'Users'
> Application have a Admin account which can be managed by managers.
> Admin can assign and revoke privilages of the users
> Admin can remove users from the application
> Admin can view logs of all the users on a given selected date
> Users can sign up in the application as a non-privilaged user with a unique username.
> Users can log their daily sign-in and sign-out time
> Admin and Users can view User's log history

## Installation
> Front end is made in Angular 9
> Back-end is made in Spring Boot
> Both back-end and the front-end will be deployed as a springboot application. So you need to install java, angular cli, npm (needs node js) and maven on your system in order to get it working

OS X & Linux:
```sh
sudo apt update
sudo apt install maven
sudo apt install nodejs
npm install npm@latest -g

```

Windows:
You can download and install the same from 
* https://maven.apache.org/download.cgi
* https://nodejs.org/en/download/
* ```sh
npm install npm@latest -g
```

## Release History
* 0.1.0
    * The first proper release
    * Added support for jwt role based authorization and spring security.
* 0.0.1
    * Work in progress

## Upcoming Features
* Admins can clock their working time too
* Generate an excel based report on request

## Meta

Naman Shrimali – [@YourTwitter](https://twitter.com/dbader_org) – namanshrimali@gmail.com
[https://github.com/namanshrimali/logify](https://github.com/namanshrimali/)

## Contributing

1. Fork it (<https://github.com/yourname/yourproject/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
