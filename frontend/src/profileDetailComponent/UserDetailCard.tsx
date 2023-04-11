import {useLocation} from "react-router-dom";
import {Box, Typography} from "@mui/material";
import Avatar from "@mui/material/Avatar";
import * as React from "react";
import Tag from "../searchExpertsComponent/Tag";

export default function UserDetailCard() {
    const location = useLocation()
    const user = location.state.user

    const placeTags = user.places.map((place: string) => {
        return (
            <>
                <Tag tagName={place} tagColor={"#184e74"}></Tag>{" "}
            </>
        )
    })

    const activitiesTags = user.activities.map((activities: string) => {
        return (
            <>
                <Tag tagName={activities} tagColor={"#18746c"}></Tag>{" "}
            </>
        )
    })
    return (
        <Box sx={{
            display: "flex",
            flexDirection: "column",
            flexWrap: "wrap",
            rowGap: "5px",
            width: "100%",
            alignItems: "center",
            alignContent: "center",
            justifyContent: "center",
            justifyItems: "center"
        }}>
            <Box sx={{
                display: "flex",
                flexDirection: "column",
                flexWrap: "wrap",
                rowGap: "5px",
                justifyContent: "center",
                width: "85%",
            }}>

                <Box sx={{height: "5px"}}></Box>

                <Box
                    sx={{
                        display: "flex",
                        alignContent: "center",
                        flexDirection: "column",
                        columnGap: "5px",
                        alignItems: "center",
                        justifyItems: "center"
                    }}>

                    <Avatar
                        alt="profile"
                        src={user.picture}
                        sx={{width: 85, height: 85}}
                    />
                    <Typography sx={{fontSize: 14, fontWeight: 500}}>
                        {user.firstName}
                        {" "}
                        {user.lastName}
                    </Typography>
                    <Box sx={{height: "5px"}}></Box>
                    <Box>
                        <Typography sx={{fontSize: 14}}>
                            {user.description}
                        </Typography>
                    </Box>
                    <Box sx={{height: "5px"}}></Box>
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
        </Box>
    )
}

