
# Xplora
An app that connects travelers and travel experts to explore remote travel destinations. 

![xplora_gif](https://user-images.githubusercontent.com/122820707/232806586-8c91fa33-2db1-4e0f-ab87-f9259f9582de.gif)

## Deployed Version
xplora.fly.dev -> Choose traveler

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

`CLOUDINARY_URL` --> Cloudinary URL containing your key

## Features

- Registration/Sign Up
- Profile edit 
- Search 
- Profile view (in development)
- Chat (in development)
- Login (planned)
- Delete Account (planned)
- Many more as the project develops

## Lessons Learned

1. Mock external libraries with static and final functions such as Google Places API by using helper classes that handle the API requests. In this way you can mock your helper class functions for testing purposes. 

2. If you plan to use something that you never used before (libraries, API's) plan plenty of time.

3. CSS frameworks such as MUI make your (developers) life easier. 

4. Do not think too complicated, sometimes solutions are simple. 

5. Rubber ducking is super useful.

6. Take breaks.

7. If you have issues with positioning things put borders around elements to see where they are. 

