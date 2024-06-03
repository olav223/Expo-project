import "./FingerBlock.css";

const FingerBlock = () => {
    return <div className="container center">
        <h2>Tilgang Begrenset til stemmeside for Expo 2024</h2>
        <p>Vi har oppdaget ett av følgende problemer med din nåværende oppsett:</p>
        <ul>
            <li><strong>Adblocker oppdaget:</strong> Vennligst deaktiver din Adblocker for å fortsette.</li>
            <li><strong>Ikke-støttet nettleser:</strong> Nettleseren du bruker er ikke støttet.</li>
        </ul>
        <p><strong>Neste steg:</strong></p>
        <ul>
            <li>Deaktiver din reklameblokkering og oppdater siden.</li>
            <li>Bytt til en støttet nettleser og prøv igjen.</li>
            <li>Hvis du trenger hjelp, kom til stand <strong>D2 eller D3.</strong></li>
        </ul>
        <p>Takk for din forståelse og samarbeid.</p>
    </div>
}

export default FingerBlock;