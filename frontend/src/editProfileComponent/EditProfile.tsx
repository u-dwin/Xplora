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
        fileInputRef,
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
                        src="https://media.licdn.com/dms/image/D4E03AQGN7GwF9u-UTQ/profile-displayphoto-shrink_800_800/0/1679248833047?e=1686182400&v=beta&t=SkaZW7K3m0pKnMZa4_tcB8vlbfJQ_AneOscbP6nyoy8"
                        sx={{width: 70, height: 70}}
                    />
                    <IconButton color="primary" aria-label="upload picture" component="label">
                        <input hidden accept="image/*" type="file" onClick={handleFileUpload}/>
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
