# SocialNetworkAPI

SocialNetworkAPI is an API designed for social networking. It allows to register users, add friends, posts, check the weather in the city where the user lives, see posts made by your friends, detect the language it's users are using and more!


## Getting Started

Software used for the project.

### Prerequisites

* [IntelliJ IDEA Community edition](https://www.jetbrains.com/idea/download/#section=windows)
* [Apache Tomcat®](https://tomcat.apache.org/download-90.cgi)
* [Git Bash](https://git-scm.com/downloads)

## End points

* GET: /friendInvites - get friend invites.
* POST: /friendInvites - send a friend invite.
* POST: /posts - add post conataining an image and text.
* GET: /friend - search for user by his fullname.
* DELETE: /friendInvites - reject friend invite.
* GET: /posts - see posts made by your friends.
* POST: /language-detect - detect language by submiting text.
* GET: /verifyMail - check is email is valid.
* POST /register{user} Metodas, iterpiantis nauja naudotoja i duomenu baze (duomenys JSON formatu)
* GET /friends{userId} Metodas skirtas gauti draugus pagal id reiksme (duomenys JSON formatu)
* POST /login/{credentials} Metodas skirtas prijungti vartotoja prie sistemos (duomenys JSON formatu)
* DELETE /friendInvites/{id} Metodas skirtas panaikinti draugo pakvietima (duomenys JSON formatu)
* GET /posts/{id} Metodas skirtas gauti draugo postus (duomenys JSON formatu)

## Built with

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Laurynas Zlatkus** - *Developer* - [LaZlat](https://github.com/LaZlat)
* **Rytis Razmus** - *Developer* - [RytisRazmus](https://github.com/RytisRazmus)
* **Evaldas Tamutis** - *Quality assurance specialist* - [Evaldas189](https://github.com/Evaldas189)
* **Jonas Zemaitis** - *Quality assurance specialist* - [Jonas](https://github.com/JonasPonas)
* **Evaldas Zalnierius** - *Code documentation* - [ezalnierius](https://github.com/ezalnierius)
