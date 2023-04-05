import React, { Dispatch, SetStateAction, useEffect, useState } from "react";
import restApi from "../../utils/restApi";
import { StandWithScore } from "../../model/Stand";
import { Link } from "react-router-dom";
import "./JuryPage.css";
import Dropdown from "../../components/Dropdown/Dropdown";

const eventQuery = "eventID";
const sortOptions = ["Resultat", "Alfabetisk"];

/**
 * Page for jury to see the results of an event, while the event is ongoing.
 * @author Martin Berg Alstad
 */
const JuryPage = () => {

    const params = new URLSearchParams(window.location.search);
    const eventId = params.has(eventQuery) ? params.get(eventQuery) ?? "1" : "1";

    const [scores, setScores] = useState<StandWithScore[] | null>(null);
    const [event, setEvent] = useState<EventModel | null>(null);

    /**
     * Handles sorting of the list of stands.
     * @param value The value of the selected option.
     */
    function handleSort(value: string): void {
        let list: StandWithScore[] | undefined;
        if (value === sortOptions[0]) {
            list = scores?.sort((a, b) => b.sumVotes - a.sumVotes);
        }
        else {
            list = scores?.sort((a, b) => a.title.localeCompare(b.title));
        }
        if (list) {
            setScores([...list]);
        }
    }

    useEffect(() => {

        async function getData(setter: Dispatch<SetStateAction<any>>, url: string) {
            const res = await restApi({ url, method: "GET" });
            if (res.status === 200) {
                setter(res.body);
            }
            else {
                console.error(res);
            }
        }

        getData(setScores, `/api/vote/score?eventID=${ eventId }`);
        getData(setEvent, `/api/admin/event?eventID=${ eventId }`);
    }, []);

    return (
        <div className={ "jury-page-parent" }>
            <h2>Resultat for { event?.name ?? "event" }</h2>
            {
                scores === null ?
                    <p>Laster inn...</p>
                    :
                    <div className={ "table-container" }>
                        <div className={ "dropdown" }>Sorter: { " " }
                            <Dropdown options={ sortOptions } onChange={ handleSort } />
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Navn</th>
                                    <th>Resultat</th>
                                </tr>
                            </thead>
                            <tbody>{
                                scores.map(score => (
                                    <tr key={ score.id }>
                                        <td>{ score.id }</td>
                                        <td><Link to={ `/stand?id=${ score.id }` } title={ "Les mer" }>
                                            { score.title }
                                        </Link></td>
                                        <td>{ score.sumVotes }</td>
                                    </tr>
                                )) }
                            </tbody>
                        </table>
                    </div>
            }
        </div>
    );
};

export default JuryPage;
