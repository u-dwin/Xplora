import {ChangeEvent, useEffect, useState} from "react";
import {User} from "./User";
import {useNavigate} from "react-router-dom";
import axios from "axios"

export default function useAddUser() {
    const navigate = useNavigate();
    const [addUser, setAddUser] = useState<User>()
    const [inputFields, setInputFields] = useState({
        email: "",
        password: "",
    })

    function travelerChoiceClick() {
        setAddUser({
                userType: "traveler",
                userEmailAddress: "",
                userPassword: "",
            }
        )
    }

    function expertChoiceClick() {
        setAddUser({
                userType: "expert",
                userEmailAddress: "",
                userPassword: "",
            }
        )
    }

    function handleEmailChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, email: evt.target.value})
    }

    function handlePasswordChange(evt: ChangeEvent<HTMLInputElement>) {
        setInputFields({...inputFields, password: evt.target.value})
    }

    useEffect(() => {
        setAddUser({
            userType: addUser?.userType ?? "",
            userEmailAddress: inputFields.email,
            userPassword: inputFields.password
        })
    }, [addUser?.userType, inputFields])

    function registrationFormSubmit(evt: React.FormEvent<HTMLFormElement>) {
        evt.preventDefault();

        axios.post("/api/users/add", {
            userType: addUser?.userType,
            userEmailAddress: addUser?.userEmailAddress,
            userPassword: addUser?.userPassword
        })
            .then(() => {
                navigate("/sign-up-success")
            })
            .catch((error) => console.error(error))
    }

    return {
        travelerChoiceClick,
        expertChoiceClick,
        inputFields,
        registrationFormSubmit,
        handleEmailChange,
        handlePasswordChange,
        addUser,
        setAddUser
    }
}
