# Training

Upskilling on Kotlin language features. Professional development.

## Kotlin REPL (also known as event loop or interactive console)
```
1.  Open Console in Intellij with:
        Tools > Kotlin > Kotlin REPL
    Run code in the console:
        val power = 10.0
        Math.pow(2.0, power)
       
2.  REPL is aware of your project and can reference classes.
```

## Multi-module Project Initialisation
```
1.  ALWAYS choose a Kotlin version that matches the Kotlin version that comes pre-packaged with Intellij:
        * do NOT want hassle of messing about with Intellij Kotlin versions
        * WILL wait for the Intellij Kotlin version upgrade cycle to determine our 
          Kotlin version upgrade cycle (as we don't want any IDE hassle and want a stable well tested version)
    as at 2024 latest Kotlin version packaged with Intellij is version 1.9 and compatibility matrices:
        https://stackoverflow.com/questions/63989767/which-versions-of-kotlin-are-compatible-with-which-versions-of-java
        https://docs.gradle.org/current/userguide/compatibility.html#kotlin
    indicate should install:
        kotlin 1.9          -> requires at least gradle 8.10
        java   21.0.4-zulu  -> version 21 is max version compatible with Kotlin 1.9
        gradle 8.12         -> is above the min version required by Kotlin 1.9
          
    
2.  Install tooling to init the project (easier than using IDE for this step):
        https://sdkman.io/
    Install latest gradle version and choose as default (as its init cmd worked)
        sdk list gradle
        sdk install gradle 8.12
    Install kotlin
        sdk list kotlin
        sdk install kotlin 1.9.0
        
    
3.  Initialise a new project:
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
        run the tests from the command line as part of build e.g 
            gradle clean build

```