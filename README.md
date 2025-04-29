# MovieSearch Demo App


## **Table of Contents**

- [Architecture](#architecture)
- [Setup and Installation](#setup-and-installation)

---


## **Architecture**

The project is separated in 3 modules : data , domain and app.App being the application module.
Application module uses MVI with single activity approach.
Domain module holds UseCases and domain models.
Data module holds the repository. 


### Library's used:

- **ComposeBOM**: Jetpack Compose.
- **Ktlint**: Kotlin linter.
- **Detekt**: Code anal. tool
- **Koin**: Dependency Injection
- **Coroutines** : Android Coroutines/Flows
- **Coil** : Image loading 
- **Retrofit/OkHttp** : Network calls

---

## **Setup and Installation**

### **Prerequisites**

- **Android Studio**:Latest [Download Android Studio](https://developer.android.com/studio)
- **JDK 11 or higher
  **: [Install JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Gradle**: Bundled with Android Studio.

### **Steps**

1. Clone the repository:
2. Build it