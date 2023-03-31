import {Box, Button, TextField} from "@mui/material";
import useEditProfile from "./useEditProfile";
import EditPlaces from "./EditPlaces";
import EditActivities from "./EditActivities";


export default function EditProfile() {
    const {
        handleLastNameChange,
        handleFirstNameChange,
        inputFields,
        handleDescriptionChange,
        firstNameError,
        lastNameError,
        descriptionError
    } = useEditProfile();


    return (
        <Box sx={{
            textAlign: "center",
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            alignContent: "space-around",
            rowGap: "5px"
        }}>
            <Box sx={{height: "50px"}}></Box>

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
                required={true}
            />
                <TextField
                    size="small"
                    label="Last Name"
                    id="outlined-size-normal"
                    value={inputFields.lastName}
                    onChange={handleLastNameChange}
                    error={Boolean(lastNameError)}
                    helperText={"Please enter a last name"}
                    required={true}
                />
                <TextField
                    id="outlined-multiline-static"
                    label="Bio"
                    multiline
                    rows={4}
                    placeholder={"Introduce yourself in up to 500 characters..."}
                    value={inputFields.description}
                    onChange={handleDescriptionChange}
                    inputProps={{
                        maxLength: 500,
                    }}
                    required={true}
                />
                <EditPlaces places={inputFields.places}/>
                <EditActivities activities={inputFields.activities}/>

                <Box sx={{height: "50px"}}></Box>

                <Button type="submit" variant="outlined" color="inherit" size="medium">
                    Update Profile
                </Button>
            </Box>
        </Box>
    );
}
