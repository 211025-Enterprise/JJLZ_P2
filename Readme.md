stock paper trading application.

Jonathan Sammons , John Meo, Zeyad Nid , Levan Siradze


High-Level Requirements
Application must leverage the full stack:

#RDBMS for persistence
- [x] API built with Java 8 and Spring 5
- [x] First iteration of the UI is due on December 10th and will be built with HTML, CSS and JS.
- [x] Second and final iteration of the UI is due with the presentation on December 17 and will be built with React.


#Technology framework requirements:

- [x] Java API will use Hibernate to communicate with a PostGreSQL RDBMS
- [x] Java API will leverage the Spring Framework
- [x] Java API will be RESTful (no HttpSession, use JWTs!)
- [ ] Complete CI/CD pipelines will use AWS (CodePipeline, CodeBuild, Elastic Beanstalk, and S3)


#Other requirements:

- [x] Application will demonstrate at least ten individual user stories
- [x] Application will leverage at least one external API
- [x] Application's own data model must be sufficiently complex (i.e. >2 tables)
- [x] RDBMS will be deployed to the cloud (AWS RDS)
- [ ] Java API will be deployed to the cloud (AWS EC2)
- [ ] UI application will be deployed to the cloud (AWS S3)
- [ ] Java API will have >=80% test coverage for service layer
- [ ] Java API will leverage Spring's MockMvc for integration/e2e tests of controller endpoints

#Suggested bonus goals:

- [ ] Deploy API using ECS w/ Docker (instead of Elastic Beanstalk)
- [ ] Secure your Java API using Spring Security
- [ ] Java API will be adequately documented (Java Docs and web endpoint documentation [Swagger/OpenAPI])


Getting Started

git clone https://github.com/211025-Enterprise/JJLZ_P2.git

git clone https://github.com/211025-Enterprise/JJLZ_P2.git

open webapp with VScode

run command "npm start" in terminal

open localhost:3000 in browser, then you'll have choices to register or log in

After logging in, you can search for stocks and buy/sell them. look at the charts to see price increase or drops. and view your total balance including the stocks you hold
