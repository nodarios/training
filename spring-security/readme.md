### spring-security

* implemented spring security
* added different users and different pages
* added brute force protector

---

### web structure

`/login` login page.  
`/index` has public access.  
`/user` secured with role "USER".  
`/admin` secured with role "ADMIN".

---

###  credentials

user/user, has role "USER".  
admin/admin, has role "USER" and "ADMIN".

---

### start service 

`./gradlew bootRun`

---

###  scenarios

check index page has public access:
* [index page](http://localhost:8080/index)

check blocked user list:
* [login page](http://localhost:8080/login)
* try to authenticate with bad credentials 3 times, e.g. sa/sa
* then authenticate as `admin`
* go to admin page to see list of blocked users
* [admin page](http://localhost:8080/admin)
* after 1 minute refresh admin page, blocked list should be empty

check `user` should hava access to user page, but no access to admin page:
* [login page](http://localhost:8080/login)
* authenticate as `user`
* go to user page to see it is accessible
* [user page](http://localhost:8080/user)
* go to admin page to see it is forbidden
* [admin page](http://localhost:8080/admin)
