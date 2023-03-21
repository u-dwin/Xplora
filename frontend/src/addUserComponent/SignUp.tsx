import {Box, Button, TextField, Typography} from "@mui/material";
import useAddUser from "./useAddUser";

export default function SignUp() {
    const {
        travelerChoiceClick,
        expertChoiceClick,
        registrationFormSubmit,
        handleEmailChange,
        handlePasswordChange,
        inputFields,
        addUser,
    } = useAddUser();

    return (
        <Box sx={{textAlign: "center"}}>
            {addUser?.userType === "" ? (
                <>
                    <Typography variant={"h4"}>I am a...</Typography>
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
                        "& .MuiTextField-root": {m: 1, width: "25ch"},
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <div>
                        <TextField
                            label="email"
                            id="outlined-size-normal"
                            value={inputFields.email}
                            onChange={handleEmailChange}
                        />
                        <TextField
                            label="password"
                            id="outlined-size-normal"
                            value={inputFields.password}
                            onChange={handlePasswordChange}
                        />
                        <Button type="submit" variant="outlined" color="inherit">
                            Sign Up
                        </Button>
                    </div>
                </Box>
            )}
        </Box>
    );
}
