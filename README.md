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
		
## To test the application by Post Man

 * Use Post man and create token by Authorization Tab
 * use created token to header and access API


![](https://camo.githubusercontent.com/b46ab24a80417c1f7776f7b0c045a32a5093e489/68747470733a2f2f73636f6e74656e742e66626b6b32322d312e666e612e666263646e2e6e65742f762f74312e31353735322d392f37323834323832345f3432353635373632383333303938355f363938383535343531353236373937373231365f6e2e706e673f5f6e635f6361743d313030265f6e635f6f633d41516d496f396e456e4d68725a466a726a6d3470434a7577677951486971774a747a427776554e3278684636445377696834782d38356b7837454f6756446532435338265f6e635f68743d73636f6e74656e742e66626b6b32322d312e666e61266f683d6636623330386630356430663637356665613737633965653633663830613830266f653d3545354232444231)

![](https://camo.githubusercontent.com/ec5223029736bad78fac4a0bbeae1f24c046cb10/68747470733a2f2f73636f6e74656e742e66626b6b32322d312e666e612e666263646e2e6e65742f762f74312e31353735322d392f37323833303730335f3735323630373331353231303732365f343530383333383035303033393830383030305f6e2e706e673f5f6e635f6361743d313030265f6e635f6f633d41516e34452d5874716d734453465139306130306d6541426b7576443663464377675348756d3569313074484d5a6453372d53464e7659477a42305069716a4d6e7841265f6e635f68743d73636f6e74656e742e66626b6b32322d312e666e61266f683d3834353837363732663765373365663132653330343139326534366232366236266f653d3545314331353238)

![](https://camo.githubusercontent.com/ddc07ffac0dd30bd1f60b264b816a98a830ed8d3/68747470733a2f2f73636f6e74656e742e66626b6b32322d312e666e612e666263646e2e6e65742f762f74312e31353735322d392f37323438363233315f3434323038323436393736393339335f343832393131363336363936353434303531325f6e2e706e673f5f6e635f6361743d313034265f6e635f6f633d41516d4e39387a6747426571496d33584a35546d4c463334484b78784b6442616730336c4c6f34326250356f4c4673447163793775514c4d544d766835623746325a55265f6e635f68743d73636f6e74656e742e66626b6b32322d312e666e61266f683d3765353036353238393734326436663739313065656637306564306261323234266f653d3545323236343942)

![](https://camo.githubusercontent.com/43b9ace2e359182df07cc5c503c04036e90a97e2/68747470733a2f2f73636f6e74656e742e66626b6b32322d312e666e612e666263646e2e6e65742f762f74312e31353735322d392f37323737393434385f3732323535363235383236313933345f333431303836343737393338323735313233325f6e2e706e673f5f6e635f6361743d313031265f6e635f6f633d41516b5a4b6b415a6e5a7674656e68586d314561306437557341664262464e677366517a477a52353658516f5879387057696f50445a69784f6e645f556945755a7541265f6e635f68743d73636f6e74656e742e66626b6b32322d312e666e61266f683d6162316565653761623936316633313131333437363230336331356434623736266f653d3545323139344542)
