import * as React from 'react';
import {ChangeEvent, useState} from 'react';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';

export default function EditActivities() {


    const [inputValue, setInputValue] = useState<string>("");
    const handleInputChange = async (event: ChangeEvent<HTMLInputElement>) => {
        const activity_search = event.target.value;
        setInputValue(activity_search);
    }

    const activities: string[] = ["Sightseeing", "Hiking", "Scuba diving", "Snorkeling", "Surfing", "Skiing", "Snowboarding", "Snowshoeing", "Biking", "Kayaking", "Canoeing", "Whitewater rafting", "Horseback riding", "Hot air ballooning", "Zip-lining", "Parasailing", "Bungee jumping", "Skydiving", "Rock climbing", "Camping", "Wildlife safaris", "Photography tours", "Food and wine tours", "Cultural tours", "Historical tours", "Museums and art galleries", "Beach activities", "Fishing", "Golfing", "Spa and wellness retreats", "Stand-up paddleboarding", "Whale watching", "Snuba diving", "Jet skiing", "Windsurfing", "Kiteboarding", "Volcano tours", "Caving", "Mountain climbing", "Trekking", "Balinese dance and music classes", "Local handicraft classes", "Yoga and meditation retreats", "Cooking classes", "Wine tasting", "Brewery tours", "Distillery tours", "Music festivals", "Film festivals", "Street art tours", "Ghost tours", "Scenic drives", "Train rides", "Helicopter tours", "Hot springs", "Sailing", "Canyoning", "Zorbing", "Kitesurfing", "Ice climbing", "Snowmobiling", "Sledding", "Ice skating", "Dog sledding", "Reindeer sledding", "Aurora hunting", "Glamping", "Biking tours", "River cruises", "Sea kayaking", "Biking tours", "Island hopping", "Archaeological tours", "Eco tours", "Wine and cheese tours", "Horse-drawn carriage rides", "High tea experiences", "Food truck tours", "Rooftop bar hopping", "Botanical gardens", "Local markets", "Street food tours", "Bike rentals", "Beach volleyball", "Tennis", "Ziplining", "Paintball", "Airboat rides", "Water parks", "Birdwatching", "Whale shark tours", "Turtle hatchling release", "Brewery crawls", "Distillery crawls", "River tubing", "Parasailing", "Jungle hikes", "Rock scrambling", "Wine and cheese picnics", "Sunrise/sunset watching"]


    return (
        <div>
            <Autocomplete
                multiple
                autoComplete
                id="tags-standard"
                limitTags={10}
                options={activities}
                getOptionLabel={(activities) => activities}
                defaultValue={[]}
                inputValue={inputValue}
                filterSelectedOptions
                onInputChange={(event, inputValue, reason) => {
                    if (reason === "reset") {
                        setInputValue("");
                    }
                }}
                renderInput={(params) => (
                    <TextField
                        {...params}
                        label="Favorite Travel Activities"
                        placeholder="Add up to 5 activities"
                        helperText="Add up to 5 activities"
                        onChange={handleInputChange}
                        required={true}
                    />
                )}
            />
        </div>
    )
}