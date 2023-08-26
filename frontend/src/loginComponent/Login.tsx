import {Box, Button, TextField, Typography} from "@mui/material";
import logo from "../logo.png";
import useLogin from "./useLogin";

export default function Login() {
    const {
        loginFormSubmit,
        handleEmailChange,
        handlePasswordChange,
        inputFields
    } = useLogin();

    return (
        <Box sx={{
            textAlign: "center",
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            alignContent: "space-around",
            rowGap: "5px"
        }}>
            <Box> <img src={logo} alt="xplora_logo" width="200" height="133"/> </Box>
            <Typography sx={{fontSize: 16, fontWeight: 'bold'}}>
                {"Login"}
            </Typography>
                <Box
                    component="form"
                    onSubmit={loginFormSubmit}
                    sx={{
                        "& .MuiTextField-root": {
                            m: 1,
                            width: "25ch",
                            display: "flex",
                            flexDirection: "column",
                            flexWrap: "wrap",
                            rowGap: "5px",
                            justifyContent: "center"
                        },
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <TextField
                        size="small"
                        label="email"
                        id="emailTextfield"
                        value={inputFields.email}
                        onChange={handleEmailChange}
                    />

                    <TextField
                        size="small"
                        label="password"
                        type="password"
                        id="passwordTextfield"
                        value={inputFields.password}
                        onChange={handlePasswordChange}
                    />
                    <Button type="submit" variant="outlined" color="inherit" size="medium">
                        Sign Up
                    </Button>
                </Box>
        </Box>
    );
}
