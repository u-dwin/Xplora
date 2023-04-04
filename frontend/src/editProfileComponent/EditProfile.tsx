import {Box, Button, IconButton, TextField, Typography} from "@mui/material";
import useEditProfile from "./useEditProfile";
import EditPlaces from "./EditPlaces";
import EditActivities from "./EditActivities";
import Avatar from '@mui/material/Avatar';
import {DriveFolderUpload} from "@mui/icons-material";


export default function EditProfile() {
    const {
        handleLastNameChange,
        handleFirstNameChange,
        handlePlacesSelectionChange,
        handleActivitySelectionChange,
        inputFields,
        notAllFieldsFilledError,
        handleDescriptionChange,
        updateProfileFormSubmit,
        handleFileSelected,
        handleFileUpload
    } = useEditProfile();





    return (
        <Box sx={{
            textAlign: "center",
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            alignContent: "space-around",
            alignItems: "center",
            rowGap: "5px"
        }}>
            <Box sx={{height: "50px"}}></Box>

            <Box
                component="form"
                onSubmit={updateProfileFormSubmit}
                sx={{
                    "& .MuiTextField-root": {
                        m: 1,
                        width: "25ch",
                        display: "flex",
                        flexDirection: "column",
                        flexWrap: "wrap",
                        rowGap: "5px",
                        justifyContent: "center",
                    },
                }}
                noValidate
                autoComplete="off"
            >

                <Box
                    sx={{
                        display: "flex",
                        justifyContent: "center",
                        flexDirection: "row",
                        columnGap: "25px",
                        alignItems: "center"
                    }}
                >
                    <Avatar
                        alt="profile"
                        src={inputFields.picture}
                        sx={{width: 70, height: 70}}
                    />
                    <IconButton color="primary" aria-label="upload picture" component="label"
                                onClick={handleFileUpload}>
                        <input hidden accept="image/*" type="file" onInput={handleFileSelected}/>
                        <DriveFolderUpload/>
                    </IconButton>
                </Box>

                <TextField
                    size="small"
                    label="First Name"
                    id="outlined-size-normal"
                    value={inputFields.firstName}
                    onChange={handleFirstNameChange}
                    helperText={"Please enter a first name"}
                    inputProps={{maxLength: 15}}
                    required={true}
                />
                <TextField
                    size="small"
                    label="Last Name"
                    id="outlined-size-normal"
                    value={inputFields.lastName}
                    onChange={handleLastNameChange}
                    helperText={"Please enter a last name"}
                    inputProps={{maxLength: 15}}
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
                    inputProps={{maxLength: 500}}
                    required={true}
                />
                <EditPlaces places={inputFields.places} handlePlacesSelectionChange={handlePlacesSelectionChange}/>
                <EditActivities activities={inputFields.activities}
                                handleActivitiesSelectionChange={handleActivitySelectionChange}/>

                <Box sx={{height: "5px"}}></Box>

                <Typography>{notAllFieldsFilledError}</Typography>

                <Box sx={{height: "5px"}}></Box>

                <Button type="submit" variant="outlined" color="inherit" size="medium">
                    Update Profile
                </Button>
            </Box>
        </Box>
    );
}
