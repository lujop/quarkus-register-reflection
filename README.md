# quarkus-register-reflection
Extension to easily register for reflection multiple classes from external dependencies

## Usage
- Register external dependencies by their group and artifact, that make sure that their content is indexed.
- Register class patterns using regular expressions, classes that match will be registered for reflection

### Example:
Put in application.properties configuration file:
```
quarkus.quarkus-register-reflection.external-dependencies[0].group=org.telegram
quarkus.quarkus-register-reflection.external-dependencies[0].artifact=telegrambots-meta
quarkus.quarkus-register-reflection.external-dependencies[1].group=anotherlibrary-group
quarkus.quarkus-register-reflection.external-dependencies[1].artifact=anotherlibrary-artifact
quarkus.quarkus-register-reflection.class-patterns[0]=org\\.telegram\\.telegrambots\\.meta\\.api.*
quarkus.quarkus-register-reflection.class-patterns[1]=another\\.sample\\.pattern
```
