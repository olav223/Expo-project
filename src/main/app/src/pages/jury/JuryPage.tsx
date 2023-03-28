import { useEffect, useState } from "react";
import restApi from "../../utils/restApi";
import { StandWithScore } from "../../model/Stand";
import { Link } from "react-router-dom";

const JuryPage = () => {

    const [scores, setScores] = useState<StandWithScore[] | null>(null);

    useEffect(() => { // TODO Sjekk om bruker er innlogget og jurymedlem
        async function getScores(): Promise<void> {
            const res = await restApi({ url: `/api/vote/score?eventID=${ 1 }`, method: "GET" }); // TODO hent id fra aktiv event
            if (res.status === 200) {
                setScores(res.body);
            }
            else {
                console.error(res);
            }
        }

        getScores();
    }, []);

    return (
        <>
            <h1>Scores for event</h1>
            {
                scores === null ?
                    <p>Loading...</p>
                    :
                    <table>
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>Navn</th>
                                <th>Score</th>
                            </tr>
                        </thead>
                        <tbody style={ { textAlign: "center" } }>
                            {
                                scores?.map(score => (
                                    <tr key={ score.id }>
                                        <td>{ score.id }</td>
                                        <td><Link to={ `/stand?id=${ score.id }` }>{ score.title }</Link></td>
                                        <td>{ score.sumVotes }</td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
            }
        </>
    )
}

export default JuryPage;