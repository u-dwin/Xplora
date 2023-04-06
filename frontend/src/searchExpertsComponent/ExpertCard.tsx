import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Avatar from "@mui/material/Avatar";
import {UserDetails} from "../editProfileComponent/UserDetails";


export default function ExpertCard(props: UserDetails) {

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
                    alignItems: "center",
                    borderStyle: "solid",
                    borderColour: "inherit",
                    borderWidth: "0.5px"
                }}>
                    <Avatar
                        alt="profile"
                        src={props.picture}
                        sx={{width: 55, height: 55}}
                    />
                <Typography>
                    {props.firstName}
                    {props.lastName}
                </Typography>

            </Box>
            <Box sx={{height: "5px"}}></Box>
        </Box>
    );
}