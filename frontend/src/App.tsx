import React from 'react';
import './App.css';
import SignUp from "./addUserComponent/SignUp";
import {Navigate, Route, Routes} from "react-router-dom";
import SignUpSuccess from "./addUserComponent/SignUpSuccess";
import EditProfile from "./editProfileComponent/EditProfile";

function App() {
    return (
        <div>
            <Routes>
                <Route path={"/"} element={<Navigate to={"/sign-up"}/>}/>
                <Route path="/sign-up" element={<SignUp/>}/>
                <Route path="/sign-up-success" element={<SignUpSuccess/>}/>
                <Route path="/edit-profile/:id" element={<EditProfile/>}/>
            </Routes>
        </div>
    )
}

export default App;
