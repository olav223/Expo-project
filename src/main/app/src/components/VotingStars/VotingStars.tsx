import restApi from "../../utils/restApi";
import {Rating} from "react-simple-star-rating";
import {useEffect, useState} from "react";
import Auth from "../../utils/auth";
import "./VotingStars.css";
import notification from "../../utils/notification";
const confetti = require('canvas-confetti');

const VotingStars = () => {
    const [rating, setRating] = useState(0)
    const auth = new Auth();

    const url = window.location.href.split("?")[1];
    const params = new URLSearchParams(url);

    const urlRequired:boolean = params.has("id") && params.has("event");

    const handleRating = (rate: number) => {
        setRating(rate)
    }

    const particles = () => {
        const defaults = {
            spread: 360,
            ticks: 50,
            gravity: 0,
            decay: 0.94,
            startVelocity: 30,
            shapes: ['star'],
            colors: ['FFE400', 'FFBD00', 'E89400', 'FFCA6C', 'FDFFB8']
        };
        const myConfetti = confetti.create(document.getElementById("partical-canvas"),{resize:true});

        myConfetti({
            ...defaults,
            particleCount: 40,
            scalar: 1.2,
            shapes: ['star']
        });

        myConfetti({
            ...defaults,
            particleCount: 10,
            scalar: 0.75,
            shapes: ['circle']
        });
    }

    const getSavedVote = async() => {
        const user = auth.getUser();
        if (user === null) {
            await auth.createVoter(params.get("event")??"");
        } else {
            const result = await restApi({url: "/api/vote?standID="+params.get("id")+"&voterID="+auth.getUser()!.username, method: "GET"});
            if (result.status === 200) {
                handleRating(result.body);
            }
        }
    }

    const vote = async() => {
        const user = auth.getUser();

        if (user !== null) {
            const result = await restApi({url: "/api/vote", method: "POST", body: {
                    "votePK": {
                        "id_voter": auth.getUser()!.username,
                        "id_stand": params.get("id")
                    },
                    "stars": rating
                }});
            if (result.status === 200) {
                setTimeout(particles, 0);
                setTimeout(particles, 100);
                setTimeout(particles, 200);
            }
        } else {
            notification({text: "Fant ikke bruker", type: "error"})
        }
    }

    useEffect(() => {
        if (urlRequired) getSavedVote();
    }, [])

    if (urlRequired) {
        return <div>
            <canvas id="partical-canvas"></canvas>
            <Rating size={35} initialValue={rating} onClick={handleRating} />
            <button className={"submit-vote"} onClick={vote}>Stem</button>
        </div>
    } else {
        return null;
    }
}

export default VotingStars;