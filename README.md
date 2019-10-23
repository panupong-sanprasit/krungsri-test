# Krungsri test
## Spring Boot Application Provide User Service API and Securing with JSON Web Token (JWT) and Oauth2 + Grant Type Password

## Main building blocks
 * Spring Boot 1.5.3.RELEASE  
 * OAuth2 strategy & JSON Web Token
 * H2 Database Engine - used for rapid test and simulate data 

## To run the application
Use one of the several ways of running a Spring Boot application. Below are just three options:

1. Build using maven goal: `mvn clean package` and execute the resulting artifact as follows `java -jar krungsri-test-0.0.1-SNAPSHOT.jar` or
2. On Unix/Linux based systems: run `mvn clean package` then run the resulting jar as any other executable `./krungsri-test-0.0.1-SNAPSHOT.jar`
3. Build and start as a Docker container. Instructions at: [README](src/main/docker/README.md)


## To test the application

 ### First you will need the following basic pieces of information:

 * client: krungsri-client-id
 * secret: krungsri-secret
 * Standard-User username and password: krungsri.test and jwtpass
 * Admin username and password: admin.admin and jwtpass
 * Example of resource accessible to all authenticated users: 
 	http://localhost:8080/krungsri-test/v1/api/user/create
 	http://localhost:8080/krungsri-test/v1/api/user/{id}

 1. Generate an access token

   Use the following generic command to generate an access token:
   `$ curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=pwd`

   For this specific application, to generate an access token for the non-admin user john.doe, run:
   `$ curl krungsri-client-id:krungsri-client-secret@localhost:8080/oauth/token -d grant_type=password -d username=krungsri.test -d password=jwtpass`
    You'll receive a response similar to below

    `
    {
      "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4uYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNDk0NDU0MjgyLCJhdXRob3JpdGllcyI6WyJTVEFOREFSRF9VU0VSIiwiQURNSU5fVVNFUiJdLCJqdGkiOiIwYmQ4ZTQ1MC03ZjVjLTQ5ZjMtOTFmMC01Nzc1YjdiY2MwMGYiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQifQ.rvEAa4dIz8hT8uxzfjkEJKG982Ree5PdUW17KtFyeec",
      "token_type": "bearer",
      "expires_in": 43199,
      "scope": "read write",
      "jti": "0bd8e450-7f5c-49f3-91f0-5775b7bcc00f"
    }`

 2. Use the token to access resources through your RESTful API

    * Access content available to all authenticated users

        Use the generated token  as the value of the Bearer in the Authorization header as follows:
        `curl  http://localhost:8080/api/user/1 -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4uYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNDk0NDU0MjgyLCJhdXRob3JpdGllcyI6WyJTVEFOREFSRF9VU0VSIiwiQURNSU5fVVNFUiJdLCJqdGkiOiIwYmQ4ZTQ1MC03ZjVjLTQ5ZjMtOTFmMC01Nzc1YjdiY2MwMGYiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQifQ.rvEAa4dIz8hT8uxzfjkEJKG982Ree5PdUW17KtFyeec" `

        The response will be from your create with your path parameter {id}:
        `
		{
		    "id": 1,
		    "firstName": "Panupong",
		    "lastName": "Sanprasit",
		    "email": "sanprasit.panupong@gmail.com",
		    "phoneNumber": "+660875145175",
		    "memberType": "platinum",
		    "referenceCode": "201910235175",
		    "salary": 5000000,
		    "userName": "tum007",
		    "password": "tumtum2524",
		    "address": "82/84 Moo 13 Bangkeaw Bangplee Samutprakarn 10540"
		}
        `![](https://camo.githubusercontent.com/b46ab24a80417c1f7776f7b0c045a32a5093e489/68747470733a2f2f73636f6e74656e742e66626b6b32322d312e666e612e666263646e2e6e65742f762f74312e31353735322d392f37323834323832345f3432353635373632383333303938355f363938383535343531353236373937373231365f6e2e706e673f5f6e635f6361743d313030265f6e635f6f633d41516d496f396e456e4d68725a466a726a6d3470434a7577677951486971774a747a427776554e3278684636445377696834782d38356b7837454f6756446532435338265f6e635f68743d73636f6e74656e742e66626b6b32322d312e666e61266f683d6636623330386630356430663637356665613737633965653633663830613830266f653d3545354232444231)
