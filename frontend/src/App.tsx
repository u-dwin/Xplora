import React from 'react';
import './App.css';
import SignUpForm from "./addUserComponent/SignUpForm";
import SignUpChoice from "./addUserComponent/SignUpChoice";
import {createTheme} from "@mui/material";
import {grey} from "@mui/material/colors";
import {Route, Routes} from "react-router-dom";

const xploraTheme = createTheme({
    palette: {
        primary: grey,
    },
});

function App() {
    return (
        <div>
            <Routes>
                <Route path="/" element={<SignUpChoice/>}/>
                <Route path="/sign-up" element={<SignUpForm/>}/>
            </Routes>
        </div>
    )
}

export default App;
