import restApi from "../../utils/restApi";
import {Rating} from "react-simple-star-rating";
import {useState} from "react";
import Auth from "../../utils/auth";
import "./VotingStars.css";

interface VotingStarsProps {
    standId: number;
}
const VotingStars = (props:VotingStarsProps) => {
    const [rating, setRating] = useState(0)
    const auth = new Auth();

    const handleRating = (rate: number) => {
        setRating(rate)
    }
    const vote = async() => {
        const result = await restApi({url: "/api/vote", method: "POST", body: {
                "votePK": {
                    "id_voter": auth.getUser().username,
                    "id_stand": props.standId
                },
                "stars": rating
            }});
        console.log(result.status)
        if (result.status === 200) {
            window.alert("Stemme sendt inn!");
        }
    }

    return <div>
        <Rating size={35} onClick={handleRating} />
        <button className={"submit-vote"} onClick={vote}>Stem</button>
    </div>
}

export default VotingStars;