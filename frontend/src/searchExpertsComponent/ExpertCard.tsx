import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Avatar from "@mui/material/Avatar";
import {UserDetails} from "../editProfileComponent/UserDetails";
import Tag from "./Tag";
import {useNavigate} from "react-router-dom";


export default function ExpertCard(props: UserDetails) {
    const navigate = useNavigate();
    const maxCharLength: number = 100;
    const trimmedDescription: string = props.description.substring(0, maxCharLength)

    const placeTags = props.places.map((place: string) => {
        return (
            <>
                <Tag tagName={place} tagColor={"#184e74"}></Tag>{" "}
            </>
        )
    })

    const activitiesTags = props.activities.map((activities: string) => {
        return (
            <>
                <Tag tagName={activities} tagColor={"#18746c"}></Tag>{" "}
            </>
        )
    })

    function handleClick() {
        navigate('/userDetail', {state: {user: props}})
    }

    return (
        <div onClick={handleClick}>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                flexWrap: "wrap",
                rowGap: "5px",
                justifyContent: "center",
                justifyItems: "center",
                width: "95%",
            }}>

                <Box sx={{height: "5px"}}></Box>

                <Box
                    sx={{
                        display: "flex",
                        justifyContent: "left",
                        flexDirection: "row",
                        columnGap: "5px",
                        alignItems: "center",
                    }}>
                    <Avatar
                        alt="profile"
                        src={props.picture}
                        sx={{width: 55, height: 55}}
                    />
                    <Box>
                        <Typography sx={{fontSize: 13, fontWeight: 525}}>
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

                    }}>
                    {placeTags}
                    {activitiesTags}
                </Box>
            </Box>
        </div>
    );
}