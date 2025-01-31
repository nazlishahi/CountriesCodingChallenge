# Countries Coding Challenge

This is a coding challenge project that fetches a list of countries from a given JSON endpoint and displays them in a RecyclerView, following MVVM architecture and clean coding principles.

## 📌 Features

- Fetches country data from a remote JSON source.
- Displays country details in a structured format using a `RecyclerView`.
- Implements MVVM architecture for better separation of concerns.
- Uses Coroutines for asynchronous operations.
- Handles errors and supports device rotation.

## 🛠️ Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM + Clean Architecture
- **Business Logic:** ViewModel, LiveData, UseCase, Repository
- **UI Components**: RecyclerView, Activity, Fragment
- **Networking**: Retrofit, OkHttpClient
- **Dependency Injection**: Hilt
- **Concurrency**: Coroutines
- **Unit Testing**: JUnit, Mockito

## 📡 API Source

The app fetches country data from:

```
https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/?offset=10/countries.json
```

## 🚀 Setup & Run

1. Clone the repository:
   ```sh
   git clone https://github.com/nazlishahi/CountriesCodingChallenge.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the app on an emulator or device.

## 🧪 Testing

Run unit tests with:

```sh
./gradlew test
```

## 📸 Screenshots and recording
<p>
  <img src="https://github.com/user-attachments/assets/ced6c75b-e55f-493a-a7ba-9df9ccdf9396" width="200"/>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
  
  <img src="https://github.com/user-attachments/assets/8799f639-f738-4fcc-a5df-b827ef2a0c02" width="200"/>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp   
  
  <img src="https://github.com/user-attachments/assets/d6d23820-b714-44f4-9d77-0bbcc2964e97" width="400"/>
</p>



[https://github.com/user-attachments/assets/08ac4112-64f3-4857-bbc1-0cfde894b49e](https://github.com/user-attachments/assets/08ac4112-64f3-4857-bbc1-0cfde894b49e)

## [📜](https://github.com/user-attachments/assets/08ac4112-64f3-4857-bbc1-0cfde894b49e📜) License

This project is for coding evaluation purposes.

---

### ✨ Contributions & Feedback

Feel free to fork, open issues, or suggest improvements!

---
