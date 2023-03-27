import {Box, Button, TextField} from "@mui/material";
import useEditProfile from "./useEditProfile";
import EditPlaces from "./EditPlaces";

export default function EditProfile() {
    const {
        handleFirstNameChange,
        handleLastNameChange,
        inputFields,
        firstNameError,
        lastNameError
    } = useEditProfile();

    return (
        <Box
            component="form"
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
                value={inputFields.firstName}
                onChange={handleFirstNameChange}
                error={Boolean(firstNameError)}
                helperText={"Please enter a first name"}
            />
            <TextField
                size="small"
                label="Last Name"
                id="outlined-size-normal"
                value={inputFields.lastName}
                onChange={handleLastNameChange}
                error={Boolean(lastNameError)}
                helperText={"Please enter a last name"}
            />
            <EditPlaces/>
            <Button type="submit" variant="outlined" color="inherit" size="medium">
                Update Profile
            </Button>
        </Box>
    );
}
