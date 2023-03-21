import {Box, TextField} from "@mui/material";

export default function SignUpForm() {
    return (
        <Box
            component="form"
            sx={{
                '& .MuiTextField-root': {m: 1, width: '25ch'},
            }}
            noValidate
            autoComplete="off"
        >
            <div>
                <TextField label="email" id="outlined-size-normal" defaultValue=""/>
                <TextField label="password" id="outlined-size-normal" defaultValue=""/>
            </div>
        </Box>
    );
}
