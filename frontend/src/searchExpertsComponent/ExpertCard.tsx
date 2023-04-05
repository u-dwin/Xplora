import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import Avatar from "@mui/material/Avatar";
import {UserDetails} from "../editProfileComponent/UserDetails";


export default function ExpertCard(props: UserDetails) {

    return (
        <Card>
            <CardContent>
                <Box
                    sx={{
                        display: "flex",
                        justifyContent: "center",
                        flexDirection: "row",
                        columnGap: "5px",
                        justifyItems: "center",
                        alignItems: "center",
                        width: "100%"
                    }}>
                    <Avatar
                        alt="profile"
                        src={props.picture}
                        sx={{width: 70, height: 70}}
                    />
                    <Typography>
                        {props.firstName}
                        {props.lastName}
                    </Typography>

                </Box>


            </CardContent>
        </Card>
    );
}