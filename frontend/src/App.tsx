import React from 'react';
import './App.css';
import SignUp from "./addUserComponent/SignUp";
import {Navigate, Route, Routes} from "react-router-dom";
import UserDetailsSuccess from "./editProfileComponent/UserDetailsSuccess";
import EditProfile from "./editProfileComponent/EditProfile";
import SearchExperts from "./searchExpertsComponent/SearchExperts";

function App() {
    return (
        <div>
            <Routes>
                <Route path={"/"} element={<Navigate to={"/sign-up"}/>}/>
                <Route path="/sign-up" element={<SignUp/>}/>
                <Route path="/update-profile-success" element={<UserDetailsSuccess/>}/>
                <Route path="/edit-profile/:userId" element={<EditProfile/>}/>
                <Route path="/home" element={<SearchExperts/>}/>
            </Routes>
        </div>
    )
}

export default App;
