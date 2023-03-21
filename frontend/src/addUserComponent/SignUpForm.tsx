import {Box, Button, TextField} from "@mui/material";
import useAddUser from "./useAddUser";


export default function SignUp() {
    const {registrationFormSubmit, handleEmailChange, handlePasswordChange, inputFields} = useAddUser();

    return (
        <Box
            component="form"
            onSubmit={registrationFormSubmit}
            sx={{
                '& .MuiTextField-root': {m: 1, width: '25ch'},
            }}
            noValidate
            autoComplete="off"
        >
            <div>
                <TextField label="email" id="outlined-size-normal" value={inputFields.email}
                           onChange={handleEmailChange}/>
                <TextField label="password" id="outlined-size-normal" value={inputFields.password}
                           onChange={handlePasswordChange}/>
                <Button type="submit" variant="outlined" color="inherit">Sign Up</Button>
            </div>
        </Box>
    );
}
