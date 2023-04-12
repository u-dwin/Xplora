import {Box} from "@mui/material";
import TextField from "@mui/material/TextField";
import {UserDetails} from "../editProfileComponent/UserDetails";
import axios from "axios";
import {useEffect, useState} from "react";
import ExpertCard from "./ExpertCard";


export default function ExpertSearch() {
    const [experts, setExperts] = useState<UserDetails[]>([]);
    const [searchQuery, setSearchQuery] = useState<string>("");

    useEffect(() => {
        axios.get("/api/users/experts").then((response) => {
            if (response.data) {
                setExperts(response.data);
            }
        })
    }, [])

    const expertCards = experts
        .filter((expert: UserDetails) => {
            const words = searchQuery.toLowerCase().split(" ");
            return words.every(word =>
                expert.places.toString().toLowerCase().includes(word) ||
                expert.activities.toString().toLowerCase().includes(word) ||
                expert.description.toString().toLowerCase().includes(word)
            );
        })
        .map((expert: UserDetails) => {
            const key = `${expert.firstName}-${expert.lastName}-${expert.description}-${expert.places}-${expert.activities}`;
            return (
                <ExpertCard key={key} picture={expert.picture} description={expert.description}
                            firstName={expert.firstName}
                            lastName={expert.lastName} places={expert.places}
                            activities={expert.activities}></ExpertCard>
            )
        })

    const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSearchQuery(event.target.value);
    };

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
                    flexDirection: "column",
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
                           value={searchQuery}
                           onChange={handleSearchChange}
                />
                {expertCards}
            </Box>
        </Box>

    )
}

