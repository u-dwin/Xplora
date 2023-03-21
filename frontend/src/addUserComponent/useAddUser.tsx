import {useState} from "react";
import {User} from "./User";
import {useNavigate} from "react-router-dom";

export default function useAddUser() {
    const navigate = useNavigate();
    const [addUser, setAddUser] = useState<User | null>(null)
    const [inputFields, setInputFields] = useState({
        email: "",
        password: ""
    })

    function travelerChoiceClick() {
        setAddUser({
                userType: "traveler",
                userEmailAddress: "",
                userPassword: "",
            }
        )
        navigate("/sign-up")
    }

    function expertChoiceClick() {
        setAddUser({
                userType: "expert",
                userEmailAddress: "",
                userPassword: "",
            }
            )
        navigate("/sign-up")
    }

    function registrationFormSubmit() {
        setAddUser({
            userType: addUser?.userType ?? "",
            userEmailAddress: inputFields.email,
            userPassword: inputFields.password,
        })
    }

    return {travelerChoiceClick, expertChoiceClick}
}
