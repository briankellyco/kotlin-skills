# sandbox

Sandbox to play with Kotlin

https://www.udemy.com/course/kotlin-for-java-developers/

## Multi-module Project Initialisation
```
1.  Install sdkman to manage tooling
        https://sdkman.io/
    Install latest gradle version and choose as default (as its init cmd worked)
        sdk list gradle
        sdk install gradle 8.5 
    Install kotlin
        sdk list kotlin
        sdk install kotlin 1.9.21
2.  Initialise a new project:
        https://docs.gradle.org/current/samples/sample_building_java_applications_multi_project.html
    where:
        mkdir kotlin-skills
        cd kotlin-skills
        gradle init         # follow the prompts and choose to create "(2) application".
    and:
        import that project into Intellij.
    and:
        refresh the gradle dependencies
    finally:
        run the tests from Intellij to confirm the build is working.
        run the tests from the command line as part of build e.g gradle clean build

3.  Intellij project should have a JDK21 setup to run the examples.

```

## Kotlin REPL (also known as event loop or interactive console)
```
1.  Open Console in Intellij with:
        Tools > Kotlin > Kotlin REPL
    Run code in the console:
        val power = 10.0
        Math.pow(2.0, power)
       
2.  REPL is aware of your project and can reference classes.
```