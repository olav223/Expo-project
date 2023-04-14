import { Link, useParams } from "react-router-dom";
import restApi from "../../utils/restApi";
import "./Admin.css";
import { StandList } from "../../components/StandList/StandList";
import notification from "../../utils/notification";

/**
 * Viser en liste over alle stands som er registrert på et gitt event.
 * Hvor man kan endre, legge til eller slette en stands.
 * @author Torbjørn Vatnelid
 */
const AdminStandList = () => {
    const { id } = useParams();

    const deleteStand = async (id: any) => {
        const result = await restApi({ url: `/api/stand`, method: "DELETE", body: id });
        if (result.status == 200) {
            notification({ type: "success", text: "Stand slettet" });
            window.location.reload();
        }
        else {
            console.error(result);
            notification({ type: "error", text: "Kunne ikke slette stand" });
        }
    };

    return <div className="standList">
        <h2>Stands</h2>
        <Link to={ `/admin/stand/edit/-1?eventId=${ id }` }>
            <button className={ "submit-btn" }>Legg til en ny stand</button>
        </Link>
        <StandList eventId={ id } diableHeader components={ (stand) =>
            <div style={ { textAlign: "left" } }>
                <Link to={ `/admin/stand/edit/${ stand.id }` }>
                    <button type="submit">
                        Endre
                    </button>
                </Link>
                <button type="submit" className="delete-button" onClick={ () => deleteStand(stand.id) }>Slett</button>
            </div>
        } />
    </div>;
};

export default AdminStandList;
