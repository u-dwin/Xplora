import {Box, Button, TextField} from "@mui/material";
import useEditProfile from "./useEditProfile";
import PlaceTags from "./PlaceTags";

export default function EditProfile() {
    const {
        registrationFormSubmit,
        handleEmailChange,
        inputFields,
        emailError,
    } = useEditProfile();

    return (
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
                label="First Name"
                id="outlined-size-normal"
                value={inputFields.email}
                onChange={handleEmailChange}
                error={Boolean(emailError)}
                helperText={emailError}
            />
            <TextField
                size="small"
                label="Last Name"
                id="outlined-size-normal"
                value={inputFields.email}
                onChange={handleEmailChange}
                error={Boolean(emailError)}
                helperText={emailError}
            />
            <PlaceTags/>
            <Button type="submit" variant="outlined" color="inherit" size="medium">
                Update Profile
            </Button>
        </Box>
    );
}
