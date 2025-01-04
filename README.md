# Pencila Task

## Used tools and libraries:
```bash
- Java-17, maven, Junit5.
- major used libs > Selenium, Rest assured, allure reporting.
```
### Video for the implemented tests:
```bash
- https://drive.google.com/file/d/10Ahns9_QFkgOZCgyIi7TAjUleBPsriZZ/view?usp=sharing
```
## Design patterns used:
```bash
- Page object model design pattern

## How to run the project:
### to run all tests: 
```bash
- Run E2E tests > mvn clean -DargLine="-Xmx6g" -D"junit.jupiter.execution.parallel.enabled=true" -D"junit.jupiter.execution.parallel.config.strategy=dynamic" -Dtest="com/pencila/tests/regressionE2eTests/*/**" test
```
### Open allure report locally from cmd
```bash
Allure serve
```

## How to contribute:
### Before adding to the project
```bash
- Switch to main branch
- Pull latest test or main branch: git pull
- Take a copy of test or main branch: git checkout -b new-branch-name main
```
### After finishing follow the below steps:
```bash
- git add .
- git commit -m "Adding related message to your PR"
- git push origin branch-name
- From GitHub: Create PR by Comparing your branch to test or main branch
```
