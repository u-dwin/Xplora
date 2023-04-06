import {Box} from "@mui/material";
import TextField from "@mui/material/TextField";
import {UserDetails} from "../editProfileComponent/UserDetails";
import axios from "axios";
import {useEffect, useState} from "react";
import ExpertCard from "./ExpertCard";


export default function ExpertSearch() {
    const [users, setUsers] = useState<UserDetails[]>([]);

    useEffect(() => {
        axios.get("/api/users/experts").then((response) => {
            if (response.data) {
                setUsers(response.data);
            }
        })
    }, [])

    const expertCard = users.map((expert: UserDetails) => {
        return (
            <ExpertCard picture={expert.picture} description={expert.description} firstName={expert.firstName}
                        lastName={expert.lastName} places={expert.places} activities={expert.activities}></ExpertCard>
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

            <Box sx={{height: "20px"}}></Box>

            <Box
                sx={{
                    display: "flex",
                    justifyContent: "center",
                    flexDirection: "row",
                    columnGap: "5px",
                    justifyItems: "center",
                    alignItems: "center",
                    width: "85%",
                }}
            >
                <TextField id="outlined-search"
                           label=""
                           type="search"
                           size="small"
                           fullWidth
                           placeholder="Search for places and activities..."
                />

            </Box>

            {expertCard}
        </Box>

    )
}

