import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Avatar from "@mui/material/Avatar";
import {UserDetails} from "../editProfileComponent/UserDetails";
import Tag from "./Tag";


export default function ExpertCard(props: UserDetails) {

    const maxCharLength: number = 100;
    const trimmedDescription: string = props.description.substring(0, maxCharLength)

    const placeTags = props.places.map((place: string) => {
        return (
            <>
                <Tag tagName={place}></Tag>{" "}
            </>
        )
    })

    const activitiesTags = props.activities.map((activities: string) => {
        return (
            <>
                <Tag tagName={activities}></Tag>{" "}
            </>
        )
    })

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
                </Box>
            </Box>
            <Box
                sx={{
                    display: "flex",
                    justifyContent: "left",
                    flexDirection: "row",
                    flexWrap: "wrap",
                    columnGap: "5px",
                    rowGap: "5px",
                    alignItems: "top",
                    borderStyle: "solid",
                    borderColour: "inherit",
                    borderWidth: "0.5px"
                }}>
                {placeTags}
            </Box>
            <Box
                sx={{
                    display: "flex",
                    justifyContent: "left",
                    flexDirection: "row",
                    flexWrap: "wrap",
                    columnGap: "5px",
                    rowGap: "5px",
                    alignItems: "top",
                    borderStyle: "solid",
                    borderColour: "inherit",
                    borderWidth: "0.5px"
                }}>
                {activitiesTags}
            </Box>
        </Box>
    );
}