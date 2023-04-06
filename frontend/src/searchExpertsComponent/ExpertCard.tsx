import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Avatar from "@mui/material/Avatar";
import {UserDetails} from "../editProfileComponent/UserDetails";


export default function ExpertCard(props: UserDetails) {

    const maxCharLength: number = 100;
    const trimmedDescription: string = props.description.substring(0, maxCharLength)


    return (
        <Box sx={{
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            rowGap: "5px",
            justifyContent: "center",
            width: "85%",
            borderStyle: "dotted",
            borderColor: "inherit",
            borderWidth: "0.5px",
        }}>

            <Box sx={{height: "5px"}}></Box>

            <Box
                sx={{
                    display: "flex",
                    justifyContent: "left",
                    flexDirection: "row",
                    columnGap: "5px",
                    alignItems: "top",
                    borderStyle: "solid",
                    borderColour: "inherit",
                    borderWidth: "0.5px"
                }}>
                <Avatar
                    alt="profile"
                    src={props.picture}
                    sx={{width: 55, height: 55}}
                />
                <Box>

                    <Typography sx={{fontSize: 13, fontWeight: 500}}>
                        {props.firstName}
                        {" "}
                        {props.lastName}
                    </Typography>
                    <Typography sx={{fontSize: 12}}>
                        {trimmedDescription}...
                    </Typography>
                    <Typography sx={{fontSize: 12}}>
                        {props.places}
                    </Typography>
                    <Typography sx={{fontSize: 12}}>
                        {props.activities}
                    </Typography>
                </Box>

            </Box>
            <Box sx={{height: "5px"}}></Box>
        </Box>
    );
}