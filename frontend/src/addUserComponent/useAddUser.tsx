import {ChangeEvent, useEffect, useState} from "react";
import {User} from "./User";
import {useNavigate} from "react-router-dom";
import axios from "axios"

export default function useAddUser() {
    const [emailError, setEmailError] = useState<string | boolean>(false)
    const [passwordError, setPasswordError] = useState<string | boolean>(false)
    const navigate = useNavigate();
    const [addUser, setAddUser] = useState<User>()
    const [inputFields, setInputFields] = useState({
        userType: "",
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

    function validateEmail(email: string) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
        return re.test(email);
    }

    function validatePassword(inputField: string): boolean {
        return inputField.trim().length > 5;
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
    }, [addUser?.userType, inputFields, passwordError, emailError])

    useEffect(() => {
        setEmailError(false)
        setPasswordError(false)
    }, [inputFields])

    function registrationFormSubmit(evt: React.FormEvent<HTMLFormElement>) {
        evt.preventDefault();

        if (validateEmail(inputFields.email) && validatePassword(inputFields.password)) {
            axios.post("/api/users/add", {
                userType: addUser?.userType,
                userEmailAddress: addUser?.userEmailAddress,
                userPassword: addUser?.userPassword
            })
                .then(() => {
                    navigate("/sign-up-success")
                })
                .catch((error) => console.error(error))
        } else if (!validateEmail(inputFields.email)) {
            return setEmailError("Please enter a valid email address")
        } else if (!validatePassword(inputFields.password)) {
            return setPasswordError("Please enter a password with at least 6 characters")
        }
    }

    return {
        travelerChoiceClick,
        expertChoiceClick,
        inputFields,
        registrationFormSubmit,
        handleEmailChange,
        handlePasswordChange,
        addUser,
        setAddUser,
        emailError,
        passwordError
    }
}
