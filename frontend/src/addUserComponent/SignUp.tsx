import {Box, Button, TextField, Typography} from "@mui/material";
import useAddUser from "./useAddUser";
import logo from "../logo.png";

export default function SignUp() {
    const {
        travelerChoiceClick,
        expertChoiceClick,
        registrationFormSubmit,
        handleEmailChange,
        handlePasswordChange,
        inputFields,
        addUser,
        emailError,
        passwordError
    } = useAddUser();

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
            <Box sx={{height: "50px"}}></Box>
            {addUser?.userType === "" ? (
                <>
                    <Typography variant={"h4"} justifyItems={"center"}>I am a...</Typography>
                    <Box sx={{height: "50px"}}></Box>
                    <Button variant="outlined" color="inherit" onClick={travelerChoiceClick}>
                        Traveler
                    </Button>
                    <Button variant="outlined" color="inherit" onClick={expertChoiceClick}>
                        Expert
                    </Button>
                </>
            ) : (
                <Box
                    component="form"
                    onSubmit={registrationFormSubmit}
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
                            id="outlined-size-normal"
                            value={inputFields.email}
                            onChange={handleEmailChange}
                            error={Boolean(emailError)}
                            helperText={emailError}
                        />

                    <TextField
                        size="small"
                        label="password"
                        type="password"
                        id="outlined-size-normal"
                        value={inputFields.password}
                        onChange={handlePasswordChange}
                        error={Boolean(passwordError)}
                        helperText={passwordError}
                    />
                    <Button type="submit" variant="outlined" color="inherit" size="medium">
                        Sign Up
                    </Button>
                </Box>
            )}
        </Box>
    );
}
