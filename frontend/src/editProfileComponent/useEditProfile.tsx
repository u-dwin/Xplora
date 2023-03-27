import {ChangeEvent, useState} from "react";
import {useNavigate} from "react-router-dom";
import {Profile} from "./Profile";

export default function useEditProfile() {
    const [firstNameError, setFirstNameError] = useState<string | boolean>(false)
    const [lastNameError, setLastNameError] = useState<string | boolean>(false)
    const [descriptionError, setDescriptionError] = useState<string | boolean>(false)


    const navigate = useNavigate();
    const [inputFields, setInputFields] = useState<Profile>({
        picture: "",
        description: "",
        firstName: "",
        lastName: "",
        places: [""],
        activities: [""],
    })

    function handleFirstNameChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, firstName: evt.target.value})
    }

    function handleLastNameChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, lastName: evt.target.value})
    }

    function validateFirstName(firstName: string) {
        return firstName.trim().length > 1;
    }

    function validateLastName(lastName: string): boolean {
        return lastName.trim().length > 1;
    }

    function validateDescription(description: string): boolean {
        return description.trim().length > 30;
    }


    return {
        handleLastNameChange,
        handleFirstNameChange,
        inputFields,
        firstNameError,
        lastNameError,
        descriptionError
    }
}
