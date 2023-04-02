
# Xplora
An app that connects travelers and travel experts to explore remote travel destinations. 


## Run Locally

```bash
  cd frontend
```

Install dependencies

```bash
  npm install
```

```bash
  npm i react-router-dom
```

```bash
  npm i axios
```


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`GOOGLE_API_KEY` --> Your Google Cloud API Key

`MONGODB_URL` --> URL of your local MongoDB


## Features

- Registration/Sign Up
- Profile edit (in development)
- Search (planned)
- Profile view (planned)
- Chat (planned)
- Login (planned)
- Delete Account (planned)
- Many more as the project develops

## Lessons Learned

1. Mock external libraries with static and final functions such as Google Places API by using helper classes that handle the API requests. In this way you can mock your helper class functions for testing purposes. 

2. If you plan to use something that you never used before (libraries, API's) plan plenty of time.

3. CSS frameworks such as MUI make your (developers) life easier. 

4. Do not think too complicated, sometimes solutions are simple. 
