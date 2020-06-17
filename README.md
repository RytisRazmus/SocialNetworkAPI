# SocialNetworkAPI

SocialNetworkAPI is an API designed for social networking. It allows to register users, add friends, posts, check the weather in the city where the user lives, see posts made by your friends, detect the language it's users are using and more!


## Getting Started

Software used for the project.

### Prerequisites

* [IntelliJ IDEA Community edition](https://www.jetbrains.com/idea/download/#section=windows)
* [Apache Tomcat®](https://tomcat.apache.org/download-90.cgi)
* [Git Bash](https://git-scm.com/downloads)

## End points

* GET: /friendInvites/{id} - get friend invites by user id.
* POST: /friendInvites - send a friend invite (Takes a Json object).
* POST: /posts - add post containing an image and text (Takes a Json object).
* GET: /friend/{fullname}- search for user by his full-name.
* DELETE: /friendInvites/{id} - reject friend invite.
* GET: /posts/{id} - see posts made by your friends.
* POST: /language-detect - detect language by submiting text (Takes a Json object).
* GET: /weather/{id} - returns weather in users living city.
* GET: /verifyMail/{email} - check is email is valid.
* POST: /register{user} add user to database (Takes a Json object).
* GET:/love/{id}/{loveId} - return percentage of love between users.
* GET: /friends{userId} get users friends.
* POST: /login/{credentials} - login to the system (Takes a Json object).



## Built with

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Laurynas Zlatkus** - *Developer* - [LaZlat](https://github.com/LaZlat)
* **Rytis Razmus** - *Developer* - [RytisRazmus](https://github.com/RytisRazmus)
* **Evaldas Tamutis** - *Quality assurance specialist* - [Evaldas189](https://github.com/Evaldas189)
* **Jonas Zemaitis** - *Quality assurance specialist* - [Jonas](https://github.com/JonasPonas)
* **Evaldas Zalnierius** - *Code documentation* - [ezalnierius](https://github.com/ezalnierius)
