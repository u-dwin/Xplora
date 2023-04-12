import React from 'react';
import './App.css';
import SignUp from "./addUserComponent/SignUp";
import {Navigate, Route, Routes} from "react-router-dom";
import UserDetailsSuccess from "./editProfileComponent/UserDetailsSuccess";
import EditProfile from "./editProfileComponent/EditProfile";
import ExpertSearch from "./searchExpertsComponent/ExpertSearch";
import UserDetailCard from "./profileDetailComponent/UserDetailCard";
import {Chat} from "@mui/icons-material";
import ChatComponent from "./chatComponent/ChatComponent";

function App() {
    return (
        <div>
            <Routes>
                <Route path={"/"} element={<Navigate to={"/sign-up"}/>}/>
                <Route path="/sign-up" element={<SignUp/>}/>
                <Route path="/update-profile-success" element={<UserDetailsSuccess/>}/>
                <Route path="/edit-profile/:userId" element={<EditProfile/>}/>
                <Route path="/home" element={<ExpertSearch/>}/>
                <Route path="/userDetail" element={<UserDetailCard/>}/>
                <Route path="/chat" element={<ChatComponent/>}/>
            </Routes>
        </div>
    )
}

export default App;
