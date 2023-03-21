import {Box, Button, TextField, Typography} from "@mui/material";
import useAddUser from "./useAddUser";
export default function SignUpChoice() {
    const{travelerChoiceClick, expertChoiceClick}=useAddUser();
    return (
        <div>
            <Typography variant={"h4"}>
                I am a...
            </Typography>
            <Button variant="outlined" color="inherit" onClick={travelerChoiceClick}>Traveler</Button>
            <Button variant="outlined" color="inherit" onClick={expertChoiceClick}>Expert</Button>
        </div>
    );
}
