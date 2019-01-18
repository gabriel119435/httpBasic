<h1>Http Basic</h1>
adding just this dependency:

    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
    <dependency>
makes Spring print this line at console every startup:

    Using generated security password: f29c056d-273f-4f6e-8a87-b4f70325604a
now [we know](https://docs.spring.io/spring-boot/docs/1.4.0.RELEASE/reference/htmlsingle/#boot-features-security) our password and user. [Here](https://www.base64encode.org/) we can paste

    user:f29c056d-273f-4f6e-8a87-b4f70325604a
returning
     
    dXNlcjpmMjljMDU2ZC0yNzNmLTRmNmUtOGE4Ny1iNGY3MDMyNTYwNGE=
now our REST call should have this header (taken from Postman, where you can use at `Authentication` tab, `Basic Auth` option, and you`ll get the same result):
    
    [{"key":"Authorization","value":"Basic dXNlcjpmMjljMDU2ZC0yNzNmLTRmNmUtOGE4Ny1iNGY3MDMyNTYwNGE=","type":"text"}]
and it works!

<h1>Http Basic Configured</h1>

now that we added some configuration at `SecurityConfig`, we have

| User/Password  | Role            |
| -------------  |:---------------:|
| user/pass      | STANDARD        |
| admin/admin    | STANDARD, ADMIN |

APIs with `/seriousInfo` requires user authentication with ADMIN role. To all other APIs, just authenticate with any user!