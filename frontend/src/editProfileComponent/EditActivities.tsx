import * as React from 'react';
import {ChangeEvent, useEffect, useState} from 'react';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';

type EditActivitiesProps = {
    activities: string[]
}
export default function EditActivities(props: EditActivitiesProps) {

    const activities: string[] = ["Sightseeing", "Hiking", "Scuba diving", "Snorkeling", "Surfing", "Skiing", "Snowboarding", "Biking", "Kayaking", "Whitewater rafting", "Horseback riding", "Hot air ballooning", "Zip-lining", "Parasailing", "Bungee jumping", "Skydiving", "Rock climbing", "Camping", "Wildlife safaris", "Photography tours", "Food tours", "Cultural tours", "Historical tours", "Museums", "Beaches", "Fishing", "Golfing", "Spa", "Paddleboarding", "Whale watching", "Jet skiing", "Windsurfing", "Kiteboarding", "Volcano tours", "Caving", "Trekking", "Dancing", "Classes", "Yoga", "Cooking classes", "Wine tasting", "Brewery tours", "Music festivals", "Film festivals", "Street art tours", "Ghost tours", "Scenic drives", "Train rides", "Helicopter tours", "Hot springs", "Sailing", "Canyoning", "Zorbing", "Ice climbing", "Snowmobiling", "Sledding", "Ice skating", "Dog sledding", "Reindeer sledding", "Aurora hunting", "Glamping", "River cruises", "Island hopping", "Archaeological tours", "Eco tours", "Wine tours", "Carriage rides", "High tea experiences", "Food truck tours", "Rooftop bar hopping", "Botanical gardens", "Local markets", "Street food tours", "Bike rentals", "Beach sports", "Tennis", "Ziplining", "Paintball", "Airboat rides", "Water parks", "Birdwatching", "Whale shark tours", "Turtle hatchling release", "Brewery crawls", "River tubing", "Jungle tours", "Rock scrambling", "Picnics", "Sunrise/sunset watching"]
    const [inputValue, setInputValue] = useState<string>("");
    const [options, setOptions] = useState<string[]>([]);
    const [activitySelection, setActivitySelection] = useState<string[]>(props.activities);
    const [value, setValue] = useState<string[]>([])

    useEffect(() => {
        const uniqueOptions = Array.from(new Set(options));
        setOptions(uniqueOptions);
    }, [options]);

    const handleInputChange = async (event: ChangeEvent<HTMLInputElement>) => {
        const query = event.target.value;
        setInputValue(query);

        const filteredOptions = activities.filter((activity) =>
            activity.toLowerCase().includes(query.toLowerCase())
        );

        setOptions(filteredOptions);
    };

    const handleActivitySelectionChange = (event: React.SyntheticEvent, value: string[]) => {
        setValue(value)
        setActivitySelection(value);
    };


    return (
        <div>
            <Autocomplete
                multiple
                autoComplete
                id="tags-standard"
                limitTags={5}
                options={options}
                getOptionLabel={(activities) => activities}
                value={value}
                freeSolo={true}
                inputValue={inputValue}
                filterSelectedOptions
                onChange={handleActivitySelectionChange}
                onInputChange={(event, inputValue, reason) => {
                    if (reason === "reset") {
                        setInputValue("");
                    }
                }}
                renderInput={(params) => (
                    <TextField
                        {...params}
                        label="Favorite Travel Activities"
                        placeholder="Add up to 7 activities"
                        helperText="Add up to 7 activities"
                        InputProps={{
                            ...params.InputProps,
                            type: 'search',
                        }}
                        onChange={handleInputChange}
                        required={true}
                    />
                )}
            />
        </div>
    )
}