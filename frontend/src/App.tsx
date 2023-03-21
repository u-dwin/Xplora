import React from 'react';
import './App.css';
import SignUp from "./addUserComponent/SignUp";
import {createTheme} from "@mui/material";
import {grey} from "@mui/material/colors";
import {Route, Routes} from "react-router-dom";
import SignUpSuccess from "./addUserComponent/SignUpSuccess";

const xploraTheme = createTheme({
    palette: {
        primary: grey,
    },
});

function App() {
    return (
        <div>
            <Routes>
                <Route path="/sign-up" element={<SignUp/>}/>
                <Route path="/sign-up-success" element={<SignUpSuccess/>}/>
            </Routes>
        </div>
    )
}

export default App;
